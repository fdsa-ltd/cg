DROP TABLE IF EXISTS "t_file";

CREATE TABLE "t_file" (
    "file_id" int4 NOT NULL,
    "title" varchar(255) NULL,
    "content" varchar(255) NULL,
    "image" varchar(255) NULL,
    "geometry" varchar(255) NULL,
    "from_time" varchar(255) NULL,
    "end_time" varchar(255) NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("file_id")
);
COMMENT ON COLUMN "t_file"."file_id" IS '主键';
COMMENT ON COLUMN "t_file"."title" IS '标题';
COMMENT ON COLUMN "t_file"."content" IS '内容';
COMMENT ON COLUMN "t_file"."image" IS '图片';
COMMENT ON COLUMN "t_file"."geometry" IS '位置';
COMMENT ON COLUMN "t_file"."from_time" IS '有效始时';
COMMENT ON COLUMN "t_file"."end_time" IS '有效终时';
COMMENT ON COLUMN "t_file"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_file"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_file"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_file"."update_by" IS '更新用户';

