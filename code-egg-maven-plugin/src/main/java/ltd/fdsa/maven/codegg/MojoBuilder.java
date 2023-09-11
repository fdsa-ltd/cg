package ltd.fdsa.maven.codegg;

import com.google.common.base.Strings;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.var;
import ltd.fdsa.code.egg.CodeEgg;
import ltd.fdsa.code.egg.ModelEgg;
import ltd.fdsa.code.model.Schema;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.springframework.core.env.*;

import java.util.Properties;

@EqualsAndHashCode(callSuper = true)
@Mojo(name = "package", defaultPhase = LifecyclePhase.PACKAGE, threadSafe = true, requiresDependencyResolution = ResolutionScope.RUNTIME)
@Data
public class MojoBuilder extends AbstractMojo {

    Log log = this.getLog();
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Parameter(property = "input")
    private String input;

    @Parameter(property = "output")
    private String output;

    @Parameter(property = "template")
    private String template;

    @Parameter(property = "setting")
    private String setting;

    @Override
    @SneakyThrows
    public void execute() {
        log.info("------------------------------------------------------------------------");
        try {
            if (Strings.isNullOrEmpty(input)) {
                input = this.project.getBuild().getDirectory();
            }
            if (Strings.isNullOrEmpty(output)) {
                output = this.project.getBuild().getOutputDirectory() + "/../project";
            }
            if (Strings.isNullOrEmpty(setting)) {
                setting = this.project.getBuild().getOutputDirectory() + "/../config";
            }
            if (Strings.isNullOrEmpty(template)) {
                template = this.project.getBuild().getOutputDirectory() + "/../templates";
            }
            Properties environment = new Properties();
            environment.put("project.name", project.getName());
            environment.put("project.description", project.getDescription());
            environment.put("input", input);
            environment.put("output", output);
            environment.put("template", template);
            environment.put("config", setting);

            CodeEgg egg = new CodeEgg();

            var builder = new ModelEgg(environment).builder();
            for (var item : builder.getModules()) {
                egg.execute(item);
            }
        } catch (Exception e) {
            log.error("execute", e);
        }
    }

}