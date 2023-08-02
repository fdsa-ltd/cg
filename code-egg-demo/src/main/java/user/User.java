package user;

import base.BaseEntity;
import ltd.fdsa.code.annotation.*;

@Table(value = "t_user", remark = "用户表")
public class User extends BaseEntity {
    @Column(value = "uid", remark = "主键", primary = true)
    Integer id;
    @Column(value = "usernm", remark = "用户名", length = 32)
    String usernm;
    @Column(value = "mobile_phone", remark = "手机号", length = 32)
    String mobilePhone;
    @Column(value = "email_address", remark = "电子邮箱", length = 32)
    String emailAddress;
    @Column(remark = "密码", length = 32)
    String password;
}