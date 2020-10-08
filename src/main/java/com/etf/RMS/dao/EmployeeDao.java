package com.etf.RMS.dao;

import com.etf.RMS.data.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dusan
 */
public class EmployeeDao {

    private static final EmployeeDao instance = new EmployeeDao();

    public EmployeeDao() {
    }

    public static EmployeeDao getInstance() {
        return instance;
    }

    public void create(Employee employee, Connection con) throws SQLException {
        /*
        Dodajemo novi red u employee tabeli
        u bazi podataka
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `employee`(`last_name`, `first_name`, `birthday`) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getLast_name());
            ps.setString(2, employee.getFirst_name());
            ps.setString(3, employee.getBirthday());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(Employee employee, Connection con) throws SQLException {
        /*
        Ažuriramo zapis u employee tabeli po id-u 
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `employee` SET `last_name`=?,`first_name`=?,`birthday`=? WHERE `employee_id`=?");
            ps.setString(1, employee.getLast_name());
            ps.setString(2, employee.getFirst_name());
            ps.setString(3, employee.getBirthday());
            ps.setInt(4, employee.getEmployee_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int employee_id, Connection con) throws SQLException {
        /*
        Brišemo člana tabele employee
        sa zadatim id-jem
         */
        PreparedStatement ps = null;
        try {
            Employee employee = EmployeeDao.getInstance().find(employee_id, con);

            /*
            Kada brišemo employee, brišemo i order
            po stranom ključu employee_id
             */
            OrderDao.getInstance().delete(employee, con);
            ps = con.prepareStatement("DELETE FROM `employee` WHERE `employee_id`=?");
            ps.setInt(1, employee_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public Employee find(int employee_id, Connection con) throws SQLException {
        /*
        Vraćamo instancu klase employee
        iz baze pronađena preko id-a
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `employee` WHERE `employee_id`=?");
            ps.setInt(1, employee_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee(rs.getInt("employee_id"), rs.getString("last_name"), rs.getString("first_name"), rs.getString("birthday"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employee;
    }

    public List<Employee> findall(Connection con) throws SQLException {
        /*
        Vraćamo zapise iz tabele employee iz baze
        kao listu instanci klase
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `employee`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("employee_id"), rs.getString("last_name"), rs.getString("first_name"), rs.getString("birthday"));
                employeeList.add(employee);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employeeList;
    }
}
