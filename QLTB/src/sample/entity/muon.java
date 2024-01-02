package sample.entity;

import javax.xml.crypto.Data;
import java.util.Date;

public class muon {
    String maphieumuon;
    String matb;
    Date ngaytra;
    String tinhtrangTB;
    String tentb;
    public muon(String maphieumuon, String matb, Date ngaytra, String tinhtrangTB,String tentb){
        this.maphieumuon = maphieumuon;
        this.matb = matb;
        this.ngaytra = ngaytra;
        this.tinhtrangTB = tinhtrangTB;
        this.tentb = tentb;
    }

    public String getMaphieumuon() {
        return maphieumuon;
    }

    public Date getNgaytra() {
        return ngaytra;
    }

    public String getMatb() {
        return matb;
    }

    public String getTinhtrangTB() {
        return tinhtrangTB;
    }

    public String getTentb() {
        return tentb;
    }
}
