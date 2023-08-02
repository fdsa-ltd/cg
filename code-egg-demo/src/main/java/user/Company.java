package user;

import base.BaseEntity;
import ltd.fdsa.code.annotation.Column;
import ltd.fdsa.code.annotation.Table;

@Table(value = "t_company", remark = "公司表")
public class Company extends BaseEntity {
    @Column(value = "cid", remark = "主键", nullable = false, primary = true)
    Integer id;
    @Column(remark = "名称", length = 32)
    String name;

    //...
    @Column(remark = "备注", length = 32)
    String remark;
}