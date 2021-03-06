package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseTrademark;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BaseTrademarkService extends IService<BaseTrademark> {

    //查询所有品牌数据
    //业务层中获取数据，get，Mapper层 select
    IPage<BaseTrademark> getPage(Page<BaseTrademark> page);


}
