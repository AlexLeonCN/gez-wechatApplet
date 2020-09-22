package com.gez.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gez.mapper.TallyDataMapper;
@Service
public class TallyDataServiceImpl implements TallyDataService {
	@Autowired
	TallyDataMapper tallyDataMapper;
	@Override
	public List<Map<String, Object>> getDefTallyData(String openId) {
		List<Map<String, Object>> dataList = tallyDataMapper.getDefTallyData(openId);
		return dataList;
	}

}
