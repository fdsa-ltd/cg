import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_user", remark = "用户表")
public class User extends BaseEntity {
    @Column(primary = true)
    Integer userId;
    @Column(remark = "用户名", value = "username", length = 32)
    String userName;
    @Column(remark = "手机号", value = "mobile_phone", length = 32)
    String mobilePhone;
    @Column(remark = "电子邮箱", value = "email_address", length = 32)
    String emailAddress;
    @Column(remark = "密码", length = 32)
    String password;
    @Column(remark = "有效始时", value = "from_time", length = 32)
    String fromTime;
    @Column(remark = "有效终时", value = "end_time", length = 32)
    String endTime;
}

