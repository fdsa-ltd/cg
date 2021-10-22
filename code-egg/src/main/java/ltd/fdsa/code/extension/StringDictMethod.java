package ltd.fdsa.code.extension;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;


import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StringDictMethod implements TemplateMethodModelEx {
    final Properties properties;

    public StringDictMethod(String dir) throws IOException {
        this.properties = new Properties();
        for (var path : new File(dir).list((f, n) -> f.isFile())) {
            if (path.endsWith(".yaml") || path.endsWith(".yml")) {
                FileInputStream file = new FileInputStream(path);
                this.properties.putAll(loadYaml(file));
            } else if (path.endsWith(".properties")) {
                FileInputStream file = new FileInputStream(path);
                this.properties.putAll(loadProp(file));
            }
        }
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list.size() != 1) {
            throw new TemplateModelException("Wrong arguments");
        }
        var input = list.get(0);
        var output = this.properties.get(input);
        return output;
    }

    Properties loadProp(FileInputStream file) throws IOException {
        Properties properties = new Properties();
        properties.load(file);
        return properties;
    }

    Map<String, String> loadYaml(FileInputStream file) {
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


