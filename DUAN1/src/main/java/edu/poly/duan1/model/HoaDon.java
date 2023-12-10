/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.model;

import java.util.Date;

/**
 *
 * @author ASUS PC
 */
public class HoaDon {
    private String MaKH;
    private String TenKH;
    private int CMND;
    private String MaPN;
    private String TenPN;
    private float GiaPhong;
    private String ngayTT;
    private float Sale;
    private float ThanhTien;

    public HoaDon() {
    }

    public HoaDon(String MaKH, String TenKH, int CMND, String MaPN, String TenPN, float GiaPhong, String ngayTT, float Sale, float ThanhTien) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.CMND = CMND;
        this.MaPN = MaPN;
        this.TenPN = TenPN;
        this.GiaPhong = GiaPhong;
        this.ngayTT = ngayTT;
        this.Sale = Sale;
        this.ThanhTien = ThanhTien;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public int getCMND() {
        return CMND;
    }

    public void setCMND(int CMND) {
        this.CMND = CMND;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public String getTenPN() {
        return TenPN;
    }

    public void setTenPN(String TenPN) {
        this.TenPN = TenPN;
    }

    public float getGiaPhong() {
        return GiaPhong;
    }

    public void setGiaPhong(float GiaPhong) {
        this.GiaPhong = GiaPhong;
    }

    public String getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(String ngayTT) {
        this.ngayTT = ngayTT;
    }

    public float getSale() {
        return Sale;
    }

    public void setSale(float Sale) {
        this.Sale = Sale;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    
    
    
}
