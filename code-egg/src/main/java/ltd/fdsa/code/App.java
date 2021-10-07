package ltd.fdsa.code;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class App {
    public static void main(String[] args) throws IOException, TemplateException {

        CodeEgg egg = new CodeEgg();
        egg.execute("./target", "./output", "");
    }
}


