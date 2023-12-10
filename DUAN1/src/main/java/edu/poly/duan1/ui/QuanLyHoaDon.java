/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package edu.poly.duan1.ui;

import edu.poly.duan1.dao.HoaDonDAO;
import edu.poly.duan1.dao.KhachHangDAO;
import edu.poly.duan1.dao.PhongNghiDAO;
import edu.poly.duan1.dao.insertHoaDonDAO;
import edu.poly.duan1.helper.JdbcHelper;
import edu.poly.duan1.helper.MsgBox;
import edu.poly.duan1.helper.XImage;
import edu.poly.duan1.model.HoaDon;
import edu.poly.duan1.model.KhachHang;
import edu.poly.duan1.model.PhongNghi;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS PC
 */
public class QuanLyHoaDon extends javax.swing.JDialog {

    /**
     * Creates new form QuanLyHoaDon
     */
    public QuanLyHoaDon(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        getContentPane().setBackground(Color.white);
        init();
    }

    void init() {
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("STARCITY QUẢN LÝ HÓA ĐƠN");

    }

    void loadKhachHang() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            //String keyword = txtTimKiem.getText();
            KhachHangDAO dao = new KhachHangDAO();
            List<KhachHang> list = dao.select();//tạo 1 list và từ đối tượng dao 
            for (KhachHang kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getHoTen(),
                    kh.getSDT(),
                    kh.getCMND(),
                    kh.isGioiTinh(),
                    kh.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    // load vao table phong
    void loadPhongNghi() {
        DefaultTableModel model = (DefaultTableModel) tblPhongNghi.getModel();
        model.setRowCount(0);
        PhongNghiDAO dao = new PhongNghiDAO();
        try {
            List<PhongNghi> list = dao.select();//tạo 1 list và từ đối tượng dao 
            for (PhongNghi pn : list) {
                Object[] row = {
                    pn.getMaPN(),
                    pn.getTenPN(),
                    pn.getGiaPN(),
                    pn.getLoaiPhong(),
                    pn.getMoTa()

                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }
// laod đang chờ 

    void loadCn() {
        DefaultTableModel model = (DefaultTableModel) tlbCN.getModel();
        model.setRowCount(0);
        try {
            //String keyword = txtTimKiem.getText();
            HoaDonDAO dao = new HoaDonDAO();
            List<HoaDon> list = dao.getCn();//tạo 1 list và từ đối tượng dao 
            for (HoaDon kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getCMND(),
                    kh.getMaPN(),
                    kh.getTenPN(),
                    kh.getGiaPhong(),
                    kh.getNgayTT(),
                    kh.getThanhTien()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    // tìm chap nhan
    void displayCn() {
        try {
            int row = tlbCN.getSelectedRow();
            if (row >= 0) {
                String id = (String) tlbCN.getValueAt(row, 0);
                System.out.println("----" + id);
                HoaDonDAO dao = new HoaDonDAO();
                HoaDon sp = dao.findByCn(id);
                if (sp != null) {
                    txtMaKH1.setText(sp.getMaKH());
                    txtTenKh1.setText(String.valueOf(sp.getTenKH()));       
                    txtCmnd1.setText("" + sp.getCMND());
                    txtMaPn1.setText(sp.getMaPN());
                    txtTenPn1.setText(sp.getTenPN());
                    txtGp1.setText("" + sp.getGiaPhong());
                    txtDay.setText(sp.getMaKH());
                    txtThanhTien1.setText("" + sp.getThanhTien());

                }
            }

        } catch (Exception e) {
            System.err.println( e.toString() + " cc ");
        }
    }

    // tìm kách hàng
    void displayPhong() {
        try {
            int row = tblPhongNghi.getSelectedRow();
            if (row >= 0) {
                String id = (String) tblPhongNghi.getValueAt(row, 0);
                System.out.println("----" + id);
                PhongNghiDAO dao = new PhongNghiDAO();
                PhongNghi sp = dao.findById(id);
                if (sp != null) {
                    txtMaPn.setText(sp.getMaPN());
                    txtTenPn.setText("" + sp.getTenPN());
                    txtGp.setText("" + sp.getGiaPN());
                    txtThanhTien.setText("" + sp.getGiaPN());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tim phong nghi
    void displayKh() {
        try {
            int row = tblKhachHang.getSelectedRow();
            if (row >= 0) {
                String id = (String) tblKhachHang.getValueAt(row, 0);
                System.out.println("----" + id);
                KhachHangDAO dao = new KhachHangDAO();
                KhachHang sp = dao.findById(id);
                if (sp != null) {
                    txtMaKH.setText(sp.getMaKH());
                    txtTenKh.setText("" + sp.getHoTen());
                    txtCmnd.setText("" + sp.getCMND());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaPn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenPn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtGp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        btnTao = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPhongNghi = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        txtCmnd = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSale = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlbCN = new javax.swing.JTable();
        txtMaKH1 = new javax.swing.JTextField();
        txtGp1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTenKh1 = new javax.swing.JTextField();
        txtThanhTien1 = new javax.swing.JTextField();
        txtCmnd1 = new javax.swing.JTextField();
        btnChapNhan = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnMoi1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtDay = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtMaPn1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTenPn1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã nhân viên:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên hóa đơn:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ HÓA ĐƠN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã phòng nghỉ:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên phòng nghỉ:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giá phòng nghỉ:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã khách hàng:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Thành tiền:");

        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        btnTao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTao.setForeground(new java.awt.Color(51, 51, 51));
        btnTao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/duan1/icon/plus.png"))); // NOI18N
        btnTao.setText("TẠO");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(51, 51, 51));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/duan1/icon/moi.png"))); // NOI18N
        btnMoi.setText("MỚI");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "SDT", "CMND", "Giới Tính", "Chú Ý"
            }
        ));
        tblKhachHang.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblKhachHangAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKhachHang);

        tblPhongNghi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PN", "Tên PN", "Giá PN", "Loại PN", "Mô tả"
            }
        ));
        tblPhongNghi.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblPhongNghiAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblPhongNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhongNghiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPhongNghi);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 102));
        jLabel9.setText("THÔNG TIN KHÁCH HÀNG");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 102));
        jLabel10.setText("PHÒNG NGHỈ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Tên khách hàng:");

        txtTenKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhActionPerformed(evt);
            }
        });

        txtCmnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCmndActionPerformed(evt);
            }
        });

        jLabel14.setText("CMND:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Được giảm:");

        txtSale.setText("0");
        txtSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtSale, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txtMaPn)
                    .addComponent(txtTenPn)
                    .addComponent(txtMaKH)
                    .addComponent(txtTenKh)
                    .addComponent(txtCmnd)
                    .addComponent(jLabel6)
                    .addComponent(txtGp, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMoi)
                        .addGap(62, 62, 62)))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(9, 9, 9)
                        .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaPn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTao)
                            .addComponent(btnMoi))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tabs.addTab("CẬP NHẬT", jPanel1);

        tlbCN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ KH", "TÊN KH", "CMND", "MÃ PN", "TÊN PN", "GIÁ PN", "NGÀY TT", "THÀNH TIỀN"
            }
        ));
        tlbCN.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tlbCNAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tlbCN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tlbCNMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tlbCN);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Tên khách hàng:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Thành tiền:");

        txtTenKh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKh1ActionPerformed(evt);
            }
        });

        txtThanhTien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTien1ActionPerformed(evt);
            }
        });

        txtCmnd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCmnd1ActionPerformed(evt);
            }
        });

        btnChapNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChapNhan.setForeground(new java.awt.Color(51, 51, 51));
        btnChapNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/duan1/icon/plus.png"))); // NOI18N
        btnChapNhan.setText("Chấp Nhận Đơn Hàng");
        btnChapNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChapNhanActionPerformed(evt);
            }
        });

        jLabel16.setText("CMND:");

        btnMoi1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMoi1.setForeground(new java.awt.Color(51, 51, 51));
        btnMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/duan1/icon/moi.png"))); // NOI18N
        btnMoi1.setText("MỚI");
        btnMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoi1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Ngày thanh toán:");

        txtDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDayActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Mã phòng nghỉ:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tên phòng nghỉ:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Giá phòng nghỉ:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Mã khách hàng:");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/duan1/icon/ks3.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(txtThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(txtMaPn1)
                    .addComponent(txtTenPn1)
                    .addComponent(txtMaKH1)
                    .addComponent(txtTenKh1)
                    .addComponent(txtCmnd1)
                    .addComponent(jLabel20)
                    .addComponent(txtGp1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnChapNhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel21)
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMaKH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addGap(9, 9, 9)
                        .addComponent(txtTenKh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCmnd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaPn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChapNhan)
                            .addComponent(btnMoi1)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tabs.addTab("DANH SÁCH", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaleActionPerformed
        float sale = Float.parseFloat(txtSale.getText());
        float price = Float.parseFloat(txtGp.getText());
        float total = price - sale;
        txtThanhTien.setText("" + total);
        System.out.println("tonh tien" + total);
    }//GEN-LAST:event_txtSaleActionPerformed

    private void txtCmndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCmndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCmndActionPerformed

    private void txtTenKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhActionPerformed

    private void tblPhongNghiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongNghiMouseClicked
        displayPhong();
    }//GEN-LAST:event_tblPhongNghiMouseClicked

    private void tblPhongNghiAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblPhongNghiAncestorAdded
        loadPhongNghi();
    }//GEN-LAST:event_tblPhongNghiAncestorAdded

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        displayKh();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void tblKhachHangAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblKhachHangAncestorAdded
        loadKhachHang();
    }//GEN-LAST:event_tblKhachHangAncestorAdded

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        txtMaKH.setText("");
        txtTenKh.setText("");
        txtCmnd.setText("");
        txtMaPn.setText("");
        txtTenPn.setText("");
        txtGp.setText("");
        txtSale.setText("");
        txtThanhTien.setText("");
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        try {
            HoaDon hd = new HoaDon();
            hd.setMaKH(txtMaKH.getText());
            System.out.println("---" + txtMaKH.getText());
            hd.setTenKH(txtTenKh.getText());
            hd.setCMND(Integer.parseInt(txtCmnd.getText()));
            hd.setMaPN(txtMaPn.getText());
            hd.setTenPN(txtTenPn.getText());
            hd.setGiaPhong(Float.parseFloat(txtGp.getText()));
            LocalDateTime current = LocalDateTime.now();
            DateTimeFormatter day = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String svdate = current.format(day);
            System.out.println(svdate);
            hd.setNgayTT("" + svdate);
            hd.setSale(Float.parseFloat(txtSale.getText()));
            hd.setThanhTien(Float.parseFloat(txtThanhTien.getText()));
            insertHoaDonDAO dao = new insertHoaDonDAO();
            dao.luuHD(hd);

            JOptionPane.showMessageDialog(this, "Thanh Cong");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }//GEN-LAST:event_btnTaoActionPerformed

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed

    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void txtTenKh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKh1ActionPerformed

    private void txtThanhTien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTien1ActionPerformed

    private void txtCmnd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCmnd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCmnd1ActionPerformed

    private void btnChapNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChapNhanActionPerformed
        try {
            String id = txtMaKH1.getText();
            System.out.println(id);
            HoaDonDAO dao = new HoaDonDAO();
            if(!dao.channhan(id));
            JOptionPane.showMessageDialog(this, "Chap nhan thanh cong");
           loadCn();
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnChapNhanActionPerformed

    private void btnMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoi1ActionPerformed
        txtMaKH1.setText("");
        txtTenKh1.setText("");
        txtCmnd1.setText("");
        txtMaPn1.setText("");
        txtTenPn1.setText("");
        txtGp1.setText("");
        txtDay.setText("");
        txtThanhTien1.setText("");
    }//GEN-LAST:event_btnMoi1ActionPerformed

    private void txtDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDayActionPerformed

    private void tlbCNAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tlbCNAncestorAdded
        loadCn();        // TODO add your handling code here:
    }//GEN-LAST:event_tlbCNAncestorAdded

    private void tlbCNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tlbCNMouseClicked
        displayCn();        // TODO add your handling code here:
    }//GEN-LAST:event_tlbCNMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLyHoaDon dialog = new QuanLyHoaDon(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChapNhan;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnMoi1;
    private javax.swing.JButton btnTao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblPhongNghi;
    private javax.swing.JTable tlbCN;
    private javax.swing.JTextField txtCmnd;
    private javax.swing.JTextField txtCmnd1;
    private javax.swing.JTextField txtDay;
    private javax.swing.JTextField txtGp;
    private javax.swing.JTextField txtGp1;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaKH1;
    private javax.swing.JTextField txtMaPn;
    private javax.swing.JTextField txtMaPn1;
    private javax.swing.JTextField txtSale;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTenKh1;
    private javax.swing.JTextField txtTenPn;
    private javax.swing.JTextField txtTenPn1;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtThanhTien1;
    // End of variables declaration//GEN-END:variables
}
