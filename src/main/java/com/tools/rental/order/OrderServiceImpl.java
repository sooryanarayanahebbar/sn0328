package com.tools.rental.order;

import com.tools.rental.admin.tool.ToolDetailsBean;
import com.tools.rental.admin.tool.ToolService;
import com.tools.rental.model.dto.OrderResponse;
import com.tools.rental.model.dto.ToolRental;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ToolService toolService;

    @Override
    public OrderResponse checkoutProcess(List<OrderDto> orderList) {
        List<ToolRental> rentalToolResponse = new ArrayList<>();
        for (OrderDto dto : orderList) {
            ToolDetailsBean toolsDetails = toolService.toolDetailsByCode(dto.getToolCode());
            ToolRental toolCharges = new ToolRental(toolsDetails, dto);
            rentalToolResponse.add(toolCharges);
            System.out.println("****************************************************\n");
            System.out.println("------------------Rental Agreement------------------\n");
            System.out.println(toolCharges.printRentalAgreement());
            System.out.println("*************** Have a nice Day ********************\n");
            System.out.println("****************************************************\n");

        }
        OrderResponse orderResponse = new OrderResponse(rentalToolResponse);
        /////orderResponse.printRentalAgreement();
        //System.out.println(Json.pretty(orderList).toString());
        //System.out.println(Json.pretty(orderList).toString());
        //System.out.println("*********************************************");
        //System.out.println(Json.pretty(toolsDetails).toString());


        return orderResponse;
    }

}
