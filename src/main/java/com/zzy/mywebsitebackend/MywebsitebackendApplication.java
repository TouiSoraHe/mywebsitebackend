package com.zzy.mywebsitebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzy.mywebsitebackend.Data.Mapper")
public class MywebsitebackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(MywebsitebackendApplication.class, args);
	}
}
