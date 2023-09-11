package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum DynamicFormType {
    CREATE(0, "Create"),
    EDIT(1, "Edit"),
    Approve(4, "Approve");
    private final int code;
    private final String name;

    DynamicFormType(int code, String name) {
        this.code = code;
        this.name = name;
    }


    public static DynamicFormType valueOf(int code) {
        for (var item : DynamicFormType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return CREATE;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}