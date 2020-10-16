package com.gez.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gez.service.SortManageService;
import com.gez.utils.Utils;
/**
 * 此类用于账单的类别的增删改查操作
 */
@RestController
public class SortManageController {
	@Autowired
	private SortManageService sortManageService;
	//获取支出分类:包含系统默认和用户自定义的
	@PostMapping("/getSorts")
	Map<String, Object> getIncomeSort(@RequestBody Map<String, Object> dataMap){
		Map<String, Object> resultMap = new HashMap<>();
		if (Utils.isNullOrEmpty(dataMap.get("openId"))) {
			resultMap.put("code", "201");
			resultMap.put("msg", "openId不能为空");
			return resultMap;
		}
		if (Utils.isNullOrEmpty(dataMap.get("type"))) {
			resultMap.put("code", "201");
			resultMap.put("msg", "类型不能为空");
			return resultMap;
		}
		try {
			List<Map<String, Object>> dataList = new ArrayList<>();
			dataList = sortManageService.getSorts(dataMap);
			resultMap.put("code", "200");
			resultMap.put("msg", "success");
			resultMap.put("data", dataList);
			return resultMap;
		} catch (Exception e) {
			resultMap.put("code", "201");
			resultMap.put("msg", "获取分类失败");
			return resultMap;
		}
	}
}
