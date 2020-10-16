package com.gez.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gez.service.TallyDataService;
import com.gez.utils.Utils;
import lombok.extern.log4j.Log4j2;
/**
 * 此类用于账单的增删改查操作
 */
@Log4j2
@RestController
public class TallyDataController {
	@Autowired
	TallyDataService tallyDataService;
	
	//获取默认记账本记录
	@GetMapping("/defTallyData")
	Map<String, Object> getDefTallyData(@RequestParam("openId") String openId){
		Map<String, Object> resultMap = new HashMap<>();//前端返回
		if (Utils.isNullOrEmpty(openId)) {
			resultMap.put("code", "201");
			resultMap.put("msg", "用户标识openId不能为空");
			return resultMap;
		}
		List<Map<String, Object>> dataList = new ArrayList<>();
		dataList = tallyDataService.getDefTallyData(openId);
		if (!Utils.isNullOrEmpty(dataList)) {
			resultMap.put("data", dataList);
		}
		resultMap.put("code", "200");
		resultMap.put("msg", "success");
		return resultMap;
	}
	
	//记账默认记账本
	@PostMapping("/defTallyData")
	Map<String, Object> insertDefTallyData(@RequestBody Map<String, Object> dataMap){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap = paramPreCheck(dataMap);//前置参数校验
		if (!Utils.isNullOrEmpty(resultMap)) {
			return resultMap;
		}
		try {
			tallyDataService.insertDefTallyData(dataMap);
			resultMap.put("code", "200");
			resultMap.put("msg", "success");
		} catch (Exception e) {
			log.info(e);
			resultMap.put("code", "201");
			resultMap.put("msg", "记账失败");
		}
		return resultMap;
	}
	
	//更新默认记账本
	@PutMapping("/defTallyData")
	Map<String, Object> updateDefTallyData(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap();
		resultMap = paramPreCheck(map);
		if (!Utils.isNullOrEmpty(resultMap)) {
			return resultMap;
		}
		try {
			tallyDataService.updateDefTallyData(map);
			resultMap.put("code", "200");
			resultMap.put("msg", "success");
		} catch (Exception e) {
			log.info(e);
			resultMap.put("code", "201");
			resultMap.put("msg", "更新失败");
		}
		return resultMap;
	}
	
	//删除默认记账本中的记录
	@DeleteMapping("/defTallyData")
	Map<String, Object> deleteDefTallyData(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap();
		if (Utils.isNullOrEmpty(map.get("tallyId"))) {
			resultMap.put("code", "201");
			resultMap.put("msg", "账单条目序号不能为空");
			return resultMap;
		}
		if (Utils.isNullOrEmpty(map.get("openId"))) {
			resultMap.put("code", "201");
			resultMap.put("msg", "用户标识openId不能为空");
			return resultMap;
		}
		try {
			tallyDataService.deleteDefTallyData(map);
			resultMap.put("code", "200");
			resultMap.put("msg", "success");
		} catch (Exception e) {
			log.info(e);
			resultMap.put("code", "201");
			resultMap.put("msg", "删除记录失败");
		}
		return resultMap;
	}
	
	//参数前置校验
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
