DROP TABLE IF EXISTS "t_user_file";

CREATE TABLE "t_user_file" (
    "file_id" varchar(255) NOT NULL,
    "blob_id" varchar(255) NULL,
    "user_id" varchar(255) NULL,
    "path" varchar(255) NULL,
    "type" varchar(255) NULL,
    "size" pgsql.type.long NULL,
    "width" int4 NULL,
    "height" int4 NULL,
    "end_time" varchar(255) NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("file_id")
);
COMMENT ON COLUMN "t_user_file"."file_id" IS '主键';
COMMENT ON COLUMN "t_user_file"."blob_id" IS '文件对象';
COMMENT ON COLUMN "t_user_file"."user_id" IS '文件对象';
COMMENT ON COLUMN "t_user_file"."path" IS '文件路径，如果以/结尾这目录';
COMMENT ON COLUMN "t_user_file"."type" IS '类型，文件的扩展信息';
COMMENT ON COLUMN "t_user_file"."size" IS '文件大小';
COMMENT ON COLUMN "t_user_file"."width" IS '图片宽度';
COMMENT ON COLUMN "t_user_file"."height" IS '图片高度';
COMMENT ON COLUMN "t_user_file"."end_time" IS '自定义标签';
COMMENT ON COLUMN "t_user_file"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_user_file"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_user_file"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_user_file"."update_by" IS '更新用户';

