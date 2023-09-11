
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class TestCase {

    @Before
    public void before() {

    }

    @Test
    public void loadProp() throws IOException {

        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("module.properties"));

        var data = loadYaml(this.getClass().getResourceAsStream("app.yml"));
        properties.putAll(data);
        for (var entry : properties.entrySet()) {
            log.info(entry.getKey().toString() + " : " + entry.getValue().toString());
        }
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
