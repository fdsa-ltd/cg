DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
    `file_id` INT32 NOT NULL COMMENT '主键',
    `title` NVARCHAR(255) NULL COMMENT '标题',
    `content` NVARCHAR(255) NULL COMMENT '内容',
    `image` NVARCHAR(255) NULL COMMENT '图片',
    `geometry` NVARCHAR(255) NULL COMMENT '位置',
    `from_time` NVARCHAR(255) NULL COMMENT '有效始时',
    `end_time` NVARCHAR(255) NULL COMMENT '有效终时',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `create_by` INT32 NULL COMMENT '创建用户',
    `update_by` INT32 NULL COMMENT '更新用户',
    primary key(`file_id`)
);