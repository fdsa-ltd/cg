CREATE TABLE t_role(
    rid as int32,
    name as nvarchar(255),
    cid as int32,
    status as mssql.type.Byte,
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(rid)
    primary key(cid)
);