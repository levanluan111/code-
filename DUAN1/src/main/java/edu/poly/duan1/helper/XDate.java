/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.duan1.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS PC
 */
public class XDate {
    static SimpleDateFormat formater = new SimpleDateFormat();
    static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("MM/dd/yyyy");
    //Chuyển đổi String sang Date
    //@param date là String cần chuyển
    //@param pattern là định dạng thời gian
    //@return Date kết quả
    
    public static Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (ParseException ex) {
           throw new RuntimeException(ex);
        }
    }
    
    //Chuyển đổi từ Date sang String
    //@param date là Date cần chuyển đổi
    //@param pattern là định dạng thời gian
    //@return String kết quả
    
    public static String toString(Date date, String pattern) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }
    public static Date now() {
        return new Date();
    }
    
    //Bổ sung số ngày vào thời gian
    //@param date thời gian hiện số
    //@param days số ngày cần bổ sung vào date
    //@return Date kết quả
    
    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

   public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            DATE_FORMATER.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = XDate.now();
        }
        return DATE_FORMATER.format(date);
    }
}
