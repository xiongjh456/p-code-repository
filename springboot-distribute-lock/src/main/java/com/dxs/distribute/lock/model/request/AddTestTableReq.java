package com.dxs.distribute.lock.model.request;

import java.io.Serializable;

/**
 * 添加测试表请求参数实体
 * @className  AddTestTableReq
 * @author  Mr.Xiong
 * @date  2022/08/08
 */
public class AddTestTableReq implements Serializable {
    private static final long serialVersionUID = 2049777286153474096L;
   /** 备注*/
   private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AddTestTableReq{" +
                "remark='" + remark + '\'' +
                '}';
    }
}
