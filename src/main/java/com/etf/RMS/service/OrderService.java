package com.etf.RMS.service;

import com.etf.RMS.dao.CustomerDao;
import com.etf.RMS.dao.EmployeeDao;
import com.etf.RMS.dao.OrderDao;
import com.etf.RMS.dao.ResourcesManager;
import com.etf.RMS.dao.ShipperDao;
import com.etf.RMS.data.Customer;
import com.etf.RMS.data.Employee;
import com.etf.RMS.data.Order;
import com.etf.RMS.data.Shipper;
import com.etf.RMS.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {

    private static final OrderService instance = new OrderService();

    public OrderService() {
    }

    public static OrderService getInstance() {
        return instance;
    }

    /*
    Deo vezan za Order
     */
    public void addNewOrder(Order order) throws WarehouseException {

        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            OrderDao.getInstance().create(order, con);
            con.commit();

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new order " + order, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Order findOrder(int order_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OrderDao.getInstance().find(order_id, con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find order " + order_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Order> findAllOrder() throws WarehouseException {

        Connection con = null;
        try {

            con = ResourcesManager.getConnection();
            return OrderDao.getInstance().findall(con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find orders ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteOrder(int order_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Order order = OrderDao.getInstance().find(order_id, con);
            if (order != null) {
                OrderDao.getInstance().delete(order.getOrder_id(), con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete order " + order_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateOrder(Order order) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OrderDao.getInstance().update(order, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update order " + order, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Deo vezan za Shipper
     */
    public void addNewShipper(Shipper shipper) throws WarehouseException {

        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            ShipperDao.getInstance().create(shipper, con);
            con.commit();

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new shipper " + shipper, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Shipper findShipper(int shipper_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ShipperDao.getInstance().find(shipper_id, con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find shipper " + shipper_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Shipper> findAllShipper() throws WarehouseException {

        Connection con = null;
        try {

            con = ResourcesManager.getConnection();
            return ShipperDao.getInstance().findall(con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find shippers ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteShipper(int shipper_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Shipper shipper = ShipperDao.getInstance().find(shipper_id, con);
            if (shipper != null) {
                ShipperDao.getInstance().delete(shipper.getShipper_id(), con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete shipper " + shipper_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateShipper(Shipper shipper) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ShipperDao.getInstance().update(shipper, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update shipper " + shipper, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Deo vezan za Customer
     */
    public void addNewCustomer(Customer customer) throws WarehouseException {

        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            CustomerDao.getInstance().create(customer, con);
            con.commit();

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new customer " + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Customer findCustomer(int customer_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return CustomerDao.getInstance().find(customer_id, con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find customer " + customer_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Customer> findAllCustomer() throws WarehouseException {

        Connection con = null;
        try {

            con = ResourcesManager.getConnection();
            return CustomerDao.getInstance().findall(con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find customers ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteCustomer(int customer_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Customer customer = CustomerDao.getInstance().find(customer_id, con);
            if (customer != null) {
                CustomerDao.getInstance().delete(customer.getCustomer_id(), con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete customer " + customer_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateCustomer(Customer customer) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            CustomerDao.getInstance().update(customer, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update customer " + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    /*
    Deo vezan za Employee
     */
    public void addNewEmployee(Employee employee) throws WarehouseException {

        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);

            EmployeeDao.getInstance().create(employee, con);
            con.commit();

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new employee " + employee, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Employee findEmployee(int employee_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return EmployeeDao.getInstance().find(employee_id, con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find employee " + employee_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Employee> findAllEmployee() throws WarehouseException {

        Connection con = null;
        try {

            con = ResourcesManager.getConnection();
            return EmployeeDao.getInstance().findall(con);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find employees ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteEmployee(int employee_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Employee employee = EmployeeDao.getInstance().find(employee_id, con);
            if (employee != null) {
                EmployeeDao.getInstance().delete(employee.getEmployee_id(), con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete employee " + employee_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateEmployee(Employee employee) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            EmployeeDao.getInstance().update(employee, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update employee " + employee, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
