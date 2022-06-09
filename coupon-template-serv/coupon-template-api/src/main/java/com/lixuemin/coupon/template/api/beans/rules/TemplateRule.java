package com.lixuemin.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 1. 领券规则
 * 2. 券模板的计算规则
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {
    //可以享受的折扣
    private Discount discount;
    //每人最多可以领券数量
    private Integer limitation;
    //过期时间
    private Long deadline;

}
