/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.dao;

import edu.poly.duan1.helper.JdbcHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ASUS PC
 */
public class getKhachHangDAO {
    public int getKhachHang() {
        try {
            Connection conn = JdbcHelper.openConnection();
            String query = "select count (*) from KhachHang" ;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("---" + e);
        }
        return 0;
    }
}
