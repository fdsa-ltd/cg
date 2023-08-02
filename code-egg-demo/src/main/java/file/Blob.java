package file;

import base.BaseEntity;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_blob", remark = "文件对象")
public class Blob extends BaseEntity {
    @Column(primary = true, value = "blob_id", remark = "主键，基于内容的hash and sha1", length = 42)
    String fileId;
    @Column(remark = "类型，文件的扩展信息", value = "type", length = 32)
    String type;
    @Column(remark = "文件大小", value = "size", length = 8)
    Long size;
}
