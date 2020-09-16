package com.taotao.mapper;

import com.taotao.bean.TbItemCat;

import java.util.List;

public interface TbItemCatMapper {




    List<TbItemCat> findTbItemCatByParentId(Long parentId);
}
