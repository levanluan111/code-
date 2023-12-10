/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.dao;

import edu.poly.duan1.helper.JdbcHelper;
import edu.poly.duan1.model.NhanVien;
import edu.poly.duan1.model.PhongNghi;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author ASUS PC
 */
public class PhongNghiDAO {
    public void insert(PhongNghi model) {
        String sql = "INSERT INTO PhongNghi (MaPN, TenPN, GiaPhong, LoaiPhong, MoTa) VALUES( ?,  ?,  ?,  ?,  ?) ";

        JdbcHelper.update(sql,
                model.getMaPN(),
                model.getTenPN(),
                model.getGiaPN(),
                model.getLoaiPhong(),
                model.getMoTa());
    }

    public void update(PhongNghi model) {
        String sql
                = "UPDATE PhongNghi SET TenPN=?, GiaPhong=?, LoaiPhong=?, MoTa=? WHERE MaPN=?";

        JdbcHelper.update(sql,
                
                model.getTenPN(),
                model.getGiaPN(),
                model.getLoaiPhong(),
                model.getMoTa(),
                model.getMaPN());
                
    }

    public void delete(String id) {
        String sql = "DELETE FROM PhongNghi WHERE MaPN=?";
        JdbcHelper.update(sql, id);
    }

    public List<PhongNghi> select() {
        String sql = "SELECT * FROM PhongNghi";
        return select(sql);
    }

    public List<PhongNghi> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM PhongNghi WHERE TenPN LIKE ?";
        return select(sql, "%" + keyword + "%");
    }


    public PhongNghi findById(String mapn) {
        String sql = "SELECT * FROM PhongNghi WHERE MaPN=?";
        List<PhongNghi> list = select(sql, mapn);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<PhongNghi> select(String sql, Object... args) {
        List<PhongNghi> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    PhongNghi model = readFromResultSet(rs);
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
    

    private PhongNghi readFromResultSet(ResultSet rs) throws SQLException {
        PhongNghi model = new PhongNghi();
        model.setMaPN(rs.getString("MaPN"));
        model.setTenPN(rs.getString("TenPN"));
        model.setGiaPN(rs.getFloat("GiaPhong"));
        model.setLoaiPhong(rs.getString("LoaiPhong"));
        model.setMoTa(rs.getString("Mota"));
        return model;
    }
}
