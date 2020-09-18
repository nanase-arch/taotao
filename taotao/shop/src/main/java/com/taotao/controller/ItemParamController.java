package com.taotao.controller;

import com.taotao.bean.TbItemParamGroup;
import com.taotao.service.TbItemParamService;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/param")
public class ItemParamController {
    @Autowired
    private TbItemParamService tbItemParamService;

    @RequestMapping("/getParam")
    public TaotaoResult findParamByCId(Long cId) {
        TaotaoResult result = tbItemParamService.findParamByCId(cId);
        return result;
    }

    @RequestMapping("/addParam")
    public TaotaoResult addParam(@RequestBody List<TbItemParamGroup> tbItemParamGroup) {
        TaotaoResult result = tbItemParamService.addParam(tbItemParamGroup);
        return result;
    }
}
