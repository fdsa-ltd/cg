package cn.zhumingwu.project.enums;

import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum AllowActionType {
    Query(0B00000001, "查询"),
    Write(0B00000011, "修改"),
    Submit(0B00000111, "提交"),
    SubmitTo(0B00001111, "提交到"),
    Cancel(0B10000001, "撤消"),
    Reject(0B11000001, "拒绝"),
    Approve(0B00010001, "同意"),
    Transfer(0B00110001, "转移"),
    ReturnTo(0B11010001, "回退到");

    private final int code;

    private final String name;
    AllowActionType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static AllowActionType valueOf(int code) {
        for (var item : AllowActionType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return AllowActionType.Approve;
    }
    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }
}
