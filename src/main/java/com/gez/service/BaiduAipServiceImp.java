package com.gez.service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gez.utils.Base64Util;
import com.gez.utils.FileUtil;
import com.gez.utils.HttpUtil;
import com.google.gson.Gson;
@Service
public class BaiduAipServiceImp implements BaiduAipService {
	// 官网获取的 API Key 更新为你注册的
	@Value("${baiduOcrApiKey}")
	String apiKey;
	// 官网获取的 Secret Key 更新为你注册的
	@Value("${baiduOcrSecretKey}")
	String secretKey;
	
	//获取百度AccessToken
	@Override
	public String getAccessToken() throws Exception{
		/**
		 * 获取API访问token
		 * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
		 * @param ak - 百度云官网获取的 API Key
		 * @param sk - 百度云官网获取的 Securet Key
		 * @return assess_token 示例：
		 * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
		 */
		// 获取token地址
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		String getAccessTokenUrl = authHost
				// 1. grant_type为固定参数
				+ "grant_type=client_credentials"
				// 2. 官网获取的 API Key
				+ "&client_id=" + apiKey
				// 3. 官网获取的 Secret Key
				+ "&client_secret=" + secretKey;
			URL realUrl = new URL(getAccessTokenUrl);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.err.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String result = "";
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			/**
			 * 返回结果示例
			 */
			System.err.println("result:" + result);
			JSONObject jsonObject = new JSONObject(result);
			String access_token = jsonObject.getString("access_token");
			return access_token;
	}
	
	//高精度文字识别
	public Map<String, Object> accurateBasic(String imgPath) throws Exception {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(imgPath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = getAccessToken();

            String result = HttpUtil.post(url, accessToken, param);
            Gson gs = new Gson();
            Map<String, Object> map = gs.fromJson(result, Map.class);
            System.out.println(result);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}

