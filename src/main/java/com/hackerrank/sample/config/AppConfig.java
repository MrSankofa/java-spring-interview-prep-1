package com.hackerrank.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// TODO: Forgot the configuration annotation
@Configuration
public class AppConfig {

  @Bean
  public RestTemplate getRestTemplate
      () {
    return new RestTemplate();
  }


}
