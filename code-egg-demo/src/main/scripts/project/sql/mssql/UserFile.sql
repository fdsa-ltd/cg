CREATE TABLE t_user_file(
    file_id as nvarchar(255),
    blob_id as nvarchar(255),
    user_id as nvarchar(255),
    path as nvarchar(255),
    type as nvarchar(255),
    size as mssql.type.Long,
    width as int32,
    height as int32,
    end_time as nvarchar(255),
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(file_id)
);