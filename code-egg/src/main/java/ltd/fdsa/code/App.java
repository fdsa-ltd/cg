package ltd.fdsa.code;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.code.model.Module;

import java.io.IOException;


@Slf4j
public class App {
    public static void main(String[] args) throws IOException, TemplateException {

        CodeEgg egg = new CodeEgg();

        var builder = Module.builder();
        builder.name("project name");
        builder.description("description of the project");
        builder.jarFolder("./target");
        builder.outputFolder("./output");
        builder.templateFolder("");
        egg.execute(builder);
    }
}


