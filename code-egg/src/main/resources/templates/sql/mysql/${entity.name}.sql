<#if dict('project.drop.table','true')=='true'>
DROP TABLE IF EXISTS `${snake_case(entity.code)}`;
</#if>
CREATE TABLE `${snake_case(entity.code)}` (
<#list entity.fields  as field>
    `${snake_case(field.code)}` ${dict('mssql.type.'+field.type,'mssql.type.'+field.type)?upper_case} ${field.nullable?then('NULL','NOT NULL') } COMMENT '${pascal_case(field.remark)}',
</#list>
<#list entity.fields as field>
    <#if field.primary>
    primary key(`${snake_case(field.code)}`)
    </#if>
</#list>
);