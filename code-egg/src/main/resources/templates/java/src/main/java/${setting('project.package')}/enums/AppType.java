package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum AppType {
    Blank(0, "Blank"),
    Self(1, "Self"),
    Iframe(2, "Iframe"),
    MicroWeb(3, "MicroWeb"),
    Client(4, "Client");
    private final int code;
    private final String name;

    AppType(int code, String name) {
        this.code = code;
        this.name = name;
    }


    public static AppType valueOf(int code) {
        for (var item : AppType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return Blank;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}