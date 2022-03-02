import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_user", remark = "用户表")
public class User extends BaseEntity {
    @Column(value = "user_id", remark = "主键", primary = true)
    Integer userId;
    @Column(value = "username", remark = "用户名", length = 32)
    String userName;
    @Column(value = "mobile_phone", remark = "手机号", length = 32)
    String mobilePhone;
    @Column(value = "email_address", remark = "电子邮箱", length = 32)
    String emailAddress;
    @Column(remark = "密码", length = 32)
    String password;
    @Column(value = "from_time", remark = "有效始时", length = 32)
    String fromTime;
    @Column(value = "end_time", remark = "有效终时", length = 32)
    String endTime;
}