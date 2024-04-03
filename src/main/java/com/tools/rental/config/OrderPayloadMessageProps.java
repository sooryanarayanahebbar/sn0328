package com.tools.rental.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


@Configuration
@ConfigurationProperties(prefix = "application.message.errors.validation.order.payload") 
@Data
public class OrderPayloadMessageProps {

	
	  private String emptyData;
	  private String emptyToolCode;
	  private String invalidRentalDays;
	  private String invalidRentalDaysIsToobig;
	  private String invalidDiscount;
	  private String invalidCheckoutDate;
	  private String invalidCheckoutDateFormat;
	  
}
