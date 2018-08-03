package com.alipay.payment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.alipay.payment.service"})
public class AppConfig {
}
