package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum DynamicFieldType {
    Dictionary(1, "字典（单个）"),
    Tags(4, "标签（多个）"),
    Category(0, "分类"),
    ReferOne(0, "关联"),
    ReferMany(0, "多关联"),
    Others(100, "图片，附件，位置，签名等"),
    ;
    private final int code;
    private final String name;

    DynamicFieldType(int code, String name) {
        this.code = code;
        this.name = name;
    }


    public static DynamicFieldType valueOf(int code) {
        for (var item : DynamicFieldType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return Others;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }

}