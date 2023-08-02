package ltd.fdsa.client.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Data
public class User {

    @JsonProperty("id") 
    private Integer id;
    @JsonProperty("usernm") 
    private String usernm;
    @JsonProperty("mobile_phone") 
    private String mobilePhone;
    @JsonProperty("email_address") 
    private String emailAddress;
    @JsonProperty("password") 
    private String password;
    @JsonProperty("create_time") 
    private Date createTime;
    @JsonProperty("update_time") 
    private Date updateTime;
    @JsonProperty("create_by") 
    private Integer createBy;
    @JsonProperty("update_by") 
    private Integer updateBy;
}
