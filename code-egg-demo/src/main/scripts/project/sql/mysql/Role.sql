DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
    `rid` INT32 NOT NULL COMMENT '主键',
    `name` NVARCHAR(255) NULL COMMENT '角色名称',
    `cid` INT32 NOT NULL COMMENT '主键',
    `status` MSSQL.TYPE.BYTE NULL COMMENT '状态',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `create_by` INT32 NULL COMMENT '创建用户',
    `update_by` INT32 NULL COMMENT '更新用户',
    primary key(`rid`)
    primary key(`cid`)
);