/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.dao;

import edu.poly.duan1.helper.JdbcHelper;
import edu.poly.duan1.model.HoaDon;
import edu.poly.duan1.model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS PC
 */
public class KhachHangDAO {

    public void insert(KhachHang model) {
        String sql = "INSERT INTO KhachHang (MaKH, TenKH, SDT, CMND, GioiTinh, GhiChu) VALUES( ?,  ?,  ?,  ?,  ?,  ?) ";

        JdbcHelper.update(sql,
                model.getMaKH(),
                model.getHoTen(),
                model.getSDT(),
                model.getCMND(),
                model.isGioiTinh(),
                model.getGhiChu());
    }

    public void update(KhachHang model) {
        String sql
                = "UPDATE KhachHang SET TenKH=?, SDT=?, CMND=?, GioiTinh=?, GhiChu=? WHERE  MaKH = ?";

        JdbcHelper.update(sql,
                model.getHoTen(),
                model.getSDT(),
                model.getCMND(),
                model.isGioiTinh(),
                model.getGhiChu(),
                model.getMaKH());
    }

    public void delete(String id) {
        String sql = "DELETE FROM KhachHang WHERE MaKH=?";
        JdbcHelper.update(sql, id);
    }

    public List<KhachHang> select() {
        String sql = "SELECT * FROM KhachHang";
        return select(sql);
    }

    public List<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE TenKH LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public KhachHang findById(String makh) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH=?";
        List<KhachHang> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

   
        

    

    

    private List<KhachHang> select(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    KhachHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private KhachHang readFromResultSet(ResultSet rs) throws SQLException {
        KhachHang model = new KhachHang();
        model.setMaKH(rs.getString("MaKH"));
        model.setHoTen(rs.getString("TenKH"));
        model.setSDT(rs.getInt("SDT"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setCMND(rs.getInt("CMND"));
        model.setGhiChu(rs.getString("GhiChu"));
        return model;
    }
}
