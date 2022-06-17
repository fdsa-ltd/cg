package ltd.fdsa.code;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.google.common.base.Strings;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Relation;
import ltd.fdsa.code.annotation.Table;
import ltd.fdsa.code.extension.*;
import ltd.fdsa.code.model.Entity;
import ltd.fdsa.code.model.Field;
import ltd.fdsa.code.model.Module;
import ltd.fdsa.code.model.Association;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

@Slf4j
public class CodeEgg {

    static final Table DEFAULT_TABLE;

    static final Column DEFAULT_COLUMN;

    static {
        // 默认注解
        @Table
        final class c {
            @Column
            String name;
        }
        DEFAULT_TABLE = c.class.getAnnotation(Table.class);
        DEFAULT_COLUMN = c.class.getDeclaredFields()[0].getAnnotation(Column.class);
    }

    final Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
    File templateFile;

    public void execute(Module.ModuleBuilder builder) {
        log.info("------------------------------------------------------------------------");
        log.info("Try to generate code in " + builder.getInputFolder());
        log.info("------------------------------------------------------------------------");

        try {
            // step1 获取项目模型定义
            log.info("Get java class description");
            ClassLoader classLoader = new ClassLoader(builder.getInputFolder());
            var classList = classLoader.loadClasses(
                    entry -> entry.getName().endsWith(".class"),
                    clazz -> clazz.getAnnotation(Table.class) != null);
            getEntities(classLoader, classList, builder);
            log.info("------------------------------------------------------------------------");
            log.info(builder.build().toString());
            log.info("------------------------------------------------------------------------");
            log.info("Get template files");
            // step2 获取或创建模板引擎
            if (!new File(builder.getTemplateFolder()).exists()) {
                for (var path : classLoader.jarFiles()) {
                    if (!path.startsWith("templates")) {
                        continue;
                    }
                    var targetFile = new File(builder.getTemplateFolder() + "/" + path.substring("templates".length()));
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    var initialStream = CodeEgg.class.getClassLoader().getResourceAsStream(path);
                    assert initialStream != null;
                    Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    initialStream.close();
                }
            }
            templateFile = new File(builder.getTemplateFolder());
            configuration.setDirectoryForTemplateLoading(templateFile);
            configuration.setEncoding(Locale.getDefault(), "UTF-8");
            // step3 创建模板的数据模型
            var data = new HashMap<String, Object>();
            var settings = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
            if (!new File(builder.getSettingFolder()).exists()) {
                for (var path : classLoader.jarFiles()) {
                    if (!path.startsWith("settings")) {
                        continue;
                    }
                    var targetFile = new File(builder.getSettingFolder() + "/" + path.substring("settings".length()));
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    var initialStream = CodeEgg.class.getClassLoader().getResourceAsStream(path);
                    assert initialStream != null;
                    Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    initialStream.close();
                }
            }
            var settingFolder = new File(builder.getSettingFolder());
            if (settingFolder.exists() && settingFolder.isDirectory()) {
                for (var path : Objects.requireNonNull(settingFolder.list())) {
                    if (path.endsWith(".yaml") || path.endsWith(".yml")) {
                        var d = loadYaml(new FileInputStream(settingFolder.getPath() + "/" + path));
                        settings.putAll(d);
                    } else if (path.endsWith(".properties")) {
                        var d = loadProp(new FileInputStream(settingFolder.getPath() + "/" + path));
                        settings.putAll(d);
                    }
                }
            }
            data.put("setting", new StringDictMethod(settings));
            data.put("snake_case", new SnakeCaseMethod());
            data.put("camel_case", new CamelCaseMethod());
            data.put("spinal_case", new SpinalCaseMethod());
            data.put("pascal_case", new PascalCaseMethod());
            data.put("module", builder.build());
            // step4 加载模版文件
            for (var file : find(templateFile)) {
                var sourcePath = file.getPath();
                if (sourcePath.contains("entity") && sourcePath.contains("relation")) {
                    for (var entity : builder.build().getEntities()) {
                        data.put("entity", entity);
                        for (var relation : builder.build().getRelations()) {
                            data.put("relation", relation);
                            generateCode(file, data, builder.getOutputFolder());
                        }
                    }
                    continue;
                }
                if (sourcePath.contains("entity")) {
                    for (var entity : builder.build().getEntities()) {
                        data.put("entity", entity);
                        generateCode(file, data, builder.getOutputFolder());
                    }
                    continue;
                }
                if (sourcePath.contains("relation")) {
                    for (var relation : builder.build().getRelations()) {
                        data.put("relation", relation);
                        generateCode(file, data, builder.getOutputFolder());
                    }
                    continue;
                }
                generateCode(file, data, builder.getOutputFolder());
            }
        } catch (Exception e) {
            log.error("error:", e);
        }

    }

    private void generateCode(File sourceFile, Object data, String outputFolder) {
        try {
            log.info("------------------------------------------------------------------------");
            var source = sourceFile.getPath();
            Template fileNameTemplate = new Template("file", source, configuration);
            StringWriter result = new StringWriter();
            fileNameTemplate.process(data, result);
            var targetName = result.toString().substring(this.templateFile.getPath().length() + 1);
            var templateName = sourceFile.getPath().substring(this.templateFile.getPath().length() + 1).replace("\\",
                    "/");
            Template template = configuration.getTemplate(templateName);
            // step5 生成数据
            File targetFile = new File(outputFolder + "/" + targetName);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            log.info("generate code: " + targetFile.getPath());
            Writer out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(targetFile), StandardCharsets.UTF_8));
            // step6 输出文件
            template.process(data, out);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getEntities(ClassLoader classLoader, List<Class<?>> classes, Module.ModuleBuilder moduleBuilder) {
        List<Entity> results = new ArrayList<>();
        for (var item : classes) {
            results.add(loadEntity(classLoader, item, moduleBuilder));
        }
        moduleBuilder.entities(results.toArray(new Entity[0]));
    }

