package com.taotao.service;

import com.taotao.bean.TbItem;
import com.taotao.utils.TaotaoResult;
import com.taotao.vo.Item;
import com.taotao.vo.LayuiEditResult;
import com.taotao.vo.LayuiTableResult;
import com.taotao.vo.MultipleQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface TbItemService {
    /**
     * 根据商品id查询指定商品信息
     * @param itemId 商品id
     * @return 指定商品id对象
     */
    TbItem findTbItemById(Long itemId);

    /**
     * 分页显示商品
     * @param page 当前页
     * @param limit 每一页显示条数
     * @return layui需要的json数据格式
     */
    LayuiTableResult getItemByPage(Integer page, Integer limit);

    /**
     * 根据页面传递过来的id删除指定的商品信息
     * @param tbitems
     * @return
     */
    TaotaoResult delItemById(List<TbItem> tbitems);

    /**
     * 修改商品状态
     * @param statusCode 1上架 2下架
     * @param ids 需要修改的商品id
     * @return 200表示成功
     */
    TaotaoResult changeItemStatus(Integer statusCode, Long[] ids);

    /**
     * 多条件查询商品信息
     * @param multipleQuery 商品标题、价格范围、商品状态、时间范围
     * @return layui table表需要的json数据格式
     */
    LayuiTableResult getMultipleQuery(MultipleQuery multipleQuery);

    /**
     * 根据id查询数据库得到商品信息 吧商品信息存入到 excel表对象里面去 并且返回这个对象
     * @param ids
     * @return
     */
    HSSFWorkbook getexportExcel(List<Long> ids);

    /**
     * 吧图片存入到nginx里面（多图片上传）。并且把图片地址封装到TaotaoResult里面
     * @param file
     * @return
     */
    TaotaoResult addPic(MultipartFile file);

    /**
     * 添加图片到nginx里面（富文本编辑器），并且把图片地址和图片名称封装到LayuiEditResult里面
     * @param file
     * @return
     */
    LayuiEditResult addPicDes(MultipartFile file);

    /**
     * 添加商品基本信息与商品描述信息
     * @param tbItem 商品基本信息 缺少id 状态 创建时间 更新时间
     * @param des 商品描述信息
     * @return
     */
    TaotaoResult addItem(Item item);
}
