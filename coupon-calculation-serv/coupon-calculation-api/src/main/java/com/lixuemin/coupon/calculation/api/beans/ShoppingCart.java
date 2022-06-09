package com.lixuemin.coupon.calculation.api.beans;

import com.lixuemin.coupon.template.api.beans.CouponInfo;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 封装订单消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    //订单商品列表
    @NotEmpty
    private List<Product> products;

    //封装优惠券信息，目前仅支持单张优惠券
    //考虑到以后的扩展性，此处定义了list
    private Long couponId;
    private List<CouponInfo> couponInfos;

    //订单最终价格
    private Long cost;

    @NotNull
    private Long userId;
}
