package ltd.fdsa.maven.codegg;

import com.google.common.base.Strings;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import ltd.fdsa.code.CodeEgg;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

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


    @Override
    @SneakyThrows
    public void execute() {
        log.info("------------------------------------------------------------------------");
        try {
            if (Strings.isNullOrEmpty(input)) {
                input = this.project.getBuild().getDirectory();
            }
            if (Strings.isNullOrEmpty(output)) {
                output = "./output";
            }
            CodeEgg egg = new CodeEgg();
            egg.execute(this.input, this.output, this.template);

        } catch (Exception e) {
            log.error("execute", e);
        }
    }

}