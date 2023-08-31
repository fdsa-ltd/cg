package user;

import base.BaseEntity;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.var;
import ltd.fdsa.code.annotation.*;

@Table(value = "t_user", remark = "用户表")
public class User extends BaseEntity {
    @Column(value = "uid", remark = "主键", primary = true)
    Integer id;
    @Column(value = "usernm", remark = "用户名", length = 32)
    String username;
    @Column(value = "mobile_phone", remark = "手机号", length = 32)
    String mobilePhone;
    @Column(value = "email_address", remark = "电子邮箱", length = 32)
    String emailAddress;
    @Column(remark = "密码", length = 32)
    String password;

    @Column()
    GenderType gender;


    @Getter
    public enum GenderType {
        Unknown(0, "Unknown"),
        Male(1, "Male"),
        Female(2, "Female");

        GenderType(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static GenderType valueOf(int code) {
            for (var item : GenderType.values()) {
                if (Objects.equal(item.getCode(), code)) {
                    return item;
                }
            }
            return GenderType.Unknown;
        }

        private final int code;

        private final String name;

        @Override
        public String toString() {
            return this.code + ":" + this.name;
        }
    }
}