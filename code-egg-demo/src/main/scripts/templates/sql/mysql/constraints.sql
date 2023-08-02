<#list module.relations  as relation>
-- ALTER TABLE `${snake_case(relation.target.code)}` DROP FOREIGN KEY IF EXISTS `fk_${snake_case(relation.code)}`;
ALTER TABLE `${snake_case(relation.target.code)}` ADD CONSTRAINT `fk_${snake_case(relation.code)}` 
FOREIGN KEY (`${snake_case(relation.foreignKey)}`) references `${snake_case(relation.source.code)}`(`${snake_case(relation.primaryKey)}`);
</#list>