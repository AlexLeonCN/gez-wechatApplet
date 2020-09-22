package com.gez.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gez.service.TallyDataService;
import com.gez.utils.Utils;
@RestController
public class TallyDataController {
	@Autowired
	TallyDataService tallyDataService;
	
	@GetMapping("defTallyData")
	Map<String, Object> getDefTallyData(@RequestParam("openId") String openId){
		Map<String, Object> resultMap = new HashMap<>();//前段返回
		if (Utils.isNullOrEmpty(openId)) {
			resultMap.put("code", "201");
			resultMap.put("msg", "用户标识openId不能为空");
			return resultMap;
		}
		List<Map<String, Object>> dataList = new ArrayList();
		dataList = tallyDataService.getDefTallyData(openId);
		if (!Utils.isNullOrEmpty(dataList)) {
			resultMap.put("data", dataList);
		}
		resultMap.put("code", "200");
		resultMap.put("msg", "success");
		return resultMap;
	}
	public static void main(String[] args) {
		String dataString = "2020-09-22";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dataString);
			System.out.println(date);
			long dateTime = date.getTime();
			System.out.println(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
