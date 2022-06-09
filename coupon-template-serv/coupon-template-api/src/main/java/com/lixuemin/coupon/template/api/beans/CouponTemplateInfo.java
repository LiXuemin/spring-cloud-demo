package com.lixuemin.coupon.template.api.beans;

import com.lixuemin.coupon.template.api.beans.rules.TemplateRule;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 优惠券模板
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponTemplateInfo {
    private Long id;

    @NotNull
    private String name; //优惠券名称

    @NotNull
    private String desc;//优惠券描述

    @NotNull
    private String type;//优惠券类型(引用CouponType里的code)

    private Long shopId;//优惠券适用门店 - 若无则为全店通用券

    @NotNull
    private TemplateRule rule; //优惠券使用规则

    private Boolean available; //状态位，当前模板是否可用



}
