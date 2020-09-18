package com.taotao.controller;

import com.taotao.service.TbItemCatService;
import com.taotao.vo.ZtreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/itemCat")
public class ItemCatController {

    @Autowired
    private TbItemCatService tbItemCatService;

    @RequestMapping("/showItemCat")
    @ResponseBody
    public List<ZtreeResult> showItemCat(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        List<ZtreeResult> result = tbItemCatService.findTbItemCatByParentId(parentId);
        return result;
    }
}
