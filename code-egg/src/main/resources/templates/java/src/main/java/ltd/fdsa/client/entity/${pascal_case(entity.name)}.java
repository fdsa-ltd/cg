package ${dict('project.package','ltd.fdsa.client')}.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Data
public class ${pascal_case(entity.name)} {

    <#list entity.fields  as field>
    @JsonProperty("${snake_case(field.name)}") 
    private ${field.type} ${field.name};
    </#list>
}
