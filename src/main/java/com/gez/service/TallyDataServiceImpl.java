package com.gez.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gez.mapper.TallyDataMapper;
/**
 * 用于账单的增删改查操作
 */
@Service
public class TallyDataServiceImpl implements TallyDataService {
	@Autowired
	TallyDataMapper tallyDataMapper;
	@Override
	public List<Map<String, Object>> getDefTallyData(String openId) {
		List<Map<String, Object>> dataList = tallyDataMapper.getDefTallyData(openId);
		return dataList;
	}
	@Override
	public void insertDefTallyData(Map<String, Object> map) {
		tallyDataMapper.insertDefTallyData(map);
	}
	@Override
	public void updateDefTallyData(Map<String, Object> map) {
		tallyDataMapper.updateDefTallyData(map);
	}
	@Override
	public void deleteDefTallyData(Map<String, Object> map) {
		tallyDataMapper.deleteDefTallyData(map);
	}

}
