DROP TABLE IF EXISTS "t_user_role";

CREATE TABLE "t_user_role" (
    "user_role_id" int4 NOT NULL,
    "user_id" int4 NULL,
    "role_id" int4 NULL,
    "cid" int4 NOT NULL,
    "status" pgsql.type.byte NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("user_role_id")
    primary key("cid")
);
COMMENT ON COLUMN "t_user_role"."user_role_id" IS '主键';
COMMENT ON COLUMN "t_user_role"."user_id" IS 'UserId';
COMMENT ON COLUMN "t_user_role"."role_id" IS 'RoleId';
COMMENT ON COLUMN "t_user_role"."cid" IS '主键';
COMMENT ON COLUMN "t_user_role"."status" IS '状态';
COMMENT ON COLUMN "t_user_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_user_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_user_role"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_user_role"."update_by" IS '更新用户';

