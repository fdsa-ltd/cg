package ltd.fdsa.client.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Data
public class Dataset {

    @JsonProperty("file_id") 
    private String fileId;
    @JsonProperty("user_id") 
    private String userId;
    @JsonProperty("path") 
    private String path;
    @JsonProperty("type") 
    private String type;
    @JsonProperty("size") 
    private Long size;
    @JsonProperty("width") 
    private Integer width;
    @JsonProperty("height") 
    private Integer height;
    @JsonProperty("tags") 
    private String tags;
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
