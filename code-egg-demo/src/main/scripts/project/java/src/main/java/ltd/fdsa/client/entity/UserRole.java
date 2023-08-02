package ltd.fdsa.client.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Data
public class UserRole {

    @JsonProperty("id") 
    private Integer id;
    @JsonProperty("user_id") 
    private Integer userId;
    @JsonProperty("role_id") 
    private Integer roleId;
    @JsonProperty("cid") 
    private Integer cid;
    @JsonProperty("status") 
    private Byte status;
    @JsonProperty("create_time") 
    private Date createTime;
    @JsonProperty("update_time") 
    private Date updateTime;
    @JsonProperty("create_by") 
    private Integer createBy;
    @JsonProperty("update_by") 
    private Integer updateBy;
}
