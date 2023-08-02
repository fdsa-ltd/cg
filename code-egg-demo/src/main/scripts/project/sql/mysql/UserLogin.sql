DROP TABLE IF EXISTS `t_user_login`;
CREATE TABLE `t_user_login` (
    `login_id` INT32 NOT NULL COMMENT '主键',
    `user_id` INT32 NULL COMMENT 'UserId',
    `provider` NVARCHAR(255) NULL COMMENT '提供者:phone,email,others...',
    `provider_key` NVARCHAR(255) NULL COMMENT '主键',
    `token` NVARCHAR(255) NULL COMMENT '口令',
    `end_time` NVARCHAR(255) NULL COMMENT '有效终时',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `create_by` INT32 NULL COMMENT '创建用户',
    `update_by` INT32 NULL COMMENT '更新用户',
    primary key(`login_id`)
);