DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
    `user_role_id` INT32 NOT NULL COMMENT '主键',
    `user_id` INT32 NULL COMMENT 'UserId',
    `role_id` INT32 NULL COMMENT 'RoleId',
    `cid` INT32 NOT NULL COMMENT '主键',
    `status` MSSQL.TYPE.BYTE NULL COMMENT '状态',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `create_by` INT32 NULL COMMENT '创建用户',
    `update_by` INT32 NULL COMMENT '更新用户',
    primary key(`user_role_id`)
    primary key(`cid`)
);