package sample.entity;

import java.util.Date;

public class PhieuNhap {
   private String maphieunhap;
   private Date ngaynhap;
   private String manhanvien;
   private  String mancc;
   public PhieuNhap(String maphieunhap,Date ngaynhap, String manhanvien,String mancc){
      this.maphieunhap = maphieunhap;
      this.ngaynhap = ngaynhap;
      this.manhanvien = manhanvien;
      this.mancc = mancc;

    }

    public String getMaphieunhap(){
      return maphieunhap;
    }
    public  String getManhanvien() {
       return manhanvien;
    }
    public String getMancc(){
      return  mancc;
    }
    public Date getNgaynhap(){
      return ngaynhap;
    }
}