    private Entity loadEntity(ClassLoader classLoader, Class<?> item, Module.ModuleBuilder moduleBuilder) {
        var entityBuilder = Entity.builder();
        entityBuilder.code(item.getCanonicalName());
        var table = item.getAnnotation(Table.class);
        if (table == null) {
            table = DEFAULT_TABLE;
        }
        entityBuilder.name(item.getSimpleName());

        var display = table.name();
        if (Strings.isNullOrEmpty(display)) {
            entityBuilder.display(item.getSimpleName());
        } else {
            entityBuilder.display(display);
        }
        var value = table.value();
        if (Strings.isNullOrEmpty(value)) {
            entityBuilder.code(item.getSimpleName());
        } else {
            entityBuilder.code(value);
        }
        var remark = table.remark();
        if (Strings.isNullOrEmpty(remark)) {
            entityBuilder.remark(item.getSimpleName());
        } else {
            entityBuilder.remark(remark);
        }
        if (moduleBuilder != null) {
            entityBuilder.fields(getFields(classLoader, item, moduleBuilder));
        }
        return entityBuilder.build();
    }

    private Field[] getFields(ClassLoader classLoader, Class<?> clazz, Module.ModuleBuilder moduleBuilder) {
        List<Field> results = new ArrayList<>();
        List<Association> relationDefines = new ArrayList<>();
        for (var item : classLoader.getDeclaredFields(clazz)) {

            var relation = item.getAnnotation(Relation.class);
            if (relation != null) {
                var relationDefineBuilder = Association.builder();
                var name = relation.name();
                if (Strings.isNullOrEmpty(name)) {
                    relationDefineBuilder.name(item.getName());
                } else {
                    relationDefineBuilder.name(name);
                }
                var code = relation.value();
                if (Strings.isNullOrEmpty(code)) {
                    relationDefineBuilder.code(item.getName());
                } else {
                    relationDefineBuilder.code(name);
                }
                var remark = relation.remark();
                if (Strings.isNullOrEmpty(remark)) {
                    relationDefineBuilder.remark(item.getName());
                } else {
                    relationDefineBuilder.remark(name);
                }

                relationDefineBuilder.type(relation.type());
                relationDefineBuilder.source(loadEntity(classLoader, relation.entity(), null));
                relationDefineBuilder.primaryKey(relation.field());
                relationDefineBuilder.target(loadEntity(classLoader, clazz, null));
                relationDefineBuilder.foreignKey(item.getName());
                relationDefines.add(relationDefineBuilder.build());
            }
            var builder = Field.builder();

            var column = item.getAnnotation(Column.class);
            if (column == null) {
                column = DEFAULT_COLUMN;
            }

            builder.name(item.getName());
            builder.type(item.getType().getSimpleName());

            var code = column.value();
            if (Strings.isNullOrEmpty(code)) {
                builder.code(item.getName());
            } else {
                builder.code(code);
            }
            var display = column.name();
            if (Strings.isNullOrEmpty(display)) {
                builder.display(item.getName());
            } else {
                builder.display(display);
            }
            var remark = column.remark();
            if (Strings.isNullOrEmpty(remark)) {
                builder.remark(item.getName());
            } else {
                builder.remark(remark);
            }
            var primary = column.primary();
            builder.primary(primary);

            var nullable = !primary && column.nullable();
            builder.nullable(nullable);

            var length = column.length();
            builder.length(length);

            var scale = column.scale();
            builder.scale(scale);

            results.add(builder.build());
        }
        moduleBuilder.relations(relationDefines.toArray(new Association[0]));
        return results.toArray(new Field[0]);
    }

    private List<File> find(File dirFile) {

        List<File> result = new ArrayList<>();

        if (!dirFile.exists()) {
            return result;
        }
        if (dirFile.isFile()) {
            result.add(dirFile);
            return result;
        }
        for (File file : Objects.requireNonNull(dirFile.listFiles())) {
            result.addAll(find(file));
        }
        return result;
    }

    Map<String, String> loadProp(InputStream file) throws IOException {
        Map<String, String> lines = new LinkedHashMap<>();

        Properties properties = new Properties();
        properties.load(file);
        for (var item : properties.entrySet()) {
            lines.put(item.getKey().toString(), item.getValue().toString());
        }
        return lines;
    }

    Map<String, String> loadYaml(InputStream file) {
        final String DOT = ".";
        Map<String, String> lines = new LinkedHashMap<>();
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLParser parser = yamlFactory.createParser(file);

            String key = "";
            String value = null;
            JsonToken token = parser.nextToken();
            while (token != null) {
                if (JsonToken.START_OBJECT.equals(token)) {
                    // do nothing
                } else if (JsonToken.FIELD_NAME.equals(token)) {
                    if (key.length() > 0) {
                        key = key + DOT;
                    }
                    key = key + parser.getCurrentName();

                    token = parser.nextToken();
                    if (JsonToken.START_OBJECT.equals(token)) {
                        continue;
                    }
                    value = parser.getText();
                    lines.put(key, value);

                    int dotOffset = key.lastIndexOf(DOT);
                    if (dotOffset > 0) {
                        key = key.substring(0, dotOffset);
                    }
                    value = null;
                } else if (JsonToken.END_OBJECT.equals(token)) {
                    int dotOffset = key.lastIndexOf(DOT);
                    if (dotOffset > 0) {
                        key = key.substring(0, dotOffset);
                    } else {
                        key = "";

                    }
                }
                token = parser.nextToken();
            }
            parser.close();

            return lines;
        } catch (Exception e) {
            return lines;
        }
    }
}