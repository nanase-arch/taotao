package com.taotao.service.impl;

import com.taotao.bean.TbItem;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.service.TbItemService;
import com.taotao.vo.LayuiTableResult;
import com.taotao.vo.TbItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem findTbItemById(Long itemId) {
        TbItem tbItem = tbItemMapper.findTbItemById(itemId);
        return tbItem;
    }

    @Override
    public LayuiTableResult getItemByPage(Integer page, Integer limit) {
        LayuiTableResult result = new LayuiTableResult();
        result.setCode(0);
        result.setMsg("");
        int count = tbItemMapper.getTbItemCount();
        result.setCount(count);
        List<TbItemVo> data = tbItemMapper.getItemByPage((page-1)*limit,limit);
        result.setData(data);
        return result;
    }

    @Override
    public TaotaoResult delItemById(List<TbItem> tbitems) {
        System.out.println("aaa");
        if(tbitems==null&&tbitems.size()==0){
            return TaotaoResult.build(500,"删除商品失败");
        }

        List<Long> ids = new ArrayList<Long>();

        for (TbItem item:tbitems) {
            ids.add(item.getId());
        }
        //得到需要删除的id值
        int count = tbItemMapper.delItemByIds(ids);
        if(count==0){
            return TaotaoResult.build(500,"删除商品失败");
        }

        return TaotaoResult.build(200,"删除商品成功");
    }

    @Override
    public TaotaoResult changeItemStatus(Integer statusCode, Integer[] ids) {
        if(ids==null&&ids.length==0&&statusCode==1){
           return TaotaoResult.build(500,"商品上架失败");
        }
        if(ids==null&&ids.length==0&&statusCode==2){
            return TaotaoResult.build(500,"商品下架失败");
        }
        if(ids==null&&ids.length==0&&statusCode==3){
            return TaotaoResult.build(500,"商品删除失败");
        }
        int count = tbItemMapper.changeItemStatus(statusCode,ids);
        if(count==0&&statusCode==1){
            return TaotaoResult.build(500,"商品上架失败");
        }
        if(count==0&&statusCode==2){
            return TaotaoResult.build(500,"商品下架失败");
        }
        if(count==0&&statusCode==3){
            return TaotaoResult.build(500,"商品删除失败");
        }
        if(statusCode==1){
            return TaotaoResult.build(500,"商品上架成功");
        }
        if(statusCode==3){
            return TaotaoResult.build(500,"商品删除成功");
        }
        return TaotaoResult.build(500,"商品下架成功");
    }
}
