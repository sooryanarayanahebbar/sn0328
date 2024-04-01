package com.tools.rental.order;

import com.tools.rental.model.dto.OrderResponse;

import java.util.List;


public interface OrderService {

    public OrderResponse checkoutProcess(List<OrderDto> orderList);


}
