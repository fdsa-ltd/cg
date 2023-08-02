package user;

import base.BaseEntity;
import base.TenantEntity;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_role", remark = "角色表")
public class Role extends TenantEntity {
    @Column(value = "rid", remark = "主键", primary = true)
    Integer id;
    @Column(remark = "角色名称", length = 32)
    String name;
}