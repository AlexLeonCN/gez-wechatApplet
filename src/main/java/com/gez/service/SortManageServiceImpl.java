package com.gez.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gez.mapper.SortManageMapper;

@Service
public class SortManageServiceImpl implements SortManageService {
	@Autowired
	private SortManageMapper sortManageMapper;
	@Override
	public List<Map<String, Object>> getSorts(Map<String, Object> map) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		return dataList = sortManageMapper.getSortS(map);
	}
}
