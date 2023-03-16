package ru.kirill.cuturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CutUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CutUrlApplication.class, args);
	}

}
