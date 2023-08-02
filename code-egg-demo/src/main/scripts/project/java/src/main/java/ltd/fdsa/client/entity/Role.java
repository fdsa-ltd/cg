package ltd.fdsa.client.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Data
public class Role {

    @JsonProperty("id") 
    private Integer id;
    @JsonProperty("name") 
    private String name;
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
