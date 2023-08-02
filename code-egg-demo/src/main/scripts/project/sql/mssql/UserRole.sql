CREATE TABLE t_user_role(
    user_role_id as int32,
    user_id as int32,
    role_id as int32,
    cid as int32,
    status as mssql.type.Byte,
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(user_role_id)
    primary key(cid)
);