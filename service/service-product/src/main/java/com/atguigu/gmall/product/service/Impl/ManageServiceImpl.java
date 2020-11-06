package com.atguigu.gmall.product.service.Impl;


import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springframework.beans.factory.annotation.Autowired;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    //注入mapper
    @Autowired
    private BaseCategory1Mapper baseCategory1Mapper;

    @Autowired
    private BaseCategory2Mapper baseCategory2Mapper;

    @Autowired
    private BaseCategory3Mapper baseCategory3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;



    @Override
    public List<BaseCategory1> getCategory1() {
        //select * from base_category1
        return baseCategory1Mapper.selectList(null);
    }

    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        //select * from base_category2 where category1_id = category1Id
        //查询哪个实体类或者数据表
        //构建查询条件
        QueryWrapper<BaseCategory2> baseCategory2QueryWrapper = new QueryWrapper<>();
        baseCategory2QueryWrapper.eq("category1_id",category1Id);

        return baseCategory2Mapper.selectList(baseCategory2QueryWrapper);
    }

    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        //select * from base_category3 where category2_id = category2Id
        return baseCategory3Mapper.selectList(new QueryWrapper<BaseCategory3>().eq("category2_id",category2Id));
    }

    /**
     * 页面根据分类ID 查询平台属性，没有属性值
     * 但是，需要通过这个方法将平台属性，以及平台属性值一起查出来的
     * 提示：在制作sku的时候，我们需要选择平台属性，以及平台属性的值
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        //select * from base_attr_info where category_id = category1Id category2Id category3Id

        //判断：mybatis 动态sql if（）

        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id,category2Id,category3Id);
    }

    @Override
    @Transactional
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

        /*
        base_attr_info
        base_attr_value
         */
        //什么时候是修改，什么时候是保存
        if(baseAttrInfo.getId()!=null){
            //id 是主键
            //update base_attr_info set attr_name = baseAttrInfo.getAttrName() where id = baseAttrInfo.getId();
            baseAttrInfoMapper.updateById(baseAttrInfo);

        }else {
            //base_attr_info insert();  当执行完成insert之后，那么ID会自动获取到
            baseAttrInfoMapper.insert(baseAttrInfo);

        }

        //先delete，跟attrId先关的属性值，在insert
        //delete from base_attr_value where attr_id = baseAttrInfo.getId();
        baseAttrValueMapper.delete(new QueryWrapper<BaseAttrValue>().eq("attr_id",baseAttrInfo.getId()));



        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (!CollectionUtils.isEmpty(attrValueList)) {
            //循环遍历
            for (BaseAttrValue baseAttrValue : attrValueList) {
                //插入数据
                //因为前台传递数据的时候，并没有将attr_id传递，但是我们可以通过业务指定当前的数据是谁？
                // attr_id = baseAttrInfo.getId();

                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }


    }

    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.selectList(new QueryWrapper<BaseAttrValue>().eq("attr_id", attrId));
        return baseAttrValueList;
    }

    @Override
    public BaseAttrInfo getBaseAttrInfo(Long attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectById(attrId);
        //判断平台属性不为空
        if (baseAttrInfo!=null){
            // baseAttrInfo 没有属性值集合字段
            // 给attrValueList赋值
            //
            baseAttrInfo.setAttrValueList(this.getAttrValueList(attrId));
        }
        return baseAttrInfo;
    }

    @Override
    public IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> page, SpuInfo spuInfo) {
        QueryWrapper<SpuInfo> spuInfoQueryWrapper = new QueryWrapper<>();
        spuInfoQueryWrapper.eq("category3_id",spuInfo.getCategory3Id());
        spuInfoQueryWrapper.orderByDesc("id");


        return spuInfoMapper.selectPage(page,spuInfoQueryWrapper);
    }


}
