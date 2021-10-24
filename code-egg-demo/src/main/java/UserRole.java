import ltd.fdsa.code.IEntity;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Relation;
import ltd.fdsa.code.annotation.Table;
import ltd.fdsa.code.model.Association;

@Table(value = "t_user_role",remark = "用户的所有角色")
public class UserRole implements IEntity {
    @Column(primary = true)
    Integer userRoleId;
    @Relation(entity = User.class, type = Association.Type.Many2Many, field = "user_id")
    Integer userId;
    @Relation(entity = Role.class, type = Association.Type.Many2Many, field = "role_id")
    Integer roleId;
}

