package com.etf.RMS.service;

import com.etf.RMS.dao.ProductDao;
import com.etf.RMS.dao.ResourcesManager;
import com.etf.RMS.dao.SupplierDao;
import com.etf.RMS.data.Product;
import com.etf.RMS.data.Supplier;
import com.etf.RMS.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dusan
 */
public class ProductService {

    private static final ProductService instance = new ProductService();

    public ProductService() {
    }

    public static ProductService getInstance() {
        return instance;
    }

    /*
    Deo vezan za Product
     */
    public void addNewProduct(Product product) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ProductDao.getInstance().create(product, con);
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Product findProduct(int product_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ProductDao.getInstance().find(product_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find product " + product_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Product> findAllProduct() throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ProductDao.getInstance().findall(con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find products ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteProduct(int product_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Product product = ProductDao.getInstance().find(product_id, con);
            if (product != null) {
                ProductDao.getInstance().delete(product_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete product " + product_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateProduct(Product product) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            ProductDao.getInstance().update(product, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to update product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Deo vezan za Supplier
     */

    public void addNewSupplier(Supplier supplier) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            SupplierDao.getInstance().create(supplier, con);
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new supplier " + supplier, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Supplier findSupplier(int supplier_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return SupplierDao.getInstance().find(supplier_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find supplier " + supplier_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Supplier> findAllSupplier() throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return SupplierDao.getInstance().findall(con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find suppliers ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteSupplier(int supplier_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Supplier supplier = SupplierDao.getInstance().find(supplier_id, con);
            if (supplier != null) {
                SupplierDao.getInstance().delete(supplier_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete supplier " + supplier_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateSupplier(Supplier supplier) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            SupplierDao.getInstance().update(supplier, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to update supplier " + supplier, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
