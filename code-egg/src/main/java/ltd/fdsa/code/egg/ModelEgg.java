package ltd.fdsa.code.egg;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;
import ltd.fdsa.code.extension.*;
import ltd.fdsa.code.model.*;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ModelEgg {

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

    final Properties properties;

    public ModelEgg(Properties properties) {
        this.properties = properties;
    }


    public Project builder() {
        log.info("------------------------------------------------------------------------");
        log.info("Try to generate code in " + this.properties.getProperty("input", "./"));
        log.info("------------------------------------------------------------------------");
        var builder = Schema.builder()
                .name(this.properties.getProperty("project.name"))
                .description(this.properties.getProperty("project.description"))
                .inputFolder(this.properties.getProperty("input", "./"))
                .outputFolder(this.properties.getProperty("output", "./output"))
                .templateFolder(this.properties.getProperty("template", ""))
                .settingFolder(this.properties.getProperty("config", "./config"));


        try {
            // step1 获取项目模型定义
            log.info("Get java class description");
            ClassLoader classLoader = new ClassLoader(this.properties.getProperty("input", "./"));
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
                        var ignore = targetFile.getParentFile().mkdirs();
                    }
                    var initialStream = ModelEgg.class.getClassLoader().getResourceAsStream(path);
                    assert initialStream != null;
                    Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    initialStream.close();
                }
            }


        } catch (Exception e) {
            log.error("error:", e);
        }
        return Project.builder().modules(Lists.newArrayList(builder.build())).build();
    }

    private void getEntities(ClassLoader classLoader, List<Class<?>> classes, Schema.ModuleBuilder moduleBuilder) {
        List<Entity> results = new ArrayList<>();
        for (var item : classes) {
            results.add(loadEntity(classLoader, item, moduleBuilder));
        }
        moduleBuilder.entities(results.stream().sorted((a, b) -> {
            return a.getDisplay().compareTo(b.getDisplay());
        }).collect(Collectors.toList()));
    }

    private Entity loadEntity(ClassLoader classLoader, Class<?> item, Schema.ModuleBuilder moduleBuilder) {
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

    private Field[] getFields(ClassLoader classLoader, Class<?> clazz, Schema.ModuleBuilder moduleBuilder) {
        List<Field> results = new ArrayList<>();
        List<Association> relationDefines = new ArrayList<>();
        for (var item : classLoader.getDeclaredFields(clazz)) {

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
                remark = item.getName();
            }
            if (item.getType().isEnum()) {
                remark += " - " + (Arrays.stream(item.getType().getEnumConstants()).map(Object::toString).collect(Collectors.joining(",")));
            }
            builder.remark(remark);
            var primary = column.primary();
            builder.primary(primary);

            var nullable = !primary && column.nullable();
            builder.nullable(nullable);

            var length = column.length();
            builder.length(length);

            var scale = column.scale();
            builder.scale(scale);


            var relation = column.r();
            if (relation != void.class) {
                var relationDefineBuilder = Association.builder();

                if (Strings.isNullOrEmpty(column.rnm())) {
                    relationDefineBuilder.name(item.getName());
                } else {
                    relationDefineBuilder.name(column.rnm());
                }
                if (Strings.isNullOrEmpty(column.rid())) {
                    relationDefineBuilder.code(item.getName());
                } else {
                    relationDefineBuilder.code(column.rid());
                }
                if (Strings.isNullOrEmpty(remark)) {
                    relationDefineBuilder.remark(item.getName());
                } else {
                    relationDefineBuilder.remark(remark);
                }

                relationDefineBuilder.type(column.rtp());
                relationDefineBuilder.source(loadEntity(classLoader, relation, null));
                relationDefineBuilder.primaryKey(column.rid());
                relationDefineBuilder.target(loadEntity(classLoader, clazz, null));
                relationDefineBuilder.foreignKey(item.getName());
                relationDefines.add(relationDefineBuilder.build());
            }
            results.add(builder.build());
        }
        moduleBuilder.relations(relationDefines);
        return results.toArray(new Field[0]);
    }

}