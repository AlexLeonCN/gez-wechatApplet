package com.gez.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
  *  健康检查类
 */
@RestController
public class HealthController {
	@RequestMapping("/Ping")
	@ResponseBody
	String ping() {
		return "Pong";
	}
}
