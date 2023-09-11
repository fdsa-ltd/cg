package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum ClientType {
    Unknown(0, "Unknown"),
    Client(1, "Client"),
    Company(2, "Company"),
    Government(3, "Government");

    ClientType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ClientType valueOf(int code) {
        for (var item : ClientType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return ClientType.Unknown;
    }

    private final int code;

    private final String name;
    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}