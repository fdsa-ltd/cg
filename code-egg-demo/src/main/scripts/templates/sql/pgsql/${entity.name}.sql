<#if setting('project.drop.table','true')=='true'>
DROP TABLE IF EXISTS "${snake_case(entity.code)}";
</#if>

CREATE TABLE "${snake_case(entity.code)}" (
<#list entity.fields  as field>
    "${snake_case(field.code)}" ${setting('pgsql.type.'+field.type,'pgsql.type.'+field.type)?lower_case} ${field.nullable?then('NULL','NOT NULL') },
</#list>
<#list entity.fields as field>
    <#if field.primary>
    primary key("${snake_case(field.code)}")
    </#if>
</#list>
);
<#list entity.fields as field>
COMMENT ON COLUMN "${snake_case(entity.code)}"."${snake_case(field.code)}" IS '${pascal_case(field.remark)}';
</#list>

