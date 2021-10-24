import ltd.fdsa.code.IEntity;
import ltd.fdsa.code.annotation.Column;

import java.util.Date;

public class BaseEntity implements IEntity{
    @Column(remark = "创建时间",value = "create_time")
    Date createTime;
    @Column(remark = "更新时间",value = "update_time")
    Date updateTime;
    @Column(remark = "创建用户",value = "create_by")
    Integer createBy;
    @Column(remark = "更新用户",value = "update_by")
    Integer updateBy;
 }

