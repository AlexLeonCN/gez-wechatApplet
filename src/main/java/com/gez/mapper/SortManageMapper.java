package com.gez.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SortManageMapper {
	@Select("(SELECT `SORT_ID`,`OPEN_ID`,`TYPE`,`SORT_NAME` FROM GEZ_SORT WHERE OPEN_ID IS NULL AND TYPE = #{type} )"
			+ "UNION ALL "
			+ "(SELECT `SORT_ID`,`OPEN_ID`,`TYPE`,`SORT_NAME` FROM GEZ_SORT WHERE OPEN_ID = #{openId} AND TYPE = #{type} )"
			+ "ORDER BY `SORT_ID` ASC")
	List<Map<String, Object>> getSortS(Map<String, Object> map);
}
