package com.hackerrank.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class config {

  // create a bean to send requests
  /*
  * first try functionally correct
  * @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
  *
  * getResttemplate slightly better
  * */

  @Bean
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
