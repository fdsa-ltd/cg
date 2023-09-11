package ${setting('project.package','//todo add project.package')?replace('/','.')}.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import ${setting('project.package','//todo add project.package')?replace('/','.')}.enums.*;

/**
 * ${entity.remark}
<#list entity.fields  as field>
 * ${snake_case(field.code)} - ${field.remark}
</#list>
 * @link table ${snake_case(entity.code)}
 * @author zhumingwu
 **/
@Data
public class ${pascal_case(entity.name)} {

    <#list entity.fields  as field>
    @JsonProperty("${snake_case(field.name)}") 
    private ${field.type} ${field.name};
    </#list>
}
