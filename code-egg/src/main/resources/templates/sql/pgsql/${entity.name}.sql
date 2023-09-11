<#if setting('project.drop.table','true')=='true'>
DROP TABLE IF EXISTS "${snake_case(entity.code)}";
</#if>

<#assign snake_keys=[] />
<#list entity.fields as field>
<#if field.primary>
<#assign snake_keys = snake_keys + [ '${snake_case(field.code)}' ] />
</#if>
</#list>

CREATE TABLE "${snake_case(entity.code)}" (
<#list entity.fields  as field>
    <#if field.type?ends_with("Type")>
    "${snake_case(field.code)}" SMALLINT ${field.nullable?then('NULL','NOT NULL')},
    <#else>
    "${snake_case(field.code)}" ${setting('pgsql.type.'+field.type,'pgsql.type.'+field.type)?lower_case} ${field.nullable?then('NULL','NOT NULL') },
    </#if>
    </#list>
    primary key("${(snake_keys)?join('","')}")
);
<#list entity.fields as field>
COMMENT ON COLUMN "${snake_case(entity.code)}"."${snake_case(field.code)}" IS '${pascal_case(field.remark)}';
</#list>

