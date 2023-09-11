package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum AllowUserType {
    All(0, "所有用户"),
    StaffOnly(1, "所有员工"),
    GroupOnly(2, "指定小组"),
    DepartmentOnly(4, "指定部门"),
    RolesOnly(8, "指定角色"),
    LeaderOnly(16, "直接领导"),
    DepartmentLeaderOnly(32, "部门领导"),
    CreatorSpecify(64, "发起人指定"),
    Creator(128, "发起人指定");

    AllowUserType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static AllowUserType valueOf(int code) {
        for (var item : AllowUserType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return AllowUserType.All;
    }

    private final int code;

    private final String name;

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}
