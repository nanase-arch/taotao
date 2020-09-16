package com.taotao.service.impl;

import com.taotao.bean.TbItemCat;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.service.TbItemCatService;
import com.taotao.vo.ZtreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<ZtreeResult> findTbItemCatByParentId(Long parentId) {
        List<TbItemCat> itemCats = tbItemCatMapper.findTbItemCatByParentId(parentId);
        List<ZtreeResult> results = new ArrayList<ZtreeResult>();
        for (TbItemCat itemCat:itemCats) {
            ZtreeResult result = new ZtreeResult();
            result.setId(itemCat.getId());
            result.setName(itemCat.getName());
            result.setIsParent(itemCat.getIsParent());
            results.add(result);
        }

        return results;
    }
}
