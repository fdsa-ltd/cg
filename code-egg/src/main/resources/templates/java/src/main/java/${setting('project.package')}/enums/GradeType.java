package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum GradeType {
    None(0, "None"),
    Crystal(1, "Crystal"),
    Sliver(2, "Sliver"),
    Golden(3, "Golden"),
    Black(4, "Black"),
    // Black(5, "Black")
    ;
    GradeType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GradeType valueOf(int code) {
        for (var item : GradeType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return GradeType.None;
    }

    private final int code;
    private final String name;
    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }
}