package ltd.fdsa.client.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Data
public class UserLogin {

    @JsonProperty("id") 
    private Integer Id;
    @JsonProperty("user_id") 
    private Integer userId;
    @JsonProperty("provider") 
    private String provider;
    @JsonProperty("key") 
    private String key;
    @JsonProperty("token") 
    private String token;
    @JsonProperty("end_time") 
    private String endTime;
    @JsonProperty("create_time") 
    private Date createTime;
    @JsonProperty("update_time") 
    private Date updateTime;
    @JsonProperty("create_by") 
    private Integer createBy;
    @JsonProperty("update_by") 
    private Integer updateBy;
}
