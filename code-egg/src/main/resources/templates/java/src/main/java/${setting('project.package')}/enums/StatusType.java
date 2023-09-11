package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum StatusType {
    Active(1, "Active"),
    InActive(0, "InActive"),
    Deleted(-1, "Deleted");
    StatusType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StatusType valueOf(int code) {
        for (var item : StatusType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return InActive;
    }

    private final int code;
    private final String name;

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}