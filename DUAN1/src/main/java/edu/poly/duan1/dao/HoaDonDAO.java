/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.dao;

import edu.poly.duan1.helper.JdbcHelper;
import edu.poly.duan1.model.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS PC
 */
public class HoaDonDAO {
// tim id
    public HoaDon findByCn(String makh) throws Exception {
        try {
            Connection conn = JdbcHelper.openConnection();
            String sql = "select * from HoaDon where MaKH = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, makh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon o = new HoaDon();
                o.setMaKH(rs.getString(1));
                o.setTenKH(rs.getString(2));
                o.setCMND(rs.getInt(3));
                o.setMaPN(rs.getString(4));
                o.setTenPN(rs.getString(5));
                o.setGiaPhong(rs.getFloat(6));
                o.setNgayTT(rs.getString(7));
                o.setSale(rs.getFloat(8));
                o.setThanhTien(rs.getFloat(9));
                return o;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // lay du lieu len bang
    public List<HoaDon> getCn() throws Exception {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.openConnection();
            String sql = "select * from HoaDon  ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                HoaDon o = new HoaDon();
                o.setMaKH(rs.getString(1));
                o.setTenKH(rs.getString(2));
                o.setCMND(rs.getInt(3));
                o.setMaPN(rs.getString(4));
                o.setTenPN(rs.getString(5));
                o.setGiaPhong(rs.getFloat(6));
                o.setNgayTT(rs.getString(7));
                o.setSale(rs.getFloat(8));
                o.setThanhTien(rs.getFloat(9));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // chap nhan
    public  boolean channhan(String id){
        try {
             Connection conn = JdbcHelper.openConnection();
            String sql = "delete from HoaDon where MaKH = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
