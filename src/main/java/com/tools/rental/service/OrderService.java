package com.tools.rental.service;

import com.tools.rental.model.dto.OrderDto;
import com.tools.rental.model.dto.ToolRental;


public interface OrderService {

    public ToolRental checkoutProcess(OrderDto order);


}
