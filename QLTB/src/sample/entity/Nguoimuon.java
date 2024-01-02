package sample.entity;

public class Nguoimuon {
    String manguoimuon;
    String tennguoimuon;
    String khoavien;
    String chucvu;
    String email;

    public Nguoimuon(String manguoimuon,String tennguoimuon,String khoavien,String chucvu,String email){
        this.chucvu =chucvu;
        this.email = email;
        this.khoavien = khoavien;
        this.manguoimuon = manguoimuon;
        this.tennguoimuon = tennguoimuon;
    }

    public String getManguoimuon() {
        return manguoimuon;
    }

    public String getChucvu() {
        return chucvu;
    }

    public String getEmail() {
        return email;
    }

    public String getKhoavien() {
        return khoavien;
    }

    public String getTennguoimuon() {
        return tennguoimuon;
    }
}
