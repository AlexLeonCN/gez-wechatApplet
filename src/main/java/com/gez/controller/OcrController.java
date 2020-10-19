package com.gez.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gez.service.BaiduAipService;
import com.gez.utils.Utils;
@RestController
public class OcrController {
	@Autowired
	BaiduAipService baiduAipService;

	@RequestMapping("/OcrAccurateBasic")
	public Map<String, Object> accurateBasic(@RequestBody Map<String, Object> dataMap) {
		Map<String, Object> resultMap = new HashMap<>();
		String imgPath = (String) dataMap.get("imgPath");
		try {
			Map<String, Object> result = baiduAipService.accurateBasic(imgPath);
			List<Map<String, Object>> wordsArray = new ArrayList<>();
			wordsArray = (List<Map<String, Object>>) result.get("words_result");
			if (!Utils.isNullOrEmpty(wordsArray)) {
				resultMap.put("code", "200");
				resultMap.put("msg", "success");
				String dataString = "";
				for (int i = 0; i < wordsArray.size(); i++) {
					dataString += wordsArray.get(i).get("words") + "/n";
				}
				resultMap.put("data", dataString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Utils.isNullOrEmpty(resultMap)) {
			resultMap.put("code", "201");
			resultMap.put("msg", "文字识别出错!");
		}
		return resultMap;
	}
}
