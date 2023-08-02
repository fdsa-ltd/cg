DROP TABLE IF EXISTS "t_user";

CREATE TABLE "t_user" (
    "uid" int4 NOT NULL,
    "usernm" varchar(255) NULL,
    "mobile_phone" varchar(255) NULL,
    "email_address" varchar(255) NULL,
    "password" varchar(255) NULL,
    "create_time" timestamp NULL,
    "update_time" timestamp NULL,
    "create_by" int4 NULL,
    "update_by" int4 NULL,
    primary key("uid")
);
COMMENT ON COLUMN "t_user"."uid" IS '主键';
COMMENT ON COLUMN "t_user"."usernm" IS '用户名';
COMMENT ON COLUMN "t_user"."mobile_phone" IS '手机号';
COMMENT ON COLUMN "t_user"."email_address" IS '电子邮箱';
COMMENT ON COLUMN "t_user"."password" IS '密码';
COMMENT ON COLUMN "t_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_user"."create_by" IS '创建用户';
COMMENT ON COLUMN "t_user"."update_by" IS '更新用户';

