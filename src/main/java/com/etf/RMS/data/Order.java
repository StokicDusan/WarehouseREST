package com.etf.RMS.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class Order implements Serializable {

    /*
    Atributi klase su kolone
    u tabeli u bazi podataka
     */
    private int order_id;
    private String order_date;
    private Customer customer;
    private Employee employee;
    private Shipper shipper;

    public Order() {
    }

    /*
    Konstruktori sa i bez id-a iz baze
     */
    public Order(int order_id, String order_date, Customer customer, Employee employee, Shipper shipper) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }

    public Order(String order_date, Customer customer, Employee employee, Shipper shipper) {
        this.order_date = order_date;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }

    /*
    Geteri i seteri svih atributa klase
     */
    public int getOrder_id() {
        return order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{order_id=").append(order_id);
        sb.append(", order_date=").append(order_date);
        sb.append(", customer=").append(customer);
        sb.append(", employee=").append(employee);
        sb.append(", shipper=").append(shipper);
        sb.append('}');
        return sb.toString();
    }

}
