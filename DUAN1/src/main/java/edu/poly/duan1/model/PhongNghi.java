/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.model;

/**
 *
 * @author ASUS PC
 */
public class PhongNghi {
    private String maPN;
    private String tenPN;
    private float giaPN;
    private String loaiPhong;
    private String moTa;

    public PhongNghi() {
    }

    public PhongNghi(String maPN, String tenPN, float giaPN, String loaiPhong, String moTa) {
        this.maPN = maPN;
        this.tenPN = tenPN;
        this.giaPN = giaPN;
        this.loaiPhong = loaiPhong;
        this.moTa = moTa;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getTenPN() {
        return tenPN;
    }

    public void setTenPN(String tenPN) {
        this.tenPN = tenPN;
    }

    public float getGiaPN() {
        return giaPN;
    }

    public void setGiaPN(float giaPN) {
        this.giaPN = giaPN;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    

    

    
    
}
