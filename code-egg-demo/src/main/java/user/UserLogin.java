package user;

import base.BaseEntity;
import ltd.fdsa.code.annotation.*;
import ltd.fdsa.code.model.Association;

@Table(value = "t_user_login", remark = "用户登录表")
public class UserLogin extends BaseEntity {

    @Column(value = "login_id", remark = "主键", primary = true)
    Integer Id;
    @Column(r = User.class, rtp = Column.Relation.Many2Many , rnm = "uid")
    Integer userId;
    @Column(value = "provider", remark = "提供者:phone,email,others...", length = 32)
    String provider;
    @Column(value = "provider_key", remark = "主键", length = 32)
    String key;
    @Column(value = "token", remark = "口令", length = 32)
    String token;

    @Column(value = "end_time", remark = "有效终时", length = 32)
    String endTime;
}