DROP TABLE IF EXISTS "t_role";

CREATE TABLE "t_role" (
    "rid" int4 NOT NULL,
    "name" varchar(255) NULL,
    "cid" int4 NOT NULL,
    "status" pgsql.type.byte NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("rid")
    primary key("cid")
);
COMMENT ON COLUMN "t_role"."rid" IS '主键';
COMMENT ON COLUMN "t_role"."name" IS '角色名称';
COMMENT ON COLUMN "t_role"."cid" IS '主键';
COMMENT ON COLUMN "t_role"."status" IS '状态';
COMMENT ON COLUMN "t_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_role"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_role"."update_by" IS '更新用户';

