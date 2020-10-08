package com.etf.RMS.rest;

import com.etf.RMS.data.OrderDetail;
import com.etf.RMS.exception.WarehouseException;
import com.etf.RMS.service.OrderDetailService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("order_detail")
public class OrderDetailRest {

    private final OrderDetailService orderDetailService = OrderDetailService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeOrderDetail(OrderDetail orderDetail) throws WarehouseException {
        orderDetailService.makeOrderDetail(orderDetail.getOrder(), orderDetail.getProduct(), orderDetail.getQuantity());
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDetail> getOrderDetails() throws WarehouseException {
        return orderDetailService.findAllOrderDetails();
    }
}
