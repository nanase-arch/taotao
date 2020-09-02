package com.wr.mapper;

import java.util.List;

import com.wr.bean.Items;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface ItemMapper {
	@Select("SELECT COUNT(*) FROM tbitem")
	int getConut();
	@Select("select * from tbitem limit #{index},#{limit}")
	List<Items> getSomeItems(@Param("index") int index, @Param("limit") int limit);

}
