package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum RoleType {
    Default(0, "默认"),
    Support(1, "岗位"),
    Admin(100, "职务");

    RoleType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static RoleType valueOf(int code) {
        for (var item : RoleType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return RoleType.Default;
    }

    private final int code;
    private final String name;

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}
