package com.etf.RMS.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625 
 */
public class Supplier implements Serializable {
    
    /*
    Atributi klase su kolone
    u tabeli u bazi podataka
    */
    private int supplier_id;
    private String supplier_name;
    private String contact_person;
    private String address;
    private String city;
    private int postcode;
    private String country;
    private String phone;

    public Supplier() {
    }
    
    /*
    Konstruktori sa i bez id-a iz baze
    */
    
    public Supplier(int supplier_id, String supplier_name, String contact_person, String address, String city, int postcode, String country, String phone) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.phone = phone;
    }

    public Supplier(String supplier_name, String contact_person, String address, String city, int postcode, String country, String phone) {
        this.supplier_name = supplier_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.phone = phone;
    }
    
    /*
    Geteri i seteri svih atributa klase
    */
        
    public int getSupplier_id() {
        return supplier_id;
    }
    
    public String getSupplier_name() {
        return supplier_name;
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

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }
    
    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Supplier{supplier_id=").append(supplier_id);
        sb.append(", supplier_name=").append(supplier_name);
        sb.append(", contact_person=").append(contact_person);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", postcode=").append(postcode);
        sb.append(", country=").append(country);
        sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }

}
