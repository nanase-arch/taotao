package com.taotao.mapper;

import com.taotao.bean.TbItem;
import com.taotao.bean.TbItemCat;
import com.taotao.vo.MultipleQuery;
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

    /**
     * 获取商品总记录条数
     * @return
     */
    int getTbItemCount();

    /**
     * 分页查询商品信息
     * @param index 当前索引
     * @param limit 每页显示条数
     * @return 页面需要的对象
     */
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
    int changeItemStatus(@Param("statusCode") Integer statusCode, @Param("ids") Long[] ids);

    /**
     * 多条件搜索，符合搜索条件的商品记录条数
     * @param multipleQuery 标题、价格范围、商品状态、时间范围
     * @return 符合多个条件的商品记录总条数
     */
    int getMultipleQueryCount(MultipleQuery multipleQuery);

    /**
     * 多条件搜索，符合搜索条件的商品信息
     * @param multipleQuery 标题、价格范围、商品状态、时间范围
     * @return 符合多个条件的商品信息集合对象
     */
    List<TbItemVo> getMultipleQuery(MultipleQuery multipleQuery);

    List<TbItemVo> findTbItemVoByIds(@Param("ids") List<Long> ids);

    List<TbItemCat> findId(Long id);

    int addItem(TbItem tbItem);
}
