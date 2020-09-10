package com.syt.creditos.reportes;

import com.syt.creditos.reportes.storage.StorageService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
//@RequiredArgsConstructor
//@EnableConfigurationProperties(ApplicationProperties.class)
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ReportesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportesApplication.class, args);
	}

}
