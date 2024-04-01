package com.tools.rental;

import com.tools.rental.order.OrderController;
import com.tools.rental.order.OrderDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ToolsRentalDemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(ToolsRentalDemoApplication.class, args);

    }


    @Bean
    CommandLineRunner commandLineRunner(
            OrderController orderController
    ) {
        return args -> {
            System.out.println("TEST CASE - 01");
            try {
                var order1 = OrderDto.builder()
                        .toolCode("JAKR")
                        .checkOutDate("9/3/15")
                        .rentalDays(5)
                        .discount(101)
                        .build();
                System.out.println("TEST CASE - 01: INPUT: " + order1.toString());
                orderController.checkout(order1);
            } catch (Exception e) {
            }
            try {
                System.out.println("TEST CASE - 02");
                var order2 = OrderDto.builder()
                        .toolCode("LADW")
                        .checkOutDate("7/2/20")
                        .rentalDays(3)
                        .discount(10)
                        .build();
                System.out.println("TEST CASE - 02: INPUT: " + order2.toString());
                orderController.checkout(order2);
            } catch (Exception e) {
            }
            try {
                System.out.println("TEST CASE - 03");
                var order3 = OrderDto.builder()
                        .toolCode("CHNS")
                        .checkOutDate("7/2/15")
                        .rentalDays(6)
                        .discount(0)
                        .build();
                System.out.println("TEST CASE - 03: INPUT: " + order3.toString());
                orderController.checkout(order3);
            } catch (Exception e) {
            }
            try {
                System.out.println("TEST CASE - 04");
                var order4 = OrderDto.builder()
                        .toolCode("JAKD")
                        .checkOutDate("9/9/15")
                        .rentalDays(9)
                        .discount(0)
                        .build();
                System.out.println("TEST CASE - 04: INPUT: " + order4.toString());
                orderController.checkout(order4);
            } catch (Exception e) {
            }

            try {
                System.out.println("TEST CASE - 05");
                var order5 = OrderDto.builder()
                        .toolCode("LADW")
                        .checkOutDate("02/2/24")
                        .rentalDays(5)
                        .discount(10)
                        .build();
                System.out.println("TEST CASE - 05: INPUT: " + order5.toString());
                orderController.checkout(order5);
            } catch (Exception e) {
            }
            try {
                System.out.println("TEST CASE - 06");
                var order6 = OrderDto.builder()
                        .toolCode("JAKR")
                        .checkOutDate("9/2/20")
                        .rentalDays(4)
                        .discount(50)
                        .build();
                System.out.println("TEST CASE - 06: INPUT: " + order6.toString());
                orderController.checkout(order6);
            } catch (Exception e) {
            }


        };
    }


}
