package com.taotao.controller;

import com.taotao.bean.TbItem;
import com.taotao.common.TaotaoResult;
import com.taotao.service.TbItemService;
import com.taotao.vo.LayuiTableResult;
import com.taotao.vo.MultipleQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private TaotaoResult changeItemStatus(Integer statusCode,Long[] ids){
        TaotaoResult result = tbItemService.changeItemStatus(statusCode,ids);
        return result;
    }
    @RequestMapping("/multipleQuery")
    private LayuiTableResult multipleQuery(MultipleQuery multipleQuery) throws UnsupportedEncodingException {
        LayuiTableResult result = tbItemService.getMultipleQuery(multipleQuery);
        return result;
    }
    @RequestMapping("/exportExcel")
    public void exportExcel(Long itemId, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        List<Long> ids = new ArrayList<Long>();
        ids.add(itemId);
        HSSFWorkbook workbook = tbItemService.getexportExcel(ids);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = format.format(new Date());
        String fileName ="商品信息"+time;
        System.out.println(fileName);
        //表示我们要下载的文件名称叫啥
        response.setHeader("Content-Disposition",
                "attachment;fileName=" +
                        URLEncoder.encode(fileName+".xls", "UTF-8"));
        //表示要下载文件
        OutputStream stream = response.getOutputStream();
        //就会下载文件了
        workbook.write(stream);
        stream.close();

    }
    @RequestMapping("/exportsExcel")
    public void exportsExcel(String ids, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String[] arr = ids.split(",");
        List<Long> idsList = new ArrayList<Long>();
        for (String s:arr) {
            idsList.add(Long.valueOf(s));
        }
        HSSFWorkbook workbook = tbItemService.getexportExcel(idsList);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = format.format(new Date());
        String fileName ="商品信息"+time;
        //表示我们要下载的文件名称叫啥
        response.setHeader("Content-Disposition",
                "attachment;fileName=" +
                        URLEncoder.encode(fileName+".xls", "UTF-8"));
        //表示要下载文件
        OutputStream stream = response.getOutputStream();
        //就会下载文件了
        workbook.write(stream);
        stream.close();
    }
}