package com.etf.RMS.dao;

import com.etf.RMS.data.Customer;
import com.etf.RMS.data.Employee;
import com.etf.RMS.data.Order;
import com.etf.RMS.data.Shipper;
import com.etf.RMS.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static final OrderDao instance = new OrderDao();

    public OrderDao() {
    }

    public static OrderDao getInstance() {
        return instance;
    }

    public void create(Order order, Connection con) throws SQLException, WarehouseException {
        /*
        Dodajemo novi red u order tabeli
        u bazi podataka
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `order`(`order_date`, `customer_id`, `employee_id`, `shipper_id`) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getOrder_date());

            /*
            Proveravamo da li postoje članovi u tabelama sa
            privatnim ključevima jednakim zadatim 
            stranim ključevima člana order tabele
            koje želimo da ubacimo
             */
            Customer customer = CustomerDao.getInstance().find(order.getCustomer().getCustomer_id(), con);
            if (customer == null) {
                throw new WarehouseException("Customer " + order.getCustomer().getCustomer_name() + " doesn't exist in database.");
            }
            ps.setInt(2, customer.getCustomer_id());

            Employee employee = EmployeeDao.getInstance().find(order.getEmployee().getEmployee_id(), con);
            if (employee == null) {
                throw new WarehouseException("Employee " + order.getEmployee().getLast_name() + " " + order.getEmployee().getFirst_name() + " doesn't exist in database.");
            }
            ps.setInt(3, employee.getEmployee_id());

            Shipper shipper = ShipperDao.getInstance().find(order.getShipper().getShipper_id(), con);
            if (shipper == null) {
                throw new WarehouseException("Shipper " + order.getShipper().getShipper_name() + " doesn't exist in database.");
            }
            ps.setInt(4, shipper.getShipper_id());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(Order order, Connection con) throws SQLException {
        /*
        Ažuriramo zapis u order tabeli po id-u 
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `order` SET `order_date`=?,`customer_id`=?,`employee_id`=?,`shipper_id`=? WHERE `order_id`=?");
            ps.setString(1, order.getOrder_date());
            ps.setInt(2, order.getCustomer().getCustomer_id());
            ps.setInt(3, order.getEmployee().getEmployee_id());
            ps.setInt(4, order.getShipper().getShipper_id());
            ps.setInt(5, order.getOrder_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int order_id, Connection con) throws SQLException {
        /*
        Brišemo člana tabele order
        sa zadatim id-jem
         */
        PreparedStatement ps = null;
        try {
            Order order = OrderDao.getInstance().find(order_id, con);

            /*
            Kada brišemo order, brišemo i order_detail
            po stranom ključu order_id
             */
            OrderDetailDao.getInstance().delete(order, con);

            ps = con.prepareStatement("DELETE FROM `order` WHERE `order_id`=?");
            ps.setInt(1, order.getOrder_id());
            ps.executeUpdate();

            /*
            nije potrebno da se obrišu
            ShipperDao.getInstance().delete(order.getShipper().getShipper_id(), con);
            CustomerDao.getInstance().delete(order.getCustomer().getCustomer_id(), con);
            EmployeeDao.getInstance().delete(order.getEmployee().getEmployee_id(), con);
             */
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Shipper shipper, Connection con) throws SQLException {
        /*
        Brišemo člana tabele order
        sa zadatim shipper id-jem
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `order` WHERE `shipper_id`=?");
            ps.setInt(1, shipper.getShipper_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Customer customer, Connection con) throws SQLException {
        /*
        Brišemo člana tabele order
        sa zadatim customer id-jem
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `order` WHERE `customer_id`=?");
            ps.setInt(1, customer.getCustomer_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(Employee employee, Connection con) throws SQLException {
        /*
        Brišemo člana tabele order
        sa zadatim employee id-jem
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `order` WHERE `employee_id`=?");
            ps.setInt(1, employee.getEmployee_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public Order find(int order_id, Connection con) throws SQLException {
        /*
        Vraćamo instancu klase order
        iz baze pronađena preko id-a
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `order` WHERE `order_id`=?");
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = CustomerDao.getInstance().find(rs.getInt("customer_id"), con);
                Employee employee = EmployeeDao.getInstance().find(rs.getInt("employee_id"), con);
                Shipper shipper = ShipperDao.getInstance().find(rs.getInt("shipper_id"), con);
                order = new Order(rs.getInt("order_id"), rs.getString("order_date"), customer, employee, shipper);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return order;
    }

    public List<Order> findall(Connection con) throws SQLException {
        /*
        Vraćamo zapise iz tabele order iz baze
        kao listu instanci klase
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `order`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = CustomerDao.getInstance().find(rs.getInt("customer_id"), con);
                Employee employee = EmployeeDao.getInstance().find(rs.getInt("employee_id"), con);
                Shipper shipper = ShipperDao.getInstance().find(rs.getInt("shipper_id"), con);
                Order order = new Order(rs.getInt("order_id"), rs.getString("order_date"), customer, employee, shipper);
                orderList.add(order);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderList;
    }
}
