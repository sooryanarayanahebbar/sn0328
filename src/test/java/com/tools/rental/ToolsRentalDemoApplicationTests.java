package com.tools.rental;

/*
@SpringBootTest
@AutoConfigureMockMvc
class ToolsRentalDemoApplicationTests {

    @Autowired
    private OrderController orderController;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService service;
    @Autowired
    private ToolService toolService;


    @Mock
    ToolDetailsBean toolsDetails;

    @Autowired
    private Map<String, ToolDetailsBean> toolDetailsBeansMap;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(orderController);
    }


    OrderResponse OrderResponse;

    //@Test
    void testCase() throws Exception {
        List<OrderDto> orderList = new ArrayList<>();
        OrderDto request = new OrderDto("LADW", "02/2/24", 5, 10);
        orderList.add(request);


        ToolDetailsBean toolsDetails = new ToolDetailsBean();
        List<ToolRental> rentalToolResponse = new ArrayList<>();
        ToolRental t = new ToolRental(toolsDetails, request);
        rentalToolResponse.add(t);
        OrderResponse OrderResponse = new OrderResponse(rentalToolResponse);
        when(service.checkoutProcess(orderList)).thenReturn(OrderResponse);
        when(toolService.toolDetailsByCode("LADW")).thenReturn(toolDetailsBeansMap.get("LADW"));
        //this.mockMvc.perform(get("/api/v1/checkout/order")).andDo(print()).andExpect(status().is4xxClientError())
        //.andExpect(content().string(containsString("")));
        ApplicationResponse responseEntity = orderController.checkout(request);

    }

}
*/