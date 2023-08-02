DROP TABLE IF EXISTS "t_blob";

CREATE TABLE "t_blob" (
    "blob_id" varchar(255) NOT NULL,
    "type" varchar(255) NULL,
    "size" pgsql.type.long NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("blob_id")
);
COMMENT ON COLUMN "t_blob"."blob_id" IS '主键，基于内容的hash and sha1';
COMMENT ON COLUMN "t_blob"."type" IS '类型，文件的扩展信息';
COMMENT ON COLUMN "t_blob"."size" IS '文件大小';
COMMENT ON COLUMN "t_blob"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_blob"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_blob"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_blob"."update_by" IS '更新用户';

