CREATE TABLE t_company(
    cid as int32,
    name as nvarchar(255),
    remark as nvarchar(255),
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(cid)
);