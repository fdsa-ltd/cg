DROP TABLE IF EXISTS `t_blob`;
CREATE TABLE `t_blob` (
    `blob_id` NVARCHAR(255) NOT NULL COMMENT '主键，基于内容的hash and sha1',
    `type` NVARCHAR(255) NULL COMMENT '类型，文件的扩展信息',
    `size` MSSQL.TYPE.LONG NULL COMMENT '文件大小',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `create_by` INT32 NULL COMMENT '创建用户',
    `update_by` INT32 NULL COMMENT '更新用户',
    primary key(`blob_id`)
);