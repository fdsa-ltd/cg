DROP TABLE IF EXISTS "t_post";

CREATE TABLE "t_post" (
    "post_id" int4 NOT NULL,
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
    primary key("post_id")
);
COMMENT ON COLUMN "t_post"."post_id" IS '主键';
COMMENT ON COLUMN "t_post"."title" IS '标题';
COMMENT ON COLUMN "t_post"."content" IS '内容';
COMMENT ON COLUMN "t_post"."image" IS '图片';
COMMENT ON COLUMN "t_post"."geometry" IS '位置';
COMMENT ON COLUMN "t_post"."from_time" IS '有效始时';
COMMENT ON COLUMN "t_post"."end_time" IS '有效终时';
COMMENT ON COLUMN "t_post"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_post"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_post"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_post"."update_by" IS '更新用户';

