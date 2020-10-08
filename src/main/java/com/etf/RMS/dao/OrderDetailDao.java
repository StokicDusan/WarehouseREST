package com.etf.RMS.dao;

import com.etf.RMS.data.Order;
import com.etf.RMS.data.OrderDetail;
import com.etf.RMS.data.Product;
import com.etf.RMS.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao {

    private static final OrderDetailDao instance = new OrderDetailDao();

    public OrderDetailDao() {
    }

    public static OrderDetailDao getInstance() {
        return instance;
    }

    public void create(OrderDetail orderDetail, Connection con) throws SQLException, WarehouseException {
        /*
        Dodajemo novi red u order_detail tabeli
        u bazi podataka
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `order_detail`(`order_id`, `product_id`, `quantity`) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            /*
            Proveravamo da li postoje članovi u tabelama sa
            privatnim ključevima jednakim zadatim 
            stranim ključevima člana order_detail tabele
            koje želimo da dodamo
             */
            Order order = OrderDao.getInstance().find(orderDetail.getOrder().getOrder_id(), con);
            if (order == null) {
                throw new WarehouseException("Order " + orderDetail.getOrder().getOrder_date() + " doesn't exist in database.");
            }
            ps.setInt(1, order.getOrder_id());

            Product product = ProductDao.getInstance().find(orderDetail.getProduct().getProduct_id(), con);
            if (product == null) {
                throw new WarehouseException("Product " + orderDetail.getProduct().getProduct_name() + " doesn't exist in database.");
            }
            ps.setInt(2, product.getProduct_id());

            /*
            ako nije upisana vrednost za quantity,
            podrazumevano je 1
             */
            int quantity = 1;
            if (orderDetail.getQuantity() != 1) {
                quantity = orderDetail.getQuantity();
            }
            ps.setInt(3, quantity);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(OrderDetail orderDetail, Connection con) throws SQLException {
        /*
        Ažuriramo zapis u order_detail tabeli po id-u 
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `order_detail` SET `order_id`=?,`product_id`=?,`quantity`=? WHERE `order_detail_id`=?");
            ps.setInt(1, orderDetail.getOrder().getOrder_id());
            ps.setInt(2, orderDetail.getProduct().getProduct_id());
            ps.setInt(3, orderDetail.getQuantity());
            ps.setInt(4, orderDetail.getOrder_detail_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Order order, Connection con) throws SQLException {
        /*
        Brišemo člana tabele order_detail
        sa zadatim order id-jem
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `order_detail` WHERE `order_id`=?");
            ps.setInt(1, order.getOrder_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Product product, Connection con) throws SQLException {
        /*
        Brišemo člana tabele order_detail
        sa zadatim product id-jem
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `order_detail` WHERE `product_id`=?");
            ps.setInt(1, product.getProduct_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public OrderDetail find(int order_detail_id, Connection con) throws SQLException {
        /*
        Vraćamo instancu klase order_detail
        iz baze pronađena preko id-a
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDetail orderDetail = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `order_detail` WHERE `order_detail_id`=?");
            ps.setInt(1, order_detail_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Order order = OrderDao.getInstance().find(rs.getInt("order_id"), con);
                Product product = ProductDao.getInstance().find(rs.getInt("product_id"), con);
                orderDetail = new OrderDetail(rs.getInt("order_detail_id"), order, product, rs.getInt("quantity"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetail;
    }

    public List<OrderDetail> findall(Connection con) throws SQLException {
        /*
        Vraćamo zapise iz tabele order_detail iz baze
        kao listu instanci klase
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `order_detail`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = OrderDao.getInstance().find(rs.getInt("order_id"), con);
                Product product = ProductDao.getInstance().find(rs.getInt("product_id"), con);
                OrderDetail orderDetail = new OrderDetail(rs.getInt("order_detail_id"), order, product, rs.getInt("quantity"));
                orderDetailList.add(orderDetail);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetailList;
    }

}
