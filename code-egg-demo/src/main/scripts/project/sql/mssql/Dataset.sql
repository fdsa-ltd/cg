CREATE TABLE t_user_dataset(
    dataset_id as nvarchar(255),
    user_id as nvarchar(255),
    path as nvarchar(255),
    type as nvarchar(255),
    size as mssql.type.Long,
    width as int32,
    height as int32,
    end_time as nvarchar(255),
    cid as int32,
    status as mssql.type.Byte,
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(dataset_id)
    primary key(cid)
);