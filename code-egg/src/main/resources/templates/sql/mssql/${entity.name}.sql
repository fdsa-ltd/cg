CREATE TABLE ${snake_case(entity.code)}(
<#list entity.fields as field>
    ${snake_case(field.code)} as ${dict('mssql.type.'+field.type,'mssql.type.'+field.type)},
</#list>
<#list entity.fields as field>
    <#if field.primary>
    primary key(${snake_case(field.code)})
    </#if>
</#list>
);