package com.lixuemin.coupon.template.api.common;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券类型
 * */
@Getter
@AllArgsConstructor
public enum CouponType {
    UNKNOWN("unknown", "0"),

    MONEY_OFF("满减券", "1"),

    DISCOUNT("打折", "2"),

    RANDOM_DISCOUNT("随机减", "3"),

    LONELY_NIGHT_MONEY_OFF("晚间双倍优惠券", "4");

    private String description;
    // 存在数据库里的最终code
    private String code;

    /**
     * 根据优惠券编码返回对应的枚举对象，不识别的返回UNKNOWN
     * @param code 优惠券编码
     * @return {@link CouponType}
     * */
    public static CouponType convert(String code) {
        return Stream.of(values()).filter(bean -> bean.code.equalsIgnoreCase(code)).findFirst().orElse(UNKNOWN);
    }
}
