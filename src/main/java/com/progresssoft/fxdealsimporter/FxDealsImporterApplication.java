package com.progresssoft.fxdealsimporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class FxDealsImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxDealsImporterApplication.class, args);
	}

}
