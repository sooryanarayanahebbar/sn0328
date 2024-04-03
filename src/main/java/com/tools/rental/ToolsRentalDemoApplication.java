package com.tools.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.tools.rental.config.OrderPayloadMessageProps;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties(OrderPayloadMessageProps.class)
public class ToolsRentalDemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(ToolsRentalDemoApplication.class, args);

    }
}
