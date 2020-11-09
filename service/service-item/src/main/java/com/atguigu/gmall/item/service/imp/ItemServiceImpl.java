package com.atguigu.gmall.item.service.imp;

import com.atguigu.gmall.item.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public Map<String, Object> getBySkuId(Long skuId) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        return objectObjectHashMap;
    }
}
