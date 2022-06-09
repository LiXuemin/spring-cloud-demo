package com.lixuemin.coupon.template.service.mapper;

import com.lixuemin.coupon.template.api.beans.CouponTemplateInfo;
import com.lixuemin.coupon.template.api.beans.TemplateSearchParams;
import com.lixuemin.coupon.template.dao.entity.CouponTemplate;
import java.util.List;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CouponTemplateMapper {

    CouponTemplateMapper INSTANCE = Mappers.getMapper(CouponTemplateMapper.class);

    CouponTemplate templateDtoToEntity(CouponTemplateInfo info);


    CouponTemplateInfo templateEntityToDto(CouponTemplate template);

    CouponTemplate cloneTemplateEntity(CouponTemplate template);

    CouponTemplate templateSearchParamToEntity(TemplateSearchParams params);
}
