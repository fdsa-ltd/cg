package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum GenderType {
    Unknown(0, "Unknown"),
    Male(1, "Male"),
    Female(2, "Female");

    GenderType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GenderType valueOf(int code) {
        for (var item : GenderType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return GenderType.Unknown;
    }

    private final int code;

    private final String name;

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}