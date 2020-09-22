package com.gez.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TallyDataService {
	List<Map<String, Object>> getDefTallyData(@Param("openId")String opedId);
	void insertTallyData(Map<String, Object> map);
}
