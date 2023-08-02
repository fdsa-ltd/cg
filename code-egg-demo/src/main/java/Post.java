import base.BaseEntity;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_post", remark = "递送表")
public class Post extends BaseEntity {
    @Column(primary = true, value = "post_id", remark = "主键")
    Integer Id;
    @Column(remark = "标题", value = "title", length = 32)
    String title;
    @Column(remark = "内容", value = "content", length = 32)
    String content;
    @Column(remark = "图片", value = "image", length = 32)
    String image;
    @Column(remark = "位置", value = "geometry", length = 32)
    String geometry;
    @Column(remark = "有效始时", value = "from_time", length = 32)
    String fromTime;
    @Column(remark = "有效终时", value = "end_time", length = 32)
    String endTime;
}

