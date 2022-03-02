import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_post", remark = "递送表")
public class Post extends BaseEntity {
    @Column(primary = true, value = "post_id", remark = "主键")
    Integer userId;
    @Column(remark = "标题", value = "title", length = 32)
    String userName;
    @Column(remark = "内容", value = "content", length = 32)
    String mobilePhone;
    @Column(remark = "图片", value = "image", length = 32)
    String emailAddress;
    @Column(remark = "位置", value = "geometry", length = 32)
    String password;
    @Column(remark = "有效始时", value = "from_time", length = 32)
    String fromTime;
    @Column(remark = "有效终时", value = "end_time", length = 32)
    String endTime;



}

