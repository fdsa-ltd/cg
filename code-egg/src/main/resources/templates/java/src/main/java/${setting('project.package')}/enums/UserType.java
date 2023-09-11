package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum UserType {
    User(0, "User"),
    Support(1, "Support"),
    Admin(100, "Admin");

    UserType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserType valueOf(int code) {
        for (var item : UserType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return UserType.User;
    }

    private final int code;
    private final String name;

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}