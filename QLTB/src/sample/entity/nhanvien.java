package sample.entity;

import java.util.Date;

public class nhanvien {
    private String manhanvien;
    private String hoten;
    private String CCCD;
    private Date ngaycongtac;
    private String email;

    public nhanvien(String manhanvien, String hoten, String CCCD, Date ngaycongtac, String email) {
        this.manhanvien = manhanvien;
        this.hoten = hoten;
        this.CCCD = CCCD;
        this.ngaycongtac = ngaycongtac;
        this.email = email;
    }
    public String getManhanvien(){
        return manhanvien;
    }
    public String getHoten(){
        return hoten;
    }
    public String getCCCD(){
        return CCCD;
    }
    public Date getNgaycongtac(){
        return ngaycongtac;
    }
    public String getEmail(){
        return email;
    }
}
