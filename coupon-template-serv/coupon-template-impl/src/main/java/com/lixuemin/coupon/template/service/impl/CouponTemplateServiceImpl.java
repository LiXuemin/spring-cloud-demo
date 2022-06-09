package com.lixuemin.coupon.template.service.impl;

import com.lixuemin.coupon.template.api.beans.CouponTemplateInfo;
import com.lixuemin.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.lixuemin.coupon.template.api.beans.TemplateSearchParams;
import com.lixuemin.coupon.template.dao.CouponTemplateDao;
import com.lixuemin.coupon.template.dao.entity.CouponTemplate;
import com.lixuemin.coupon.template.service.CouponTemplateService;
import com.lixuemin.coupon.template.service.mapper.CouponTemplateMapper;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private static final CouponTemplateMapper MAPPER = CouponTemplateMapper.INSTANCE;
    private final CouponTemplateDao couponTemplateDao;

    public CouponTemplateServiceImpl(CouponTemplateDao couponTemplateDao) {
        this.couponTemplateDao = couponTemplateDao;
    }

    // 创建优惠券模板
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        if (request.getShopId() != null) {
            Integer count = couponTemplateDao.countByShopIdAndAvailable(request.getShopId(), true);
            if (count >= 100) {
                log.error("the totals of coupon template exceeds maximum number");
                throw new UnsupportedOperationException("exceeded the maximum of coupon templates that you can create");
            }
        }

        CouponTemplate template = MAPPER.templateDtoToEntity(request);
        couponTemplateDao.save(template);
        return MAPPER.templateEntityToDto(template);
    }

    //复制优惠券模板
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        log.info("Cloning tempalte id {}", templateId);
        final CouponTemplate source = couponTemplateDao.findById(templateId)
            .orElseThrow(() -> new IllegalArgumentException("invalid templateId"));

        final CouponTemplate target = MAPPER.cloneTemplateEntity(source);
        target.setAvailable(true);
        target.setId(null);
        couponTemplateDao.save(target);

        return MAPPER.templateEntityToDto(target);
    }

    //分页查询模板
    public PagedCouponTemplateInfo search(TemplateSearchParams request) {
        final CouponTemplate template = MAPPER.templateSearchParamToEntity(request);
        //example查询
        Pageable page = PageRequest.of(request.getPage(), request.getPageSize());
        final Page<CouponTemplate> result = couponTemplateDao.findAll(Example.of(template), page);

        final List<CouponTemplateInfo> templateInfos = result.stream()
            .map(r -> MAPPER.templateEntityToDto(r))
            .collect(Collectors.toList());

        final PagedCouponTemplateInfo pagedCouponTemplateInfo = PagedCouponTemplateInfo.builder()
            .templates(templateInfos)
            .page(request.getPage())
            .total(result.getTotalElements())
            .build();
        return pagedCouponTemplateInfo;
    }

    //通过模板id查询优惠券模板
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        final Optional<CouponTemplate> template = couponTemplateDao.findById(id);
        return template.isPresent() ? MAPPER.templateEntityToDto(template.get()) : null;
    }

    //将模板无效
    public void deleteTemplate(Long id) {
        log.info("disable template id {}", id);
        final int rows = couponTemplateDao.makeCouponUnavailable(id);
        if (rows == 0) {
            throw new IllegalArgumentException("Template not found : " + id);
        }
    }

    //批量读取模板
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        final List<CouponTemplate> templates = couponTemplateDao.findAllById(ids);
        return templates.stream()
            .map(r -> MAPPER.templateEntityToDto(r))
            .collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }
}
