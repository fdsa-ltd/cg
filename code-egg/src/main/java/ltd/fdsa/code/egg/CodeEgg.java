package ltd.fdsa.code.egg;

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
//import ltd.fdsa.code.annotation.Relation;
import ltd.fdsa.code.annotation.Table;
import ltd.fdsa.code.extension.*;
import ltd.fdsa.code.model.Entity;
import ltd.fdsa.code.model.Field;
import ltd.fdsa.code.model.Schema;
import ltd.fdsa.code.model.Association;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

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

    public void execute(Schema builder) {
        log.info("------------------------------------------------------------------------");
        log.info("Try to generate code in " + builder.getInput());
        log.info("------------------------------------------------------------------------");

        try {

            ClassLoader classLoader = new ClassLoader(builder.getInput());

            // step2 获取或创建模板引擎
            if (!new File(builder.getTemplate()).exists()) {
                for (var path : classLoader.jarFiles()) {
                    if (!path.startsWith("templates")) {
                        continue;
                    }
                    var targetFile = new File(builder.getTemplate() + "/" + path.substring("templates".length()));
                    if (!targetFile.getParentFile().exists()) {
                        var i = targetFile.getParentFile().mkdirs();
                    }
                    var initialStream = CodeEgg.class.getClassLoader().getResourceAsStream(path);
                    assert initialStream != null;
                    Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    initialStream.close();
                }
            }
            templateFile = new File(builder.getTemplate());
            configuration.setDirectoryForTemplateLoading(templateFile);
            configuration.setEncoding(Locale.getDefault(), "UTF-8");
            // step3 创建模板的数据模型
            var data = new HashMap<String, Object>();
            var settings = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
            if (!new File(builder.getSetting()).exists()) {
                for (var path : classLoader.jarFiles()) {
                    if (!path.startsWith("config")) {
                        continue;
                    }
                    var targetFile = new File(builder.getSetting() + "/" + path.substring("config".length()));
                    if (!targetFile.getParentFile().exists()) {
                        var i = targetFile.getParentFile().mkdirs();
                    }
                    var initialStream = CodeEgg.class.getClassLoader().getResourceAsStream(path);
                    assert initialStream != null;
                    Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    initialStream.close();
                }
            }
            var settingFolder = new File(builder.getSetting());
            if (settingFolder.exists() && settingFolder.isDirectory()) {
                for (var path : Objects.requireNonNull(settingFolder.list())) {
                    if (path.endsWith(".yaml") || path.endsWith(".yml")) {
                        var d = loadYaml(Files.newInputStream(Paths.get(settingFolder.getPath() + "/" + path)));
                        settings.putAll(d);
                    } else if (path.endsWith(".properties")) {
                        var d = loadProp(Files.newInputStream(Paths.get(settingFolder.getPath() + "/" + path)));
                        settings.putAll(d);
                    }
                }
            }
            data.put("setting", new StringDictMethod(settings));
            data.put("snake_case", new SnakeCaseMethod());
            data.put("camel_case", new CamelCaseMethod());
            data.put("spinal_case", new SpinalCaseMethod());
            data.put("pascal_case", new PascalCaseMethod());
            data.put("module", builder);
            // step4 加载模版文件
            for (var file : find(templateFile)) {
                var sourcePath = file.getPath();
                if (sourcePath.contains("entity") && sourcePath.contains("relation")) {
                    for (var entity : builder.getEntities()) {
                        data.put("entity", entity);
                        for (var relation : builder.getRelations()) {
                            data.put("relation", relation);
                            generateCode(file, data, builder.getOutput());
                        }
                    }
                    continue;
                }
                if (sourcePath.contains("entity")) {
                    for (var entity : builder.getEntities()) {
                        data.put("entity", entity);
                        generateCode(file, data, builder.getOutput());
                    }
                    continue;
                }
                if (sourcePath.contains("relation")) {
                    for (var relation : builder.getRelations()) {
                        data.put("relation", relation);
                        generateCode(file, data, builder.getOutput());
                    }
                    continue;
                }
                generateCode(file, data, builder.getOutput());
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
                var i = targetFile.getParentFile().mkdirs();
            }
            log.info("generate code: " + targetFile.getPath());
            Writer out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(targetFile.toPath()), StandardCharsets.UTF_8));
            // step6 输出文件
            template.process(data, out);
        } catch (TemplateException | IOException e) {
            log.error("error", e);
        }
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
                    if (!key.isEmpty()) {
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