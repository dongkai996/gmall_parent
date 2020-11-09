package com.atguigu.gmall.item.service;

import java.util.Map;

public interface ItemService {

    //数据接口重点做汇总
    Map<String,Object> getBySkuId(Long skuId);
}
