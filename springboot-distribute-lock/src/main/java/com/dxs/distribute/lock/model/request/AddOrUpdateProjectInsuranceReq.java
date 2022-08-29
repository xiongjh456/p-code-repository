package com.dxs.distribute.lock.model.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 添加/更新项目保险请求参数实体
 * @className  AddOrUpdateProjectInsuranceReq
 * @author  Mr.Xiong
 * @date  2022/08/08
 */
public class AddOrUpdateProjectInsuranceReq implements Serializable {
    private static final long serialVersionUID = 2049777286153474096L;
    /** id*/
   private Integer id;
   /** 缴纳类型*/
   private String paymentType;
   /** 办理保险花费金额（元）*/
   private String spentAmount;
   /** 保险公司名称*/
   private String insuranceCompanyName;
   /** 保险公司代码*/
   private String insuranceCompanyCode;
   /** 保险凭证*/
   private String insuranceCertificate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentType() {
       return paymentType;
   }

   public void setPaymentType(String paymentType) {
       this.paymentType = paymentType;
   }

   public String getSpentAmount() {
       return spentAmount;
   }

   public void setSpentAmount(String spentAmount) {
       this.spentAmount = spentAmount;
   }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public String getInsuranceCompanyCode() {
        return insuranceCompanyCode;
    }

    public void setInsuranceCompanyCode(String insuranceCompanyCode) {
        this.insuranceCompanyCode = insuranceCompanyCode;
    }

    public String getInsuranceCertificate() {
       return insuranceCertificate;
   }

   public void setInsuranceCertificate(String insuranceCertificate) {
       this.insuranceCertificate = insuranceCertificate;
   }

    @Override
    public String toString() {
        return "AddOrUpdateProjectInsuranceReq{" +
                "id=" + id +
                ", paymentType='" + paymentType + '\'' +
                ", spentAmount='" + spentAmount + '\'' +
                ", insuranceCompanyName='" + insuranceCompanyName + '\'' +
                ", insuranceCompanyCode='" + insuranceCompanyCode + '\'' +
                ", insuranceCertificate='" + insuranceCertificate + '\'' +
                '}';
    }
}
