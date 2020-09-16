package com.taotao.service;


import com.taotao.vo.ZtreeResult;
import java.util.List;

public interface TbItemCatService {
    /**
     * 根据父类id查询这个分类下的所有子类对象
     * @param parentId 父类id
     * @return ztree需要的json数据格式
     */
    List<ZtreeResult> findTbItemCatByParentId(Long parentId);
}
