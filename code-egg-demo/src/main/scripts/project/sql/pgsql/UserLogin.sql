DROP TABLE IF EXISTS "t_user_login";

CREATE TABLE "t_user_login" (
    "login_id" int4 NOT NULL,
    "user_id" int4 NULL,
    "provider" varchar(255) NULL,
    "provider_key" varchar(255) NULL,
    "token" varchar(255) NULL,
    "end_time" varchar(255) NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("login_id")
);
COMMENT ON COLUMN "t_user_login"."login_id" IS '主键';
COMMENT ON COLUMN "t_user_login"."user_id" IS 'UserId';
COMMENT ON COLUMN "t_user_login"."provider" IS '提供者:phone,email,others...';
COMMENT ON COLUMN "t_user_login"."provider_key" IS '主键';
COMMENT ON COLUMN "t_user_login"."token" IS '口令';
COMMENT ON COLUMN "t_user_login"."end_time" IS '有效终时';
COMMENT ON COLUMN "t_user_login"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_user_login"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_user_login"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_user_login"."update_by" IS '更新用户';

