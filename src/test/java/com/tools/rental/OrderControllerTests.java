package com.tools.rental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.tools.rental.config.AppConstants;
import com.tools.rental.controller.OrderController;
import com.tools.rental.model.dto.ApplicationResponse;
import com.tools.rental.model.dto.OrderDto;

@SpringBootTest
public class OrderControllerTests {

	 @Autowired
	    private OrderController orderController;

	  
	    @Test
	    public void contextLoads() throws Exception {
	        Assertions.assertNotNull(orderController);
	    }

	    
	    
	    @Test
	    public void testCase01() throws Exception {
	    	ApplicationResponse response = null;
	    	 System.out.print("\n\n\n\nTEST CASE - 01");
	         try {
	             var order1 = OrderDto.builder()
	                     .toolCode("JAKR")
	                     .checkOutDate("9/3/15")
	                     .rentalDays(5)
	                     .discount(101)
	                     .build();
	             System.out.println("	INPUT: " + order1.toString());
	             response =  orderController.checkout(order1);
	         } catch (Exception e) {
	         }
	        Assertions.assertEquals(AppConstants.APP_RESPONSE_MESSAGE_VALIDATION_ERROR, response.getMessage());
	        Assertions.assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getCode());


	    }

	    
	    @Test
	    public void testCase02() throws Exception {
	    	ApplicationResponse response = null;
	    	 System.out.print("TEST CASE - 02");
	         try {
	        	 var order2 = OrderDto.builder()
	                     .toolCode("LADW")
	                     .checkOutDate("7/2/20")
	                     .rentalDays(3)
	                     .discount(10)
	                     .build();
	             System.out.println("	INPUT: " + order2.toString());
	             response = orderController.checkout(order2);
	         } catch (Exception e) {
	         }
		        Assertions.assertEquals(AppConstants.APP_RESPONSE_MESSAGE_SUCCESS, response.getMessage());
		        Assertions.assertEquals(HttpStatus.OK.toString(), response.getCode());
		  }
	    
	    @Test
	    public void testCase03() throws Exception {
	    	ApplicationResponse response = null;
	    	try {
	            System.out.print("TEST CASE - 03");
	            var order3 = OrderDto.builder()
	                    .toolCode("CHNS")
	                    .checkOutDate("7/2/15")
	                    .rentalDays(6)
	                    .discount(0)
	                    .build();
	            System.out.println("	INPUT: " + order3.toString());
	            response = orderController.checkout(order3);
	        } catch (Exception e) {
	        }
	        Assertions.assertEquals(AppConstants.APP_RESPONSE_MESSAGE_VALIDATION_ERROR, response.getMessage());
	        Assertions.assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getCode());

	    }
	    @Test
	    public void testCase04() throws Exception {
	    	ApplicationResponse response = null;
	    	try {
	            System.out.print("TEST CASE - 04");
	            var order4 = OrderDto.builder()
	                    .toolCode("JAKD")
	                    .checkOutDate("9/3/15")
	                    .rentalDays(6)
	                    .discount(0)
	                    .build();
	            System.out.println("	INPUT: " + order4.toString());
	            response = orderController.checkout(order4);
	            System.out.println(response);
	        } catch (Exception e) {
	        }
	        Assertions.assertEquals(AppConstants.APP_RESPONSE_MESSAGE_VALIDATION_ERROR, response.getMessage());
	        Assertions.assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getCode());

	    }
	    @Test
	    public void testCase05() throws Exception {
	    	ApplicationResponse response = null;
	    	 try {
	             System.out.print("TEST CASE - 05");
	             var order5 = OrderDto.builder()
	                     .toolCode("LADW")
	                     .checkOutDate("7/2/15")
	                     .rentalDays(9)
	                     .discount(0)
	                     .build();
	             System.out.println("	INPUT: " + order5.toString());
	             response = orderController.checkout(order5);
	         } catch (Exception e) {
	         }
		        Assertions.assertEquals(AppConstants.APP_RESPONSE_MESSAGE_VALIDATION_ERROR, response.getMessage());
		        Assertions.assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getCode());

	    }
	    @Test
	    public void testCase06() throws Exception {
	    	ApplicationResponse response = null;
	    	try {
	            System.out.print("TEST CASE - 06");
	            var order6 = OrderDto.builder()
	                    .toolCode("JAKR")
	                    .checkOutDate("9/2/20")
	                    .rentalDays(4)
	                    .discount(50)
	                    .build();
	            System.out.println("	INPUT: " + order6.toString());
	            response = orderController.checkout(order6);
	        } catch (Exception e) {
	        }
	        Assertions.assertEquals(AppConstants.APP_RESPONSE_MESSAGE_SUCCESS, response.getMessage());
	        Assertions.assertEquals(HttpStatus.OK.toString(), response.getCode());
	  }
	    

	    
}
