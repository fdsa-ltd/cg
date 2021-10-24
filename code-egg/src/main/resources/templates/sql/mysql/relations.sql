<#list module.relations  as relation>
-- ALTER TABLE `${snake_case(relation.entity.code)}` DROP FOREIGN KEY IF EXISTS `fk_${snake_case(relation.code)}`;
ALTER TABLE `${snake_case(relation.entity.code)}` ADD CONSTRAINT `fk_${snake_case(relation.code)}` 
FOREIGN KEY (`${snake_case(relation.foreignKey)}`) references `${snake_case(relation.reference.code)}`(`${snake_case(relation.primaryKey)}`);
</#list>