package com.taotao.mapper;

import com.taotao.bean.TbItemParamGroup;
import com.taotao.bean.TbItemParamKey;
import com.taotao.bean.TbItemParamValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemParamGroupMapper {
    /**
     * 根据分类id查询规格参数组与每一个组所对应的规格参数项
     * @param cId 分类id
     * @return
     */
    List<TbItemParamGroup> findParamByCId(Long cId);

    /**
     * 插入一条规格参数组信息到数据库中并且返回当前的id
     * @param group 组对象
     * @return
     */
    int addParamGroup(TbItemParamGroup group);

    /**
     * 插入多个规格参数项信息到数据库中
     * @param paramKeys
     * @return
     */
    int addParamKey(@Param("paramKeys") List<TbItemParamKey> paramKeys);

    int addItemParam(@Param("params") List<TbItemParamValue> params);
}
