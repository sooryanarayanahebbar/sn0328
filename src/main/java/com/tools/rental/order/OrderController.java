package com.tools.rental.order;

import com.tools.rental.admin.tool.ToolService;
import com.tools.rental.model.dto.ApplicationResponse;
import com.tools.rental.model.dto.OrderResponse;
import com.tools.rental.util.ToolsRentalHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/checkout")
public class OrderController {


    private final OrderService orderService;
    private final ToolService toolService;

    @RequestMapping("/health")
    public @ResponseBody String greeting() {
        return "I am Okay!!";
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/orders")
    public ApplicationResponse checkout(@RequestBody List<OrderDto> orderList) {

        List<String> errors = ToolsRentalHelper.validateOrders(orderList);

        if (errors.isEmpty()) {
            OrderResponse response = orderService.checkoutProcess(orderList);
            return new ApplicationResponse("SUCCESS", HttpStatus.OK.toString(), "/checkout", response, null);
        } else {
            ApplicationResponse response = new ApplicationResponse("Validation Errors", HttpStatus.BAD_REQUEST.toString(), "/checkout", null, errors);
            return response;
        }

    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/order")
    public ApplicationResponse checkout(@RequestBody OrderDto order) {

        List<String> errors = ToolsRentalHelper.validateOrders(order);

        if (errors.isEmpty()) {
            List<OrderDto> orderList = new ArrayList<>(1);
            orderList.add(order);
            OrderResponse response = orderService.checkoutProcess(orderList);
            return new ApplicationResponse("SUCCESS", HttpStatus.OK.toString(), "/checkout", response, null);
        } else {
            System.out.println("\n****************************************************\n");
            System.out.println("-------------------- Error ---------------------------\n");
            for (String error : errors)
                System.out.println(error);
            System.out.println("\n*************** Please verify you request **********\n");
            ApplicationResponse response = new ApplicationResponse("Validation Errors", HttpStatus.BAD_REQUEST.toString(), "/checkout", null, errors);
            return response;
        }

    }


}

