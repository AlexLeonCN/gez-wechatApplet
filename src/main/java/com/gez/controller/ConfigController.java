package com.gez.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ConfigController {
	@Value("${lalala.port}")
	String serverPort;
	
	@RequestMapping("/serverPort")
	@ResponseBody
	String serverPort() {
		return serverPort;
	}
}
