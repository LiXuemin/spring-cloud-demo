package com.lixuemin.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 优惠券信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponInfo {
    private Long id;

    private Long templateId;

    private Long userId;

    private Long shopId;

    private Integer status;

    private CouponTemplateInfo template;
}
