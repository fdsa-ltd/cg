package user;

import base.TenantEntity;
import ltd.fdsa.code.annotation.*;

@Table(value = "t_user_role", remark = "用户的所有角色")
public class UserRole extends TenantEntity {
    @Column(value = "user_role_id", remark = "主键", primary = true)
    Integer id;
    @Column(r = User.class, rtp = Column.Relation.Many2Many, rnm = "uid")
    Integer userId;
    @Column(r = Role.class, rtp = Column.Relation.Many2Many, rnm = "rid")
    Integer roleId;
}

