package com.etf.RMS.rest;

import com.etf.RMS.data.Product;
import com.etf.RMS.data.Supplier;
import com.etf.RMS.exception.WarehouseException;
import com.etf.RMS.service.ProductService;
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

@Path("product")
public class ProductRest {

    private final ProductService productService = ProductService.getInstance();

    /*
    Deo vezan za product
     */
    @GET
    @Path("/{product_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("product_id") int product_id) throws WarehouseException {
        return productService.findProduct(product_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() throws WarehouseException {
        return productService.findAllProduct();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) throws WarehouseException {
        productService.addNewProduct(product);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProd(Product product) throws WarehouseException {
        productService.updateProduct(product);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{product_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProd(@PathParam("product_id") int product_id) throws WarehouseException {
        productService.deleteProduct(product_id);
        return Response.ok().build();
    }

    /*
    Deo vezan za supplier
     */
    @GET
    @Path("/supplier/{supplier_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier getSupplierById(@PathParam("supplier_id") int supplier_id) throws WarehouseException {
        return productService.findSupplier(supplier_id);
    }

    @GET
    @Path("/supplier")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Supplier> getSupplier() throws WarehouseException {
        return productService.findAllSupplier();
    }

    @POST
    @Path("/supplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSupplier(Supplier supplier) throws WarehouseException {
        productService.addNewSupplier(supplier);
        return Response.ok().build();
    }

    @PUT
    @Path("/supplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupp(Supplier supplier) throws WarehouseException {
        productService.updateSupplier(supplier);
        return Response.ok().build();
    }

    @DELETE
    @Path("/supplier/{supplier_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupp(@PathParam("supplier_id") int supplier_id) throws WarehouseException {
        productService.deleteSupplier(supplier_id);
        return Response.ok().build();
    }

}
