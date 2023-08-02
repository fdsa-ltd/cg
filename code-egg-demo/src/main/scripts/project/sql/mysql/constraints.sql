-- ALTER TABLE `t_user_role` DROP FOREIGN KEY IF EXISTS `fk_id`;
ALTER TABLE `t_user_role` ADD CONSTRAINT `fk_id` 
FOREIGN KEY (`user_id`) references `t_user`(`id`);
-- ALTER TABLE `t_user_role` DROP FOREIGN KEY IF EXISTS `fk_id`;
ALTER TABLE `t_user_role` ADD CONSTRAINT `fk_id` 
FOREIGN KEY (`role_id`) references `t_role`(`id`);
-- ALTER TABLE `t_user_role` DROP FOREIGN KEY IF EXISTS `fk_cid`;
ALTER TABLE `t_user_role` ADD CONSTRAINT `fk_cid` 
FOREIGN KEY (`cid`) references `t_company`(`cid`);
