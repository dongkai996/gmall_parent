package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;
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

    //按照三级分类ID带分页的查询 pageSize,pageNo
    //IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> page,Long category3Id);

    //springmvc 对象传值
    IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> page, SpuInfo spuInfo);


    //查询所有的销售属性数据
    List<BaseSaleAttr> getBaseSaleAttrList();

    void saveSupInfo(SpuInfo spuInfo);

    //根据spuid 查询spuImage集合
    List<SpuImage> getSpuImageList(Long spuId);

    //根据spuid 查询SpusaleAttr
    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);

    void saveSkuInfo(SkuInfo skuInfo);

    //带分页查询诉苦Info
    IPage<SkuInfo> getPage(Page<SkuInfo> page);

    //商品上架
    void onSale(Long skuId);

    //商品下架
    void cancelSale(Long skuId);

    //根据SkuId获取数据
    SkuInfo getSkuInfo(Long skuId);

    /**
     * 通过三级分类id查询分类名称
     * @param category3Id
     * @return
     */
    BaseCategoryView getCategoryViewByCategory3Id(Long category3Id);

    /**
     * 获取sku价格
     * @param skuId
     * @return
     */
    BigDecimal getSkuPrice(Long skuId);


    //根据skuId，spuId获取销售属性集合
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId);

}
