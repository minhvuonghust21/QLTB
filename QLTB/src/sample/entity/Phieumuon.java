package sample.entity;

import java.util.Date;

public class Phieumuon {
    String maphieumuon;
    Date ngaylap;
    Date ngaytra;
    String manhanvien;
    String manguoimuon;

    public Phieumuon(String maphieumuon,Date ngaylap,Date ngaytra,String manhanvien,String manguoimuon){
        this.maphieumuon = maphieumuon;
        this.ngaylap = ngaylap;
        this.ngaytra = ngaytra;
        this.manhanvien = manhanvien;
        this.manguoimuon = manguoimuon;
    }

    public String getMaphieumuon() {
        return maphieumuon;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public Date getNgaytra() {
        return ngaytra;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public String getManguoimuon() {
        return manguoimuon;
    }
}
