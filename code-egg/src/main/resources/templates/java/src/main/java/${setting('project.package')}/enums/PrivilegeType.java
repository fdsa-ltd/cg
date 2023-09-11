package cn.zhumingwu.project.enums;

import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum PrivilegeType {
    Page(0, "前端路由"),
    Api(1, "后端接口"),
    Data(2, "数据表"),
    Other(100, "其它"),
    ;

    PrivilegeType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PrivilegeType valueOf(int code) {
        for (var item : PrivilegeType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return PrivilegeType.Page;
    }

    private final int code;

    private final String name;

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }
}