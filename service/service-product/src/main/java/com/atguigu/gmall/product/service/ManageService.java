package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.*;

import java.util.List;

public interface ManageService {
    //查询所有一级分类数据
    List<BaseCategory1> getCategory1();
    //根据一级分类ID 查询二级分类数据
    List<BaseCategory2> getCategory2(Long category1Id);
    //根据二级分类ID 查询三级分类数据
    List<BaseCategory3> getCategory3(Long category2Id);

    //获取到平台属性数据集合
    List<BaseAttrInfo> getAttrInfoList(Long category1Id,
                                       Long category2Id,
                                       Long category3Id);


    void saveAttrInfo(BaseAttrInfo baseAttrInfo);


    //根据属性ID 查询平台属性值数据集合
    List<BaseAttrValue> getAttrValueList(Long attrId);

    //根据平台属性ID，查询平台属性对象
    BaseAttrInfo getBaseAttrInfo(Long attrId);
}
