/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.dao;
import edu.poly.duan1.helper.JdbcHelper;
import edu.poly.duan1.model.HoaDon;
import java.sql.*;
/**
 *
 * @author ASUS PC
 */
public class insertHoaDonDAO {
    public boolean luuHD(HoaDon hd ){
        try {
            Connection conn = JdbcHelper.openConnection();
             String sql = "insert into HoaDon values (?,?,?,?,?,?,?,?,?)";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, hd.getMaKH());
             ps.setString(2, hd.getTenKH());
             ps.setInt(3, hd.getCMND());
             ps.setString(4, hd.getMaPN());
             ps.setString(5, hd.getTenPN());
             ps.setFloat(6, hd.getGiaPhong());
             ps.setString(7, hd.getNgayTT());
             ps.setFloat(8, hd.getSale());
             ps.setFloat(9, hd.getThanhTien());
             ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("eror inserHD"+e);
        }
        
        return  true;
    }
    
    
}
