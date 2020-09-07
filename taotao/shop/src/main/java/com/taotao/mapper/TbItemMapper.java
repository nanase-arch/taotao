package com.taotao.mapper;

import com.taotao.bean.TbItem;
import com.taotao.vo.TbItemVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemMapper {
    /**
     * 根据商品id查询商品信息
     * @param itemId 商品id
     * @return
     */
    TbItem findTbItemById(Long itemId);
    int getTbItemCount();
    List<TbItemVo> getItemByPage(@Param("index") Integer index, @Param("limit") Integer limit);

    /**
     * 批量删除商品信息 实际上 是修改商品状态 改为3
     * @param ids 需要删除的商品id
     * @return
     */
    int delItemByIds(@Param("ids") List<Long> ids);

    /**
     * 根据商品状态码 修改商品状态
     * @param statusCode 1上架 2下架
     * @param ids 需要上架和下架的商品id
     * @return
     */
    int changeItemStatus(@Param("statusCode") Integer statusCode, @Param("ids") Integer[] ids);
}
