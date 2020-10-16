package com.gez.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 用于账单的增删改查操作
 */
public interface TallyDataService {
	List<Map<String, Object>> getDefTallyData(@Param("openId")String opedId);
	void insertDefTallyData(Map<String, Object> map);
	void updateDefTallyData(Map<String, Object> map);
	void deleteDefTallyData(Map<String, Object> map);
}
