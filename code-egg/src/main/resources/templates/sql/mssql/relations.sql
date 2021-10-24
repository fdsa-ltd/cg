<#list module.relations  as relation>
alter table ${snake_case(relation.entity.code)} drop constraint fk_${snake_case(relation.code)};
alter table ${snake_case(relation.entity.code)} add constraint fk_${snake_case(relation.code)} foreign key( ${snake_case(relation.foreignKey)} )  references ${relation.reference.code}(${relation.primaryKey});
</#list>


 　
