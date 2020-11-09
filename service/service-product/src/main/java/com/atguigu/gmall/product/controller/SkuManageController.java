package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台sku数据接口测试")
@RestController
@RequestMapping("admin/product")
public class SkuManageController {

    //http://api.gmall.com/admin/product/saveSkuInfo
    @Autowired
    private ManageService manageService;

    @PostMapping("saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

    @GetMapping("list/{page}/{limit}")
    public Result getSkuInfoList(@PathVariable Long page,
                                 @PathVariable Long limit){
        //创建一个page对象
        Page<SkuInfo> skuInfoPage = new Page<>(page, limit);

        IPage<SkuInfo> skuInfoIPage = manageService.getPage(skuInfoPage);
        return Result.ok(skuInfoIPage);
    }

    //商品上架
    //http://api.gmall.com/admin/product/onSale/30
    @GetMapping("onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId){
        manageService.onSale(skuId);
        return Result.ok();
    }

    //商品下架
    //http://api.gmall.com/admin/product/cancelSale/27
    @GetMapping("cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId){
        manageService.cancelSale(skuId);
        return Result.ok();
    }

}
