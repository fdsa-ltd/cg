package file;

import base.BaseEntity;
import ltd.fdsa.code.annotation.Column;
//import ltd.fdsa.code.annotation.Relation;
import ltd.fdsa.code.annotation.Table;
import user.User;

@Table(value = "t_user_file", name = "z", remark = "文件表")
public class UserFile extends BaseEntity {
    @Column(primary = true, value = "file_id", remark = "主键")
    String fileId;

    @Column( value = "blob_id", remark = "文件对象", r = Blob.class,rnm = "blob_id")
    String blobId;
    @Column( value = "user_id", remark = "文件对象", r = User.class,rnm = "user_id")
    String userId;
    @Column(remark = "文件路径，如果以/结尾这目录", value = "path", length = 32)
    String path;
    @Column(remark = "类型，文件的扩展信息", value = "type", length = 32)
    String type;
    @Column(remark = "文件大小", value = "size", length = 8)
    Long size;
    @Column(remark = "图片宽度", value = "width", length = 4)
    Integer width;
    @Column(remark = "图片高度", value = "height", length = 4)
    Integer height;
    @Column(remark = "自定义标签", value = "end_time", length = 1024)
    String tags;
}
