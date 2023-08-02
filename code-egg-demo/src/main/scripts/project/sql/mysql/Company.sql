DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
    `cid` INT32 NOT NULL COMMENT '主键',
    `name` NVARCHAR(255) NULL COMMENT '名称',
    `remark` NVARCHAR(255) NULL COMMENT '备注',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `create_by` INT32 NULL COMMENT '创建用户',
    `update_by` INT32 NULL COMMENT '更新用户',
    primary key(`cid`)
);