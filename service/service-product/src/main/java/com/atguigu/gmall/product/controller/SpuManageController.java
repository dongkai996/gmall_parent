package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台spu数据接口测试")
@RestController
@RequestMapping("admin/product")
public class SpuManageController {
    @Autowired
    private ManageService manageService;

    @GetMapping("{page}/{limit}")
    public Result getSpuList(@PathVariable Long page,
                             @PathVariable Long limit,
                             //@RequestParam("category3Id") Long category3Id,
                             //Long category3Id
                             //HttpServletRequest request,
                             SpuInfo spuInfo){

        //String category3Id = request.getParameter("category3Id");
        Page<SpuInfo> spuInfoPage = new Page<>(page,limit);
        IPage<SpuInfo> spuInfoPage1 = manageService.getSpuInfoPage(spuInfoPage, spuInfo);
        return Result.ok(spuInfoPage1);
    }
}
