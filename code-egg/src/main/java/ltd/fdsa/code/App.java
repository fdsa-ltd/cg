package ltd.fdsa.code;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.code.model.Module;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;


@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {
    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(App.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        var inputFolder = "./target";
        if (args.length >= 1) {
            File file = new File(args[0]);
            if (file.isDirectory()) {
                inputFolder = file.getPath();
            } else {
                inputFolder = file.getParent();
            }
        }
        var builder = Module.builder();
        builder.name("project name");
        builder.description("description of the project");
        builder.inputFolder(inputFolder);
        builder.outputFolder("./output");
        builder.templateFolder("");
        builder.settingFolder("./settings");
        System.out.println(builder.build().toString());
        CodeEgg egg = new CodeEgg();
        egg.execute(builder);
    }
}


