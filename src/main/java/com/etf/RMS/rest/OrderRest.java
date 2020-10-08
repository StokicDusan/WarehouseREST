package com.etf.RMS.rest;

import com.etf.RMS.data.Customer;
import com.etf.RMS.data.Employee;
import com.etf.RMS.data.Order;
import com.etf.RMS.data.Shipper;
import com.etf.RMS.exception.WarehouseException;
import com.etf.RMS.service.OrderService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("order")
public class OrderRest {

    private final OrderService orderService = OrderService.getInstance();

    /*
    Deo vezan za order
     */
    @GET
    @Path("/{order_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrderById(@PathParam("order_id") int order_id) throws WarehouseException {
        return orderService.findOrder(order_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getOrders() throws WarehouseException {
        return orderService.findAllOrder();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Order order) throws WarehouseException {
        orderService.addNewOrder(order);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrd(Order order) throws WarehouseException {
        orderService.updateOrder(order);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{order_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrd(@PathParam("order_id") int order_id) throws WarehouseException {
        orderService.deleteOrder(order_id);
        return Response.ok().build();
    }

    /*
    Deo vezan za shipper
     */

    @GET
    @Path("/shipper/{shipper_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shipper getShipperById(@PathParam("shipper_id") int shipper_id) throws WarehouseException {
        return orderService.findShipper(shipper_id);
    }

    @GET
    @Path("/shipper")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipper> getShipper() throws WarehouseException {
        return orderService.findAllShipper();
    }

    @POST
    @Path("/shipper")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addShipper(Shipper shipper) throws WarehouseException {
        orderService.addNewShipper(shipper);
        return Response.ok().build();
    }

    @PUT
    @Path("/shipper")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShipp(Shipper shipper) throws WarehouseException {
        orderService.updateShipper(shipper);
        return Response.ok().build();
    }

    @DELETE
    @Path("/shipper/{shipper_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShipp(@PathParam("shipper_id") int shipper_id) throws WarehouseException {
        orderService.deleteShipper(shipper_id);
        return Response.ok().build();
    }

    /*
    Deo vezan za customer
     */

    @GET
    @Path("/customer/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("customer_id") int customer_id) throws WarehouseException {
        return orderService.findCustomer(customer_id);
    }

    @GET
    @Path("/customer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomer() throws WarehouseException {
        return orderService.findAllCustomer();
    }

    @POST
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) throws WarehouseException {
        orderService.addNewCustomer(customer);
        return Response.ok().build();
    }

    @PUT
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCust(Customer customer) throws WarehouseException {
        orderService.updateCustomer(customer);
        return Response.ok().build();
    }

    @DELETE
    @Path("/customer/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCust(@PathParam("customer_id") int customer_id) throws WarehouseException {
        orderService.deleteCustomer(customer_id);
        return Response.ok().build();
    }

    /*
    Deo vezan za employee
     */
    @GET
    @Path("/employee/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(@PathParam("employee_id") int employee_id) throws WarehouseException {
        return orderService.findEmployee(employee_id);
    }

    @GET
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployee() throws WarehouseException {
        return orderService.findAllEmployee();
    }

    @POST
    @Path("/employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) throws WarehouseException {
        orderService.addNewEmployee(employee);
        return Response.ok().build();
    }

    @PUT
    @Path("/employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmpl(Employee employee) throws WarehouseException {
        orderService.addNewEmployee(employee);
        return Response.ok().build();
    }

    @DELETE
    @Path("/employee/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmpl(@PathParam("employee_id") int employee_id) throws WarehouseException {
        orderService.deleteEmployee(employee_id);
        return Response.ok().build();
    }
}
