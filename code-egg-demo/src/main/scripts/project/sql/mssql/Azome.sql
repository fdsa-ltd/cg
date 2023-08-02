CREATE TABLE t_zone(
    file_id as int32,
    title as nvarchar(255),
    content as nvarchar(255),
    image as nvarchar(255),
    geometry as nvarchar(255),
    from_time as nvarchar(255),
    end_time as nvarchar(255),
    create_time as datetime,
    update_time as datetime,
    create_by as int32,
    update_by as int32,
    primary key(file_id)
);