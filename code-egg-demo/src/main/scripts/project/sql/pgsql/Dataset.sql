DROP TABLE IF EXISTS "t_user_dataset";

CREATE TABLE "t_user_dataset" (
    "dataset_id" varchar(255) NOT NULL,
    "user_id" varchar(255) NULL,
    "path" varchar(255) NULL,
    "type" varchar(255) NULL,
    "size" pgsql.type.long NULL,
    "width" int4 NULL,
    "height" int4 NULL,
    "end_time" varchar(255) NULL,
    "cid" int4 NOT NULL,
    "status" pgsql.type.byte NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("dataset_id")
    primary key("cid")
);
COMMENT ON COLUMN "t_user_dataset"."dataset_id" IS '主键';
COMMENT ON COLUMN "t_user_dataset"."user_id" IS '文件对象';
COMMENT ON COLUMN "t_user_dataset"."path" IS '文件路径，如果以/结尾这目录';
COMMENT ON COLUMN "t_user_dataset"."type" IS '类型，文件的扩展信息';
COMMENT ON COLUMN "t_user_dataset"."size" IS '文件大小';
COMMENT ON COLUMN "t_user_dataset"."width" IS '图片宽度';
COMMENT ON COLUMN "t_user_dataset"."height" IS '图片高度';
COMMENT ON COLUMN "t_user_dataset"."end_time" IS '自定义标签';
COMMENT ON COLUMN "t_user_dataset"."cid" IS '主键';
COMMENT ON COLUMN "t_user_dataset"."status" IS '状态';
COMMENT ON COLUMN "t_user_dataset"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_user_dataset"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_user_dataset"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_user_dataset"."update_by" IS '更新用户';

