CREATE TABLE t_user_login(
    login_id as int32,
    user_id as int32,
    provider as nvarchar(255),
    provider_key as nvarchar(255),
    token as nvarchar(255),
    end_time as nvarchar(255),
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(login_id)
);