package com.taotao.service.impl;


import com.taotao.bean.TbItemParamGroup;
import com.taotao.bean.TbItemParamKey;
import com.taotao.mapper.TbItemParamGroupMapper;
import com.taotao.service.TbItemParamService;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {
    @Autowired
    private TbItemParamGroupMapper tbItemParamGroupMapper;


    @Override
    public TaotaoResult findParamByCId(Long cId) {
        List<TbItemParamGroup> groupList = tbItemParamGroupMapper.findParamByCId(cId);
        if (groupList == null) {
            return TaotaoResult.build(200, "该分类不存在规格参数");
        }
        return TaotaoResult.build(200, "ok", groupList);
    }

    @Override
    public TaotaoResult addParam(List<TbItemParamGroup> tbItemParamGroup) {
        for (TbItemParamGroup group : tbItemParamGroup) {
            int count1 = tbItemParamGroupMapper.addParamGroup(group);
            if (count1 == 0) {
                return TaotaoResult.build(500, "添加规格参数组失败");
            }
            //当我们执行完成这个sql语句以后 我们group里面就有id了
            List<TbItemParamKey> paramKeys = group.getParamKeys();
            for (TbItemParamKey param : paramKeys) {
                param.setGroupId(group.getId());
                //走完这个for循环 我们的项对象里面就全部由组id了
            }
            int count2 = tbItemParamGroupMapper.addParamKey(paramKeys);
            if (count2 == 0) {
                return TaotaoResult.build(500, "添加规格参数项失败");
            }
        }


        return TaotaoResult.build(200,"添加成功");
    }
}
