package com.ordenaris.riskEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class RiskEngineApp {
	public static void main(String[] args) throws Exception {
		
		new SpringApplication(RiskEngineApp.class).run(args);
	
	}
	

	
}
