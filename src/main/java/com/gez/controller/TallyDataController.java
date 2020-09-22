package com.gez.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gez.service.TallyDataService;
import com.gez.utils.Utils;

import lombok.extern.log4j.Log4j2;
@Log4j2
@RestController
public class TallyDataController {
	@Autowired
	TallyDataService tallyDataService;
	
	@GetMapping("/defTallyData")
	Map<String, Object> getDefTallyData(@RequestParam("openId") String openId){
		Map<String, Object> resultMap = new HashMap<>();//前端返回
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
	
	@PostMapping("/defTallyData")
	Map<String, Object> insertDefTallyData(@RequestBody Map<String, Object> dataMap){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap = paramPreCheck(dataMap);//前置参数校验
		if (!Utils.isNullOrEmpty(resultMap)) {
			return resultMap;
		}
		try {
			tallyDataService.insertTallyData(dataMap);
			resultMap.put("code", "200");
			resultMap.put("msg", "success");
		} catch (Exception e) {
			log.info(e);
			resultMap.put("code", "201");
			resultMap.put("msg", "记账失败");
		}
		return resultMap;
	}
	
	private Map<String, Object> paramPreCheck(Map<String, Object> dataMap){
		Map<String, Object> resultMap = new HashMap<>();
		if (Utils.isNullOrEmpty(dataMap.get("openId"))) {
			resultMap.put("code", "201");
			resultMap.put("msg", "用户标识openId不能为空");
			return resultMap;
		}
		if (Utils.isNullOrEmpty(dataMap.get("transDate"))) {
			resultMap.put("code", "201");
			resultMap.put("msg", "账单日期不能为空");
			return resultMap;
		}
		if (Utils.isNullOrEmpty(dataMap.get("sortId"))) {
			resultMap.put("code", "201");
			resultMap.put("msg", "账单类型不能为空");
			return resultMap;
		}
		if (Utils.isNullOrEmpty(dataMap.get("amt"))) {
			resultMap.put("code", "201");
			resultMap.put("msg", "账单金额不能为空");
			return resultMap;
		}
		
		return resultMap;
	}
}
