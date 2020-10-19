package com.gez.service;

import java.util.Map;

/**
 * 用于调用百度AIP接口
 */
public interface BaiduAipService {
	//获取百度AccessToken
	String getAccessToken() throws Exception;
	Map<String, Object> accurateBasic(String imgPath) throws Exception;
}
