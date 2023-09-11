package ltd.fdsa.data;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.code.egg.CodeEgg;
import ltd.fdsa.data.egg.ModelEgg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;


@Slf4j
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        var context = SpringApplication.run(App.class, args);
        var properties = new Properties();
        for (var item : context.getEnvironment().getPropertySources()) {
            if (item instanceof OriginTrackedMapPropertySource) {
                var source = (OriginTrackedMapPropertySource) item;
                for (var e : source.getSource().entrySet()) {
                    properties.put(e.getKey(), e.getValue().toString());
                }
            } else if (item instanceof PropertiesPropertySource) {
                var source = (PropertiesPropertySource) item;
                properties.putAll(source.getSource());
            } else if (item instanceof RandomValuePropertySource) {
                var source = (RandomValuePropertySource) item;
            } else {
                log.info(item.toString());
            }
        }
        var project = new ModelEgg(properties).builder();
        CodeEgg egg = new CodeEgg();
        for (var module : project.getModules()) {
            egg.execute(module);
        }
        log.info("APPLICATION FINISHED");
    }

}


