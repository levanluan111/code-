/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.model;

/**
 *
 * @author ASUS PC
 */
public class KhachHang {
    private String maKH;
    private String hoTen;
    private int SDT;
    private int CMND;
    private boolean gioiTinh;
    private String ghiChu;

    public KhachHang() {
    }

    public KhachHang(String maKH, String hoTen, int SDT, int CMND, boolean gioiTinh, String ghiChu) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.SDT = SDT;
        this.CMND = CMND;
        this.gioiTinh = gioiTinh;
        this.ghiChu = ghiChu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public int getCMND() {
        return CMND;
    }

    public void setCMND(int CMND) {
        this.CMND = CMND;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
