package cn.zhumingwu.project.enums;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum GrantType {
    Implicit(0, "Implicit"),
    Hybrid(1, "Hybrid"),
    AuthorizationCode(2, "AuthorizationCode"), // 最常用，通过网站引导用户授权跳转
    ClientCredentials(3, "ClientCredentials"), // 没有网站时，让用户获取授权码填写
    ResourceOwnerPassword(4, "ResourceOwnerPassword"),
    DeviceFlow(5, "DeviceFlow");

    private final int code;
    private final String name;

    public static GrantType valueOf(int code) {
        for (var item : GrantType.values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return GrantType.ClientCredentials;
    }

    GrantType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", this.code, this.name);
    }
}