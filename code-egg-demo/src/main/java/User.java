import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_user", remark = "用户表")
public class User extends BaseEntity {
    @Column(primary = true)
    Integer userId;
    @Column(remark = "用户名", value = "username", length = 32)
    String userName;
    @Column(remark = "密码", length = 32)
    String password;
}

