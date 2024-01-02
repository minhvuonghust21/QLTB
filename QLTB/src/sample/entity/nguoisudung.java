package sample.entity;

public class nguoisudung {
    private String taikhoan;
    private String matkhau;
    private String chucvu;

    public String getTaikhoan(){
        return taikhoan;
    }
    public String getChucvu(){
        return chucvu;
    }
    public String getMatkhau(){
        return matkhau;
    }

    public nguoisudung(String taikhoan,String chucvu,String matkhau){
       this.taikhoan = taikhoan;
       this.chucvu = chucvu;
       this.matkhau = matkhau;
    }
}
