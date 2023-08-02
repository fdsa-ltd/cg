package base;

import ltd.fdsa.code.annotation.Column;
import user.Company;

import java.util.Date;

/**
 * @Description：定义基本的属性
 * @Author：朱明武
 * @Date：2022/6/16/10:00
 * @Version：1.0
 */
public class TenantEntity extends BaseEntity {
    @Column(value = "cid", remark = "主键", primary = true ,r= Company.class,rid="cid")
    Integer cid;
    @Column(remark = "状态", value = "status")
    Byte status;
}
