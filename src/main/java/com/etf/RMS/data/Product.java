package com.etf.RMS.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class Product implements Serializable {

    /*
    Atributi klase su kolone
    u tabeli u bazi podataka
     */
    private int product_id;
    private String product_name;
    private Supplier supplier;
    private String product_category;
    private int price_per_unit;

    public Product() {
    }

    /*
    Konstruktori sa i bez id-a iz baze
     */
    public Product(int product_id, String product_name, Supplier supplier, String product_category, int price_per_unit) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.supplier = supplier;
        this.product_category = product_category;
        this.price_per_unit = price_per_unit;
    }

    public Product(String product_name, Supplier supplier, String product_category, int price_per_unit) {
        this.product_name = product_name;
        this.supplier = supplier;
        this.product_category = product_category;
        this.price_per_unit = price_per_unit;
    }

    /*
    Geteri i seteri svih atributa klase
     */
    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public String getProduct_category() {
        return product_category;
    }

    public int getPrice_per_unit() {
        return price_per_unit;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public void setPrice_per_unit(int price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{product_id=").append(product_id);
        sb.append(", product_name=").append(product_name);
        sb.append(", supplier=").append(supplier);
        sb.append(", product_category=").append(product_category);
        sb.append(", price_per_unit=").append(price_per_unit);
        sb.append('}');
        return sb.toString();
    }

}
