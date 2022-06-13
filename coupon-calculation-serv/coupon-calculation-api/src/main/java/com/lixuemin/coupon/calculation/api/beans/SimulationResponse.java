package com.lixuemin.coupon.calculation.api.beans;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationResponse {
    private Long bestCouponId;

    private Map<Long, Long> couponToOrderPrice = Maps.newHashMap();
}
