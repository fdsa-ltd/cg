DROP TABLE IF EXISTS "t_company";

CREATE TABLE "t_company" (
    "cid" int4 NOT NULL,
    "name" varchar(255) NULL,
    "remark" varchar(255) NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("cid")
);
COMMENT ON COLUMN "t_company"."cid" IS '主键';
COMMENT ON COLUMN "t_company"."name" IS '名称';
COMMENT ON COLUMN "t_company"."remark" IS '备注';
COMMENT ON COLUMN "t_company"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_company"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_company"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_company"."update_by" IS '更新用户';

