package com.etf.RMS.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class OrderDetail implements Serializable {

    /*
    Atributi klase su kolone
    u tabeli u bazi podataka
     */
    private int order_detail_id;
    private Order order;
    private Product product;
    private int quantity;

    public OrderDetail() {
    }

    /*
    Konstruktori sa i bez id-a iz baze
     */
    public OrderDetail(int order_detail_id, Order order, Product product, int quantity) {
        this.order_detail_id = order_detail_id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderDetail(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    /*
    Geteri i seteri svih atributa klase
     */
    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OrderDetail{order_detail_id=").append(order_detail_id);
        sb.append(", order=").append(order);
        sb.append(", product=").append(product);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

}
