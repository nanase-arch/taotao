package com.taotao.mapper;

import com.taotao.bean.TbItemDesc;
import org.apache.ibatis.annotations.Insert;

public interface TbItemDescMapper {
    @Insert("INSERT INTO tbitemdesc(itemId, itemDesc, created, updated) VALUE (#{itemId},#{itemDesc},#{created},#{updated})")
    int addItemDes(TbItemDesc tbItemDesc);
}
