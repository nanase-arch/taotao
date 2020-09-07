package com.taotao.controller;

import com.taotao.bean.TbItem;
import com.taotao.common.TaotaoResult;
import com.taotao.service.TbItemService;
import com.taotao.vo.LayuiTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private TbItemService tbItemService;

    @RequestMapping("/{itemId}")
    private TbItem findItemById(@PathVariable Long itemId){
        TbItem tbItem = tbItemService.findTbItemById(itemId);
        return tbItem;
    }
    @RequestMapping("/getItemByPage")
    private LayuiTableResult getItemByPage(Integer page,Integer limit){
        LayuiTableResult result = tbItemService.getItemByPage(page, limit);
        return result;
    }

    @RequestMapping("/delItemById")
    private TaotaoResult delItemById(@RequestBody List<TbItem> tbitems){
        TaotaoResult result = tbItemService.delItemById(tbitems);
        return result;
    }
    @RequestMapping("/changeItemStatus")
    private TaotaoResult changeItemStatus(Integer statusCode,Integer[] ids){
        TaotaoResult result = tbItemService.changeItemStatus(statusCode,ids);
        return result;
    }
}
