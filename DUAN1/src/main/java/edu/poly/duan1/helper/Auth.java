/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.helper;

import edu.poly.duan1.model.NhanVien;

/**
 *
 * @author ASUS PC
 */
public class Auth {

    //đối tượng chứa thông tin người dùng khi đăng nhập
    public static NhanVien user = null;

    //xóa thông tin người sử dụng khi có yêu cầu đăng xuất
    public static void clear() {
        Auth.user = null;
    }

    //kiểm tra đăng nhập hay chưa
    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static boolean isManager() {
        return Auth.isLogin() && user.isChucVu();
    }
}
