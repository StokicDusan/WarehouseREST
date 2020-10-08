package com.etf.RMS.dao;

import com.etf.RMS.data.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao {

    private static final SupplierDao instance = new SupplierDao();

    public SupplierDao() {
    }

    public static SupplierDao getInstance() {
        return instance;
    }

    public void create(Supplier supplier, Connection con) throws SQLException {
        /*
        Dodajemo novi red u supplier tabeli
        u bazi podataka
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `supplier`(`supplier_name`, `contact_person`, `address`, `city`, `postcode`, `country`, `phone`) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, supplier.getSupplier_name());
            ps.setString(2, supplier.getContact_person());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setInt(5, supplier.getPostcode());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(Supplier supplier, Connection con) throws SQLException {
        /*
        Ažuriramo zapis u supplier tabeli po id-u 
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `supplier` SET `supplier_name`=?,`contact_person`=?,`address`=?,`city`=?,`postcode`=?,`country`=?,`phone`=? WHERE `supplier_id`=?");
            ps.setString(1, supplier.getSupplier_name());
            ps.setString(2, supplier.getContact_person());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setInt(5, supplier.getPostcode());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());
            ps.setInt(8, supplier.getSupplier_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int supplier_id, Connection con) throws SQLException {
        /*
        Brišemo člana tabele supplier
        sa zadatim id-jem
         */
        PreparedStatement ps = null;
        try {
            Supplier supplier = SupplierDao.getInstance().find(supplier_id, con);

            /*
            Kada brišemo supplier, brišemo i product
            po stranom ključu supplier_id
             */
            ProductDao.getInstance().delete(supplier, con);
            ps = con.prepareStatement("DELETE FROM `supplier` WHERE `supplier_id`=?");
            ps.setInt(1, supplier_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public Supplier find(int supplier_id, Connection con) throws SQLException {
        /*
        Vraćamo instancu klase supplier
        iz baze pronađena preko id-a
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `supplier` WHERE `supplier_id`=?");
            ps.setInt(1, supplier_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                supplier = new Supplier(rs.getInt("supplier_id"), rs.getString("supplier_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("postcode"), rs.getString("country"), rs.getString("phone"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }

    public Supplier find(String supplier_name, Connection con) throws SQLException {
        /*
        Vraćamo instancu klase customer
        iz baze pronađena preko imena.
        Ova metoda je dodata za testiranje, nije 
        iskorišćena u REST sloju
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `supplier` WHERE `supplier_name`=?");
            ps.setString(1, supplier_name);
            rs = ps.executeQuery();
            if (rs.next()) {
                supplier = new Supplier(rs.getInt("supplier_id"), rs.getString("supplier_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("postcode"), rs.getString("country"), rs.getString("phone"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }

    public List<Supplier> findall(Connection con) throws SQLException {
        /*
        Vraćamo zapise iz tabele supplier iz baze
        kao listu instanci klase
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Supplier> supplierList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `supplier`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getInt("supplier_id"), rs.getString("supplier_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("postcode"), rs.getString("country"), rs.getString("phone"));
                supplierList.add(supplier);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplierList;
    }

}
