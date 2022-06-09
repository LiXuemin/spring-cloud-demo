package com.lixuemin.coupon.template.service.mapper;

import com.alibaba.fastjson.JSON;
import com.lixuemin.coupon.template.api.beans.CouponTemplateInfo;
import com.lixuemin.coupon.template.api.beans.rules.TemplateRule;
import com.lixuemin.coupon.template.api.common.CouponType;
import com.lixuemin.coupon.template.dao.entity.CouponTemplate;
import org.junit.jupiter.api.Test;


class CouponTemplateMapperTest {
    @Test
    void testEntityToDto(){
        CouponTemplate template = CouponTemplate.builder()
            .name("nnname")
            .description("desc")
            .category(CouponType.DISCOUNT)
            .rule(new TemplateRule())
            .available(true)
            .build();
        final CouponTemplateInfo dto = CouponTemplateMapper.INSTANCE.templateEntityToDto(template);
        System.out.println(JSON.toJSON(dto));

    }

}
