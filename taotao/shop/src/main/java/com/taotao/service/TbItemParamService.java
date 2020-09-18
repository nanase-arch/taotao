package com.taotao.service;

import com.taotao.bean.TbItemParamGroup;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface TbItemParamService {
    /**
     * 根据商品分类id查询商品规格参数组信息
     * @param cId 分类id
     * @return 如果有规格参数组 data不为null 如果没有 则为null
     */
    TaotaoResult findParamByCId(Long cId);

    /**
     * alt+cltr
     * 添加规格参数组与规格参数项信息
     * @param tbItemParamGroup 规格参数组集合对象里面包含了项集合对象
     * @return
     */
    TaotaoResult addParam(List<TbItemParamGroup> tbItemParamGroup);
}
