package com.etf.RMS.dao;

import com.etf.RMS.data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private static final CustomerDao instance = new CustomerDao();

    public CustomerDao() {
    }

    public static CustomerDao getInstance() {
        return instance;
    }

    public void create(Customer customer, Connection con) throws SQLException {
        /*
        Dodajemo novi red u customer tabeli
        u bazi podataka
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `customer`(`customer_name`, `contact_person`, `address`, `city`, `postcode`, `country`) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, customer.getContact_person());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setInt(5, customer.getPostcode());
            ps.setString(6, customer.getCountry());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(Customer customer, Connection con) throws SQLException {
        /*
        Ažuriramo zapis u customer tabeli po id-u 
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `customer` SET `customer_name`=?,`contact_person`=?,`address`=?,`city`=?,`postcode`=?,`country`=? WHERE `customer_id`=?");
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, customer.getContact_person());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setInt(5, customer.getPostcode());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, customer.getCustomer_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int customer_id, Connection con) throws SQLException {
        /*
        Brišemo člana tabele customer
        sa zadatim id-jem
         */
        PreparedStatement ps = null;
        try {
            Customer customer = CustomerDao.getInstance().find(customer_id, con);

            /*
            Kada brišemo customer, brišemo i order
            po stranom ključu customer_id
             */
            OrderDao.getInstance().delete(customer, con);
            ps = con.prepareStatement("DELETE FROM `customer` WHERE `customer_id`=?");
            ps.setInt(1, customer_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public Customer find(int customer_id, Connection con) throws SQLException {
        /*
        Vraćamo instancu klase customer
        iz baze pronađena preko id-a
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `customer` WHERE `customer_id`=?");
            ps.setInt(1, customer_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getInt("customer_id"), rs.getString("customer_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("postcode"), rs.getString("country"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    }

    public List<Customer> findall(Connection con) throws SQLException {
        /*
        Vraćamo zapise iz tabele customer iz baze
        kao listu instanci klase
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `customer`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("customer_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("postcode"), rs.getString("country"));
                customerList.add(customer);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customerList;
    }

}
