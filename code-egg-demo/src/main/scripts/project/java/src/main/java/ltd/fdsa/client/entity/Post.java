package ltd.fdsa.client.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Data
public class Post {

    @JsonProperty("id") 
    private Integer Id;
    @JsonProperty("title") 
    private String title;
    @JsonProperty("content") 
    private String content;
    @JsonProperty("image") 
    private String image;
    @JsonProperty("geometry") 
    private String geometry;
    @JsonProperty("from_time") 
    private String fromTime;
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
