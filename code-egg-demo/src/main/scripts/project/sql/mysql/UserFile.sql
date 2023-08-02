DROP TABLE IF EXISTS `t_user_file`;
CREATE TABLE `t_user_file` (
    `file_id` NVARCHAR(255) NOT NULL COMMENT '主键',
    `blob_id` NVARCHAR(255) NULL COMMENT '文件对象',
    `user_id` NVARCHAR(255) NULL COMMENT '文件对象',
    `path` NVARCHAR(255) NULL COMMENT '文件路径，如果以/结尾这目录',
    `type` NVARCHAR(255) NULL COMMENT '类型，文件的扩展信息',
    `size` MSSQL.TYPE.LONG NULL COMMENT '文件大小',
    `width` INT32 NULL COMMENT '图片宽度',
    `height` INT32 NULL COMMENT '图片高度',
    `end_time` NVARCHAR(255) NULL COMMENT '自定义标签',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `create_by` INT32 NULL COMMENT '创建用户',
    `update_by` INT32 NULL COMMENT '更新用户',
    primary key(`file_id`)
);