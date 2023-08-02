CREATE TABLE t_user(
    uid as int32,
    usernm as nvarchar(255),
    mobile_phone as nvarchar(255),
    email_address as nvarchar(255),
    password as nvarchar(255),
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(uid)
);