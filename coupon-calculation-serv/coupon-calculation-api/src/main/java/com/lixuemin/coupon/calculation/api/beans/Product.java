package com.lixuemin.coupon.calculation.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
* 商品信息
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    //商品价格
    private Long price;
    //商品在购物车的数量
    //只有标品才能以整数来计算商品数量，如蔬菜肉类等非标品计数单位并不是“个”，实际项目中计数单位要允许小数位存在，这里demo做了简化
    private Integer count;
    //商品销售的门店
    private Long shopId;
}
