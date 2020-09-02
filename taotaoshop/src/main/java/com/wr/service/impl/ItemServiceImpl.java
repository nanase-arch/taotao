package com.wr.service.impl;

import java.util.List;

import com.wr.bean.Items;
import com.wr.bean.LayuiResult;
import com.wr.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wr.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper im;
	@Override
	public LayuiResult findSomeItems(int page, int limit) {
	int count=	im.getConut();
	int index=(page-1)*limit;
	List<Items> l=im.getSomeItems(index,limit);
	LayuiResult lr=new LayuiResult();
	lr.setData(l);
	lr.setCode(0);
	lr.setMsg("");
	lr.setCount(200);
		return lr;
	}

}
