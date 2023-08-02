DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
    `uid` INT32 NOT NULL COMMENT '主键',
    `usernm` NVARCHAR(255) NULL COMMENT '用户名',
    `mobile_phone` NVARCHAR(255) NULL COMMENT '手机号',
    `email_address` NVARCHAR(255) NULL COMMENT '电子邮箱',
    `password` NVARCHAR(255) NULL COMMENT '密码',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `create_by` INT32 NULL COMMENT '创建用户',
    `update_by` INT32 NULL COMMENT '更新用户',
    primary key(`uid`)
);