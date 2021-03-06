package com.gez;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/*该项目实现SpringBoot整合Mybatis/mybatis-plus/JSP
 * @author user
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.gez.mapper")//包扫描
public class SpringBootRun {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRun.class, args);
	}
}
