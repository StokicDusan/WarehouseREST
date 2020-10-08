package com.etf.RMS.dao;

import com.etf.RMS.data.Shipper;
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
public class ShipperDao {

    private static final ShipperDao instance = new ShipperDao();

    public ShipperDao() {
    }

    public static ShipperDao getInstance() {
        return instance;
    }

    public void create(Shipper shipper, Connection con) throws SQLException {
        /*
        Dodajemo novi red u shipper tabeli
        u bazi podataka
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO `shipper`(`shipper_name`, `phone`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, shipper.getShipper_name());
            ps.setString(2, shipper.getPhone());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void update(Shipper shipper, Connection con) throws SQLException {
        /*
        Ažuriramo zapis u shipper tabeli po id-u 
         */
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE `shipper` SET `shipper_name`=?,`phone`=? WHERE `shipper_id`=?");
            ps.setString(1, shipper.getShipper_name());
            ps.setString(2, shipper.getPhone());
            ps.setInt(3, shipper.getShipper_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int shipper_id, Connection con) throws SQLException {
        /*
        Brišemo člana tabele shipper
        sa zadatim id-jem
         */
        PreparedStatement ps = null;
        try {
            Shipper shipper = ShipperDao.getInstance().find(shipper_id, con);

            /*
            Kada brišemo shipper, brišemo i order
            po stranom ključu shipper_id
             */
            OrderDao.getInstance().delete(shipper, con);
            ps = con.prepareStatement("DELETE FROM `shipper` WHERE `shipper_id`=?");
            ps.setInt(1, shipper_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public Shipper find(int shipper_id, Connection con) throws SQLException {
        /*
        Vraćamo instancu klase shipper
        iz baze pronađena preko id-a
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        Shipper shipper = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `shipper` WHERE `shipper_id`=?");
            ps.setInt(1, shipper_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                shipper = new Shipper(rs.getInt("shipper_id"), rs.getString("shipper_name"), rs.getString("phone"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return shipper;
    }

    public List<Shipper> findall(Connection con) throws SQLException {
        /*
        Vraćamo zapise iz tabele shipper iz baze
        kao listu instanci klase
         */
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Shipper> shipperList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `shipper`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Shipper shipper = new Shipper(rs.getInt("shipper_id"), rs.getString("shipper_name"), rs.getString("phone"));
                shipperList.add(shipper);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return shipperList;
    }

}
