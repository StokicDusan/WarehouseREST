package com.etf.RMS.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class Customer implements Serializable {

    /*
    Atributi klase su kolone
    u tabeli u bazi podataka
     */
    private int customer_id;
    private String customer_name;
    private String contact_person;
    private String address;
    private String city;
    private int postcode;
    private String country;

    public Customer() {
    }

    /*
    Konstruktori sa i bez id-a iz baze
     */
    public Customer(int customer_id, String customer_name, String contact_person, String address, String city, int postcode, String country) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
    }

    public Customer(String customer_name, String contact_person, String address, String city, int postcode, String country) {
        this.customer_name = customer_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
    }

    /*
    Geteri i seteri svih atributa klase
     */
    public int getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getContact_person() {
        return contact_person;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{customer_id=").append(customer_id);
        sb.append(", customer_name=").append(customer_name);
        sb.append(", contact_person=").append(contact_person);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", postcode=").append(postcode);
        sb.append(", country=").append(country);
        sb.append('}');
        return sb.toString();
    }

}
