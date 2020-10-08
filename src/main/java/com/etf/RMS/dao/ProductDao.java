package com.etf.RMS.dao;

import com.etf.RMS.data.Product;
import com.etf.RMS.data.Supplier;
import com.etf.RMS.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final ProductDao instance = new ProductDao();

    public ProductDao() {
    }

    public static ProductDao getInstance() {
        return instance;
    }

    public void create(Product product, Connection con) throws SQLException, WarehouseException {
        /*
        Dodajemo novi red u product tabeli
        u bazi podataka
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `product`(`product_name`, `supplier_id`, `product_category`, `price_per_unit`) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProduct_name());

            /*
            Proveravamo da li postoji član u tabeli sa
            privatnim ključem jednakim zadatim 
            stranim ključen člana product tabele
            koje želimo da dodamo
             */
            Supplier supplier = SupplierDao.getInstance().find(product.getSupplier().getSupplier_id(), con);
            if (supplier == null) {
                throw new WarehouseException("Supplier " + product.getSupplier().getSupplier_name() + " doesn't exist in database.");
            }
            ps.setInt(2, supplier.getSupplier_id());

            /*
            Product_category može da se ne definiše,
            podrazumevana vrednost je null
             */
            String productCategory = null;
            if (product.getProduct_category() != null) {
                productCategory = product.getProduct_category();
            }
            ps.setString(3, productCategory);
            ps.setInt(4, product.getPrice_per_unit());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(Product product, Connection con) throws SQLException {
        /*
        Ažuriramo zapis u product tabeli po id-u 
         */
        PreparedStatement ps = null;
        System.out.println(product);
        try {
            ps = con.prepareStatement("UPDATE `product` SET `product_name`=?,`supplier_id`=?,`product_category`=?,`price_per_unit`=? WHERE `product_id`=?");
            ps.setString(1, product.getProduct_name());
            ps.setInt(2, product.getSupplier().getSupplier_id());
            String productCategory = null;
            if (product.getProduct_category() != null) {
                productCategory = product.getProduct_category();
            }
            ps.setString(3, productCategory);
            ps.setInt(4, product.getPrice_per_unit());
            ps.setInt(5, product.getProduct_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int product_id, Connection con) throws SQLException {
        /*
        Brišemo člana tabele product
        sa zadatim id-jem
         */
        PreparedStatement ps = null;
        try {
            Product product = ProductDao.getInstance().find(product_id, con);

            /*
            Kada brišemo product, brišemo i order_detail
            po stranom ključu product_id
             */
            OrderDetailDao.getInstance().delete(product, con);

            ps = con.prepareStatement("DELETE FROM `product` WHERE `product_id`=?");
            ps.setInt(1, product.getProduct_id());
            ps.executeUpdate();

            /*
            mozda ne treba da se briše supplier
            SupplierDao.getInstance().delete(product.getSupplier().getSupplier_id(), con);
             */
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Supplier supplier, Connection con) throws SQLException {
        /*
        Brišemo člana tabele product
        sa zadatim supplier id-jem
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `product` WHERE `supplier_id`=?");
            ps.setInt(1, supplier.getSupplier_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public Product find(int product_id, Connection con) throws SQLException {
        /*
        Vraćamo instancu klase product
        iz baze pronađena preko id-a
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `product` WHERE `product_id`=?");
            ps.setInt(1, product_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(rs.getInt("supplier_id"), con);
                String productCategory = null;
                if (rs.getString("product_category") != null) {
                    productCategory = rs.getString("product_category");
                }
                product = new Product(rs.getInt("product_id"), rs.getString("product_name"), supplier, productCategory, rs.getInt("price_per_unit"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }

    public List<Product> findall(Connection con) throws SQLException {
        /*
        Vraćamo zapise iz tabele product iz baze
        kao listu instanci klase
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `product`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(rs.getInt("supplier_id"), con);
                String productCategory = null;
                if (rs.getString("product_category") != null) {
                    productCategory = rs.getString("product_category");
                }
                Product product = new Product(rs.getInt("product_id"), rs.getString("product_name"), supplier, productCategory, rs.getInt("price_per_unit"));
                productList.add(product);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return productList;
    }

}
