package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.product.service.ManageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "后台数据接口测试")
@RestController//@ReponseBody +@Controller
@RequestMapping("admin/product")
//@CrossOrigin
public class BaseManagerController {

    @Autowired
    private ManageService manageService;

    //如果不确定，可以写@RequestMapping
    @GetMapping("getCategory1")
    public Result getCategory1(){
        return Result.ok(manageService.getCategory1());
    }

    //根据一级分类id，查询二级分类数据集合
    @GetMapping("getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable Long category1Id){
        return Result.ok(manageService.getCategory2(category1Id));
    }
    //根据二级分类ID，查询三级分类数据集合
    @GetMapping("getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable Long category2Id){
        return Result.ok(manageService.getCategory3(category2Id));
    }

    //根据二级分类ID，查询三级分类数据集合
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable Long category1Id,
                               @PathVariable Long category2Id,
                               @PathVariable Long category3Id){
        return Result.ok(manageService.getAttrInfoList(category1Id,category2Id,category3Id));
    }
    //http://api.gmall.com/admin/product/saveAttrInfo
    //保存平台属性
    //前台传递的json数据，转化为对象，那么这个对象就是BaseAttrInfo
    // springMVC  ||@ResponseBody {将java对象转化为json字符串，直接将数据输出到页面}
    //@RequestBody {将json 字符串转化为java Object}
    //既是保存，又是修改的控制器
    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        //调用服务层方法
        manageService.saveAttrInfo(baseAttrInfo);
        //返回
        return Result.ok();
    }

    //  http://api.gmall.com/admin/product/getAttrValueList/{attrId}
    //  根据平台属性ID获取平台属性对象数据
    @GetMapping("getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable Long attrId){
        // attrId = base_attr_value.attr_id =  base_attr_info.id
        // select * from base_attr_value where attr_id = attrId;
        //先查询把baseAttrInfo，从baseAttrInfo中获取到baseAttrValue的集合
        BaseAttrInfo baseAttrInfo = manageService.getBaseAttrInfo(attrId);
        //List<BaseAttrValue> baseAttrValueList = manageService.getAttrValueList(attrId);
        return Result.ok(baseAttrInfo.getAttrValueList());
    }

    @GetMapping("spuImageList/{spuId}")
    public Result getSpuImageList(@PathVariable Long spuId){

        //调用服务层
        List<SpuImage> list = manageService.getSpuImageList(spuId);


        return Result.ok(list);
    }

}
