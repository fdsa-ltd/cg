import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_role", remark = "角色表")
public class Role extends BaseEntity {
    @Column(value = "role_id", nullable = false, primary = true)
    Integer roleId;
    @Column(remark = "角色名称", length = 32)
    String name;
}

