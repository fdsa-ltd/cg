package ltd.fdsa.code;

import com.google.common.base.Strings;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Relation;
import ltd.fdsa.code.annotation.Table;
import ltd.fdsa.code.extension.StringLengthMethod;
import ltd.fdsa.code.model.Entity;
import ltd.fdsa.code.model.Field;
import ltd.fdsa.code.model.Module;
import ltd.fdsa.code.model.RelationDefine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
public class CodeEgg {

    static Table DEFAULT_TABLE;

    static Column DEFAULT_COLUMN;

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

    Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
    File templateFile;

    public void execute(String jarFolder, String outputFolder, String templateFolder) {
        log.info("------------------------------------------------------------------------");
        log.info("Try to generate code in ");
        log.info("------------------------------------------------------------------------");

        try {
            log.info("Generate file start");

            var builder = Module.builder();
            builder.name("project name");
            builder.description("description of the project");
            log.info("得到class描述");
            ClassLoader classLoader = new ClassLoader(jarFolder);
            var classList = classLoader.loadClasses(entry -> entry.getName().endsWith(".class"), clazz -> IEntity.class.isAssignableFrom(clazz) && !IEntity.class.equals(clazz));
            getEntities(classLoader, classList, builder);
            log.info(builder.build().toString());
            log.info("得到模板文件");


            // step1 创建freeMarker配置实例

            if (Strings.isNullOrEmpty(templateFolder)) {
                var file = CodeEgg.class.getClassLoader().getResource("templates").getFile();
                try {
                    JarFile jarFile = new JarFile(file.substring(5, file.length() - 11));
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        var entry = entries.nextElement();
                        if (entry.isDirectory()) {
                            continue;
                        }
                        var path = entry.getName();
                        if (!path.startsWith("templates")) {
                            continue;
                        }
                        var targetFile = new File("./output/" + path);
                        if (!targetFile.getParentFile().exists()) {
                            targetFile.getParentFile().mkdirs();
                        }
                        var initialStream = CodeEgg.class.getClassLoader().getResourceAsStream(path);

                        log.info("copy:{}", targetFile);
                        Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        initialStream.close();
                    }
                    templateFolder = "./output/templates";
                } catch (IOException e) {
                    log.error("io:", e);
                }
            }
            templateFile = new File(templateFolder);

            log.info(templateFile.getAbsoluteFile().getPath());
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(templateFile);

            // step3 创建数据模型
            var data = new HashMap<String, Object>();
            data.put("module", builder.build());
            data.put("extension", new StringLengthMethod());

            // step4 加载模版文件
            for (var file : find(templateFile)) {
                var sourcePath = file.getPath();
                if (sourcePath.contains("entity") && sourcePath.contains("relation")) {
                    for (var entity : builder.build().getEntities()) {
                        data.put("entity", entity);
                        for (var relation : builder.build().getRelations()) {
                            data.put("relation", relation);
                            sss(file, data, outputFolder);
                        }

                    }
                    continue;
                }
                if (sourcePath.contains("entity")) {
                    for (var entity : builder.build().getEntities()) {
                        data.put("entity", entity);
                        sss(file, data, outputFolder);
                    }
                    continue;

                }
                if (sourcePath.contains("relation")) {
                    for (var relation : builder.build().getRelations()) {
                        data.put("relation", relation);
                        sss(file, data, outputFolder);
                    }
                    continue;
                }
                sss(file, data, outputFolder);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    private void sss(File sourceFile, Object data, String outputFolder) throws IOException {

        try {
            var source = sourceFile.getPath();
            Template fileNameTemplate = new Template("file", source, configuration);
            StringWriter result = new StringWriter();
            fileNameTemplate.process(data, result);
            var targetName = result.toString().substring(this.templateFile.getPath().length() + 1);
            var templateName = sourceFile.getPath().substring(this.templateFile.getPath().length() + 1).replace("\\", "/");
            Template template = configuration.getTemplate(templateName);
            // step5 生成数据
            File targetFile = new File(outputFolder + "/" + targetName);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }

            Writer out  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
            // step6 输出文件
            template.process(data, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getEntities(ClassLoader classLoader, List<Class<?>> classes, Module.ModuleBuilder moduleBuilder) {

        List<Entity> results = new ArrayList<Entity>();
        for (var item : classes) {
            var entityBuilder = Entity.builder();
            entityBuilder.code(item.getCanonicalName());
            var table = item.getAnnotation(Table.class);
            if (table == null) {
                table = DEFAULT_TABLE;
            }
            var name = table.name();
            if (Strings.isNullOrEmpty(name)) {
                entityBuilder.name(item.getSimpleName());
            } else {
                entityBuilder.name(name);
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
            entityBuilder.fields(getFields(classLoader, item, moduleBuilder));
            results.add(entityBuilder.build());
        }
        moduleBuilder.entities(results.toArray(new Entity[0]));
    }

    private Field[] getFields(ClassLoader classLoader, Class<?> clazz, Module.ModuleBuilder moduleBuilder) {
        List<Field> results = new ArrayList<Field>();
        List<RelationDefine> relationDefines = new ArrayList<RelationDefine>();
        for (var item : classLoader.getDeclaredFields(clazz)) {

            var relation = item.getAnnotation(Relation.class);
            if (relation != null) {
                var relationDefineBuilder = RelationDefine.builder();
                relationDefineBuilder.name(relation.value());
                relationDefineBuilder.fromEntity(relation.entity());
                relationDefineBuilder.fromField(relation.field());
                relationDefineBuilder.toEntity(clazz);
                relationDefineBuilder.toField(item.getName());
                relationDefines.add(relationDefineBuilder.build());
            }
            var builder = Field.builder();
            builder.code(clazz.getCanonicalName() + "." + item.getName());
            var column = item.getAnnotation(Column.class);
            if (column == null) {
                column = DEFAULT_COLUMN;
            }
            var name = column.name();
            if (Strings.isNullOrEmpty(name)) {
                builder.name(item.getName());
            } else {
                builder.name(name);
            }
            var type = column.type();
            if (Strings.isNullOrEmpty(type)) {
                builder.type(item.getType().getSimpleName());
            } else {
                builder.type(type);
            }
            var remark = column.remark();
            if (Strings.isNullOrEmpty(remark)) {
                builder.remark(item.getName());
            } else {
                builder.remark(remark);
            }
            var isNull = column.isNull();

            builder.isNull(isNull);

            var length = column.length();

            builder.length(length);
            var scale = column.scale();

            builder.scale(scale);

            var autoIncrement = column.autoIncrement();

            builder.autoIncrement(autoIncrement);

            results.add(builder.build());
        }
        moduleBuilder.relations(relationDefines.toArray(new RelationDefine[0]));
        return results.toArray(new Field[0]);
    }

    private List<File> find(File dirFile) {

        List<File> result = new ArrayList<File>();

        if (!dirFile.exists()) {
            return result;
        }
        if (dirFile.isFile()) {
            result.add(dirFile);
            return result;
        }
        for (File file : dirFile.listFiles()) {
            result.addAll(find(file));
        }
        return result;
    }
}