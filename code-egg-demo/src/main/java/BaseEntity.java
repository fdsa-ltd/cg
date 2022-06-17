import ltd.fdsa.code.IEntity;
import ltd.fdsa.code.annotation.Column;

import java.util.Date;

/**
 * @Description：定义基本的属性
 * @Author：朱明武
 * @Date：2022/6/16/10:00
 * @Version：1.0
 */
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