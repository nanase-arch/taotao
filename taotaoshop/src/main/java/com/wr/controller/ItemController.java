package com.wr.controller;

import com.wr.bean.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wr.service.ItemService;

@Controller
public class ItemController {
@Autowired
	private ItemService is;
@RequestMapping("/showTables")
@ResponseBody
public LayuiResult show(Integer page, Integer limit) {
	LayuiResult findSomeItems = is.findSomeItems(page,limit);
	return findSomeItems;
}
}
