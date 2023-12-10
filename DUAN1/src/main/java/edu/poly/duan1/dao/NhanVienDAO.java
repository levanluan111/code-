/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.dao;

import edu.poly.duan1.helper.JdbcHelper;
import edu.poly.duan1.model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS PC
 */
public class NhanVienDAO extends MainFormDAO<NhanVien, String> {

    final String INSERT_SQL = "INSERT INTO NhanVien (MaNV, HoTen, MatKhau, CMND, DiaChi, ChucVu) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET HoTen=?, MatKhau=?, CMND=?', DiaChi=?, ChucVu=? WHERE MaNV=?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    final String SELETE_ALL_SQL = "SELECT * FROM NhanVien";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien where MaNV = ?";

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getHoTen(), entity.getMatKhau(), entity.getCMND(), entity.getDiaChi(),  entity.isChucVu());
    }

//    @Override
//    public void update(NhanVien entity) {
//        JdbcHelper.update(UPDATE_SQL, entity.getHoTen(), entity.getMatKhau(), entity.getCMND(), entity.getDiaChi(),  entity.isChucVu(), entity.getMaNV());
//    }
     public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET HoTen=?, MatKhau=?, CMND=?, DiaChi=?, ChucVu=? WHERE MaNV=?";
        JdbcHelper.update(sql,
                model.getHoTen(),
                model.getMatKhau(),
                model.getCMND(),
                model.getDiaChi(),
                model.isChucVu(),
                model.getMaNV());
    }

     public void updateMK(NhanVien model) {
        String sql = "UPDATE NhanVien SET MatKhau=? WHERE MaNV=?";
        JdbcHelper.update(sql,
                model.getMatKhau(),
                model.getMaNV());
    }
    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELETE_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {

                NhanVien staff = new NhanVien();
                staff.setMaNV(rs.getString("MaNV"));
                staff.setHoTen(rs.getString("HoTen"));
                staff.setMatKhau(rs.getString("MatKhau"));
                staff.setCMND(rs.getInt("CMND"));
                staff.setDiaChi(rs.getString("DiaChi"));
                staff.setChucVu(rs.getBoolean("ChucVu"));

                list.add(staff);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public NhanVien findByid(String id) throws SQLException {
        System.out.println("ddd");
        ResultSet rs = JdbcHelper.query(SELECT_BY_ID_SQL, id);
        if (rs.next()) {
            NhanVien staff = new NhanVien();
            staff.setMaNV(rs.getString("MaNV"));
            staff.setHoTen(rs.getString("HoTen"));
            staff.setMatKhau(rs.getString("MatKhau"));
            staff.setCMND(rs.getInt("CMND"));
            staff.setDiaChi(rs.getString("DiaChi"));
            staff.setChucVu(rs.getBoolean("ChucVu"));
            return staff;
        }

        return null;
    }

}
