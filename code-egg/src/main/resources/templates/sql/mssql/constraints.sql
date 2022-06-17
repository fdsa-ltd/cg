<#list module.relations  as relation>
alter table ${snake_case(relation.target.code)} drop constraint fk_${snake_case(relation.code)};
alter table ${snake_case(relation.target.code)} add constraint fk_${snake_case(relation.code)} foreign key( ${snake_case(relation.foreignKey)} )  references ${relation.source.code}(${relation.primaryKey});
</#list>