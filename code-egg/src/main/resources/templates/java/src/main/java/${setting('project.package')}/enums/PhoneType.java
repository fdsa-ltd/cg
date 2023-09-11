package cn.zhumingwu.project.enums;

import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum PhoneType {
    Mobile(0, "mobile phone"),
    Telephone(1, "telephone");

    PhoneType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PhoneType valueOf(int code) {
        for (var item : PhoneType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return PhoneType.Mobile;
    }

    private final int code;

    private final String name;

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}

