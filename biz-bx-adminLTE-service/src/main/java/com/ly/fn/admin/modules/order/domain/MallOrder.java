package com.ly.fn.admin.modules.order.domain;

import com.ly.fn.admin.common.domain.BaseDomain;

import java.math.BigDecimal;

/**
 * @package：com.ly.fn.admin.modules.order.domain
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2017/11/16-17:52
 */
public class MallOrder extends BaseDomain {
    private String orderCode;
    private Integer orderStatus;
    private BigDecimal orderFee;
    private String productName;
    private String productCode;
    private String planName;
    private String holderName;
    private String holderPhone;
    private String safeguardStartTime;
    private String safeguardEndTime;
    private Integer orderType;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(BigDecimal orderFee) {
        this.orderFee = orderFee;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderPhone() {
        return holderPhone;
    }

    public void setHolderPhone(String holderPhone) {
        this.holderPhone = holderPhone;
    }

    public String getSafeguardStartTime() {
        return safeguardStartTime;
    }

    public void setSafeguardStartTime(String safeguardStartTime) {
        this.safeguardStartTime = safeguardStartTime;
    }

    public String getSafeguardEndTime() {
        return safeguardEndTime;
    }

    public void setSafeguardEndTime(String safeguardEndTime) {
        this.safeguardEndTime = safeguardEndTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
