package com.etf.RMS.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class Shipper implements Serializable {

    /*
    Atributi klase su kolone
    u tabeli u bazi podataka
     */
    private int shipper_id;
    private String shipper_name;
    private String phone;

    public Shipper() {
    }

    /*
    Konstruktori sa i bez id-a iz baze
     */
    public Shipper(int shipper_id, String shipper_name, String phone) {
        this.shipper_id = shipper_id;
        this.shipper_name = shipper_name;
        this.phone = phone;
    }

    public Shipper(String shipper_name, String phone) {
        this.shipper_name = shipper_name;
        this.phone = phone;
    }

    /*
    Geteri i seteri svih atributa klase
     */
    public int getShipper_id() {
        return shipper_id;
    }

    public String getShipper_name() {
        return shipper_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setShipper_id(int shipper_id) {
        this.shipper_id = shipper_id;
    }

    public void setShipper_name(String shipper_name) {
        this.shipper_name = shipper_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shipper{shipper_id").append(shipper_id);
        sb.append(", shipper_name=").append(shipper_name);
        sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }

}
