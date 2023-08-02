CREATE TABLE t_blob(
    blob_id as nvarchar(255),
    type as nvarchar(255),
    size as mssql.type.Long,
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(blob_id)
);