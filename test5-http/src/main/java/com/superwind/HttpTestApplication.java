package com.superwind;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class HttpTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpTestApplication.class, args);
	}

	@Bean
	public OkHttpClient okHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(30, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.writeTimeout(30,TimeUnit.SECONDS)
				.retryOnConnectionFailure(true);
		return builder.build();
	}
}
