package com.tools.rental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tools.rental.config.AppConstants;
import com.tools.rental.config.OrderPayloadMessageProps;
import com.tools.rental.model.dto.ApplicationResponse;
import com.tools.rental.model.dto.OrderDto;
import com.tools.rental.model.dto.ToolRental;
import com.tools.rental.service.OrderService;
import com.tools.rental.util.ToolsRentalHelper;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/checkout")
public class OrderController {
  
	private final OrderPayloadMessageProps orderPayloadMessageProps;


    private final OrderService orderService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/order")
    public ApplicationResponse checkout(@RequestBody OrderDto order) {

        List<String> errors = ToolsRentalHelper.validateOrders(order, orderPayloadMessageProps);

        if (errors.isEmpty()) {
            ToolRental response = orderService.checkoutProcess(order);
            return new ApplicationResponse(AppConstants.APP_RESPONSE_MESSAGE_SUCCESS, HttpStatus.OK.toString(), "/checkout", response, null);
        } else {
            System.out.println("\n****************************************************\n");
            System.out.println("-------------------- Error ---------------------------\n");
            for (String error : errors)
                System.out.println(error);
            System.out.println("\n*************** Please verify you request **********\n");
            ApplicationResponse response = new ApplicationResponse(AppConstants.APP_RESPONSE_MESSAGE_VALIDATION_ERROR, HttpStatus.BAD_REQUEST.toString(), "/checkout", null, errors);
            return response;
        }

    }


}

