package ltd.fdsa.data.egg;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.model.*;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.*;


@Slf4j
public class ModelEgg {
    final Properties properties;
    final ObjectMapper objectMapper = new ObjectMapper();


    public ModelEgg(Properties properties) {
        this.properties = properties;
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

    }

    public Project builder() {
        var projectBuilder = Project.builder();
        try {
            // load driver
            Driver driver = dynamicLoadJdbc(new File(Objects.requireNonNull(properties.getProperty("driver_jar"))), properties.getProperty("driver_class_name"));
            // get jdbc url
            StringReader reader = new StringReader(Objects.requireNonNull(properties.getProperty("driver_url_template")));

            final Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            Template fileNameTemplate = new Template("jdbc_url", reader, configuration);
            StringWriter writer = new StringWriter();
            fileNameTemplate.process(properties, writer);
            String jdbcUrl = writer.toString();
            // get connection
            Connection connection = DriverManager.getConnection(jdbcUrl, properties.getProperty("user"), properties.getProperty("password"));

            // get database metadata
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet metaDataCatalogs = metaData.getCatalogs();
            printInfo(metaDataCatalogs.getMetaData(), "Catalogs");

            List<Schema> modules = new ArrayList<Schema>();
            while (metaDataCatalogs.next()) {
                var catalog = metaDataCatalogs.getString("TABLE_CAT");
                if (!Objects.equals(properties.getProperty("database"), catalog)) {
                    continue;
                }
                var metaDataSchemas = metaData.getSchemas(catalog, null);
                printInfo(metaDataSchemas.getMetaData(), "Schemas");

                do {
                    var schema = "public"; //有些数据库没有schema
                    if (metaDataSchemas.next()) {
                        schema = metaDataSchemas.getString("TABLE_SCHEM");
                    }
                    var moduleBuilder = Schema.builder()
                            .name(schema)
                            .description(catalog)
                            .inputFolder(this.properties.getProperty("input", "./"))
                            .outputFolder(this.properties.getProperty("output", "./output"))
                            .templateFolder(this.properties.getProperty("template", "./templates"))
                            .settingFolder(this.properties.getProperty("config", "./config"));

                    // get table metadata according to catalog
                    var metaDataTables = metaData.getTables(catalog, schema, null, null);
                    printInfo(metaDataTables.getMetaData(), "Tables");
                    List<Entity> entities = new ArrayList<Entity>();
                    List<Association> relations = new ArrayList<Association>();
                    while (metaDataTables.next()) {
                        var tableName = metaDataTables.getString("TABLE_NAME");
                        var tableBuilder = Entity.builder()
                                .name(tableName)
                                .display(tableName)
                                .code(tableName)
                                .remark(metaDataTables.getString("REMARKS"));
                        var columns = metaData.getColumns(catalog, schema, tableName, null);
                        printInfo(columns.getMetaData(), "Columns");

                        var pkInfo = metaData.getPrimaryKeys(catalog, schema, tableName);
                        printInfo(pkInfo.getMetaData(), "pk");
                        var fkInfo = metaData.getExportedKeys(catalog, schema, tableName);
                        printInfo(fkInfo.getMetaData(), "fk");

                        List<Field> fields = new ArrayList<Field>();
                        int index = 0;
                        while (columns.next()) {
                            index++;

                            fields.add(Field.builder()
                                    .primary(false).scale(columns.getMetaData().getScale(index)).length(1)
                                    .nullable(columns.getMetaData().isNullable(index) > 0)
                                    .code(columns.getString("COLUMN_NAME"))
                                    .display(columns.getString("COLUMN_NAME"))
                                    .name(columns.getString("COLUMN_NAME"))
                                    .type(columns.getString("TYPE_NAME"))
                                    .remark(columns.getString("REMARKS"))
                                    .build());
                        }
                        tableBuilder.fields(fields.toArray(new Field[0]));
                        entities.add(tableBuilder.build());
                    }
                    moduleBuilder.entities(entities);
                    moduleBuilder.relations(relations);
                    modules.add(moduleBuilder.build());
                } while (metaDataSchemas.next());
            }
            projectBuilder.modules(modules);
            log.info("project info: {}", objectMapper.writeValueAsString(projectBuilder.build()));


            dynamicUnLoadJdbc(driver);

        } catch (Exception exception) {
            log.error("error", exception);
        }
        return projectBuilder.build();
    }

    private void printInfo(ResultSetMetaData metaData, String message) {

        try {
            Map<String, String> info = new HashMap<String, String>();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                info.put(metaData.getColumnName(i + 1), metaData.getColumnTypeName(i + 1));
            }
            log.info(message + ": " + objectMapper.writeValueAsString(info));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<URL> dynamicLoadLibs(File file) {
        List<URL> result = new ArrayList<>();
        if (!file.exists()) {
            return result;
        }
        if (!file.isFile()) {
            for (File item : Objects.requireNonNull(file.listFiles())) {
                result.addAll(dynamicLoadLibs(item));
            }
        } else {
            if (file.getPath().endsWith(".jar")) {
                try {
                    result.add(file.toURI().toURL());
                } catch (MalformedURLException ignored) {
                }
            }
        }
        return result;
    }

    // 动态加载jdbc驱动
    private Driver dynamicLoadJdbc(File file, String className) throws Exception {
        List<URL> urlList = dynamicLoadLibs(new File("./libs"));
        urlList.addAll(dynamicLoadLibs(file));
        URLClassLoader urlClassLoader = new URLClassLoader(urlList.toArray(new URL[0]));
        Driver driver = (Driver) Class.forName(className, true, urlClassLoader).getDeclaredConstructor().newInstance();
        DriverManager.registerDriver(new DriverShim(driver));
        return driver;
    }

    // 每一次测试完卸载对应版本的jdbc驱动
    private void dynamicUnLoadJdbc(Driver driver) throws SQLException {
        DriverManager.deregisterDriver(driver);
    }

}


