package com.etf.RMS.service;

import com.etf.RMS.dao.OrderDetailDao;
import com.etf.RMS.dao.ResourcesManager;
import com.etf.RMS.data.Order;
import com.etf.RMS.data.OrderDetail;
import com.etf.RMS.data.Product;
import com.etf.RMS.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dusan
 */
public class OrderDetailService {

    private static final OrderDetailService instance = new OrderDetailService();

    public OrderDetailService() {
    }

    public static OrderDetailService getInstance() {
        return instance;
    }

    public void makeOrderDetail(Order order, Product product, int quantity) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OrderDetail orderDetail = new OrderDetail(order, product, quantity);
            OrderDetailDao.getInstance().create(orderDetail, con);

            con.commit();

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to make order datail", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }

    }

    public List<OrderDetail> findAllOrderDetails() throws WarehouseException {

        Connection con = null;
        try {

            con = ResourcesManager.getConnection();
            return OrderDetailDao.getInstance().findall(con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find order details ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

}
