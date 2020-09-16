package com.taotao.service.impl;

import com.taotao.bean.TbItem;

import com.taotao.bean.TbItemDesc;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.utils.IDUtils;
import com.taotao.utils.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.service.TbItemService;
import com.taotao.vo.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

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
        List<TbItemVo> data = tbItemMapper.getItemByPage((page - 1) * limit, limit);
        result.setData(data);
        return result;
    }

    @Override
    public TaotaoResult delItemById(List<TbItem> tbitems) {

        if (tbitems == null && tbitems.size() == 0) {
            return TaotaoResult.build(500, "删除商品失败");
        }
        List<Long> ids = new ArrayList<Long>();
        for (TbItem item : tbitems) {
            ids.add(item.getId());
        }
        //得到需要删除的id值
        int count = tbItemMapper.delItemByIds(ids);
        if (count == 0) {
            return TaotaoResult.build(500, "删除商品失败");
        }

        return TaotaoResult.build(200, "删除商品成功");
    }

    @Override
    public TaotaoResult changeItemStatus(Integer statusCode, Long[] ids) {

        if (ids == null && ids.length == 0 && statusCode == 1) {
            return TaotaoResult.build(500, "商品上架失败");
        }
        if (ids == null && ids.length == 0 && statusCode == 2) {
            return TaotaoResult.build(500, "商品下架失败");
        }
        int count = tbItemMapper.changeItemStatus(statusCode, ids);
        if (count == 0 && statusCode == 1) {
            return TaotaoResult.build(500, "商品上架失败");
        }
        if (count == 0 && statusCode == 2) {
            return TaotaoResult.build(500, "商品下架失败");
        }
        if (statusCode == 1) {
            return TaotaoResult.build(500, "商品上架成功");
        }

        return TaotaoResult.build(500, "商品下架成功");
    }

    @Override
    public LayuiTableResult getMultipleQuery(MultipleQuery multipleQuery) {
        /**
         *  price1  :0
         *  price2  :0
         *      price1>price2
         *      price1<price2
         *      price1=price2
         */
        String title = multipleQuery.getTitle();

        if (!"".equals(title)) {
            try {
                String newTitle = new String(title.getBytes("ISO-8859-1"), "UTF-8");
                multipleQuery.setTitle(newTitle);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        Long price1 = multipleQuery.getPrice1();
        if (price1 == null) {
            price1 = 0L;
        }

        Long price2 = multipleQuery.getPrice2();
        if (price2 == null) {
            price2 = 0L;
        }
        // price1 = 0  price2 = 200
        if (price1 != 0L && price2 != 0L) {
            if (price1 > price2) {
                Long type = price1;
                price1 = price2;
                price2 = type;
            }

        }
        String time = "2019-01-01";
        //页面传递过来的时间 被转化器 如果没有输入值 就被转化为2019-01-01
        Date date11 = multipleQuery.getDate1();
        //页面传递过来的时间 被转化器 如果没有输入值 就被转化为2019-01-01
        Date date22 = multipleQuery.getDate2();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format.parse(time);
            int i = date1.compareTo(date11);
            int j = date1.compareTo(date22);
            if (i == 0 && j == 0) {
                multipleQuery.setDate1(null);
                multipleQuery.setDate2(null);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //吧转化顺序的价格给转化一下
        multipleQuery.setPrice1(price1);
        multipleQuery.setPrice2(price2);
        LayuiTableResult result = new LayuiTableResult();
        result.setCode(0);
        result.setMsg("");
        int count = tbItemMapper.getMultipleQueryCount(multipleQuery);
        result.setCount(count);
        //代表我们要搜索的商品信息 实际上不存在
        if (count == 0) {
            return result;
        }
        //因为页面需要的是TbItemVo对象
        //因为页面传过来的额是page 他代表当前页 但是我们要根据 index索引来查询
        Integer index = (multipleQuery.getPage() - 1) * multipleQuery.getLimit();
        multipleQuery.setPage(index);

        List<TbItemVo> date = tbItemMapper.getMultipleQuery(multipleQuery);
        result.setData(date);
        return result;
    }

    @Override
    public HSSFWorkbook getexportExcel(List<Long> ids) {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        //根据id查询商品信息 装到集合对象里面去
        List<TbItemVo> tbItemVoList = tbItemMapper.findTbItemVoByIds(ids);
        //excel表样式对象
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);//上边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THICK);//下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);//左边框
        style.setBorderRight(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);//右边框

        HSSFSheet sheet = workbook.createSheet("商品信息");// 创建工作表(Sheet)
        //sheet他可以设置宽高
        for (int i = 0; i < 6; i++) {
            sheet.setColumnWidth(i, 31 * 256);
        }

        //创建表头
        HSSFRow firstRow = sheet.createRow(0);// 创建行,从0开始
        HSSFCell cell0 = firstRow.createCell(0);
        cell0.setCellValue("商品编号");
        HSSFCell cell1 = firstRow.createCell(1);
        cell1.setCellValue("商品名称");
        HSSFCell cell2 = firstRow.createCell(2);
        cell2.setCellValue("商品类别");
        HSSFCell cell3 = firstRow.createCell(3);
        cell3.setCellValue("创建时间");
        HSSFCell cell4 = firstRow.createCell(4);
        cell4.setCellValue("商品价格");
        HSSFCell cell5 = firstRow.createCell(5);
        cell5.setCellValue("创建数量");
        //设置样式
        cell0.setCellStyle(style);
        cell1.setCellStyle(style);
        cell2.setCellStyle(style);
        cell3.setCellStyle(style);
        cell4.setCellStyle(style);
        cell5.setCellStyle(style);
        //日期对象转化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 总金额
        Long totalMoney = 0L;
        // 总数量
        Integer totalNum = 0;
        //循环遍历 商品集合对象
        for (int i = 0; i < tbItemVoList.size(); i++) {
            TbItemVo itemVo = tbItemVoList.get(i);
            HSSFRow row = sheet.createRow(i + 1);// 根据对象来创建行 从第1行开始
            HSSFCell hssfCell0 = row.createCell(0);
            hssfCell0.setCellValue(itemVo.getId());
            HSSFCell hssfCell1 = row.createCell(1);
            hssfCell1.setCellValue(itemVo.getTitle());
            HSSFCell hssfCell2 = row.createCell(2);

            //设置类别
            hssfCell2.setCellValue(itemVo.getTbItemCatName());

            HSSFCell hssfCell3 = row.createCell(3);
            //设置创建时间
            hssfCell3.setCellValue(format.format(itemVo.getCreated()));
            Long price = itemVo.getPrice();
            //计算出了总金额
            totalMoney += price;
            //设置金额
            HSSFCell hssfCell4 = row.createCell(4);
            hssfCell4.setCellValue(getCalculationPrice(price));
            HSSFCell hssfCell5 = row.createCell(5);
            Integer num = itemVo.getNum();
            //计算出了总数量
            totalNum += num;
            //设置数量
            hssfCell5.setCellValue(num);
            hssfCell0.setCellStyle(style);
            hssfCell1.setCellStyle(style);
            hssfCell2.setCellStyle(style);
            hssfCell3.setCellStyle(style);
            hssfCell4.setCellStyle(style);
            hssfCell5.setCellStyle(style);
        }
        //设置总金额
        HSSFRow rowLast = sheet.createRow(tbItemVoList.size() + 1);
        HSSFCell secondLastCell = rowLast.createCell(4);
        secondLastCell.setCellValue("总金额:" + getCalculationPrice(totalMoney));
        secondLastCell.setCellStyle(style);
        //设置总数量
        HSSFCell lastCell = rowLast.createCell(5);
        lastCell.setCellValue("总数量:" + totalNum);
        lastCell.setCellStyle(style);

        return workbook;
    }

    @Override
    public TaotaoResult addPic(MultipartFile file) {
        //得到图片名称
        String filename = file.getOriginalFilename();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(new Date());
        File folder = new File("e://up/"+time);
        if (!folder.exists()) {
            folder.mkdir();
        }
        //修改图片名称
        filename = IDUtils.genImageName() + filename.substring(filename.lastIndexOf("."));
        try {
            //就会把图片存入到我们的E盘up文件夹里面
            file.transferTo(new File(folder,filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok("http://localhost/"+time+"/"+filename);
    }

    @Override
    public LayuiEditResult addPicDes(MultipartFile file) {
        //得到图片名称
        String filename = file.getOriginalFilename();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(new Date());
        File folder = new File("e://up/"+time);
        if (!folder.exists()) {
            folder.mkdir();
        }
        //修改图片名称
        filename = IDUtils.genImageName() + filename.substring(filename.lastIndexOf("."));
        try {
            //就会把图片存入到我们的E盘up文件夹里面
            file.transferTo(new File(folder,filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LayuiEditResult result = new LayuiEditResult();
        result.setCode(0);
        result.setMsg("");
        PicData data = new PicData();
        data.setSrc("http://localhost/"+time+"/"+filename);
        data.setTitle(filename);
        result.setData(data);
        return result;
    }

    @Override
    public TaotaoResult addItem(TbItem tbItem, String des) {
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        //生成商品id的方法
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        //商品信息准备完毕了 添加商品信息到商品表里面去
        int count1 = tbItemMapper.addItem(tbItem);
        if(count1==0){
            return TaotaoResult.build(200,"添加商品基本信息失败");
        }
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(des);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int count2 = tbItemDescMapper.addItemDes(tbItemDesc);
        if(count2==0){
            return TaotaoResult.build(200,"添加商品描述失败");
        }
        return TaotaoResult.build(200,"添加商品成功");
    }


    //单独计算价格的方法
    private String getCalculationPrice(Long price) {
        Long integer = price / 100;
        Long decimal = price % 100;
        //1100%100 = 0
        if (decimal == 0) {
            return integer + ".00";
        }
        return integer + "." + decimal;
    }
}
