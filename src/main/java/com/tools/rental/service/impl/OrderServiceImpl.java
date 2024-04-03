package com.tools.rental.service.impl;

import org.springframework.stereotype.Service;

import com.tools.rental.model.dto.OrderDto;
import com.tools.rental.model.dto.ToolRental;
import com.tools.rental.model.entity.ToolDetailsBean;
import com.tools.rental.service.OrderService;
import com.tools.rental.service.ToolService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ToolService toolService;

    @Override
    public ToolRental checkoutProcess(OrderDto order) {
        
            ToolDetailsBean toolsDetails = toolService.toolDetailsByCode(order.getToolCode());
            ToolRental toolCharges = new ToolRental(toolsDetails, order);
            System.out.println("****************************************************\n");
            System.out.println("------------------Rental Agreement------------------\n");
            System.out.println(toolCharges.printRentalAgreement());
            System.out.println("*************** Have a nice Day ********************\n");
            System.out.println("****************************************************\n");

        return toolCharges;
    }

}
