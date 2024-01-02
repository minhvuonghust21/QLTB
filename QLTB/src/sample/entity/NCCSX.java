package sample.entity;

public class NCCSX {
    private  String ma;
    private  String ten;
    private  String diachi;
    private  String SDT;

    public NCCSX(String ma,String ten,String diachi,String SDT){
      this.ma = ma;
      this.ten = ten;
      this.diachi = diachi;
      this.SDT = SDT;
    }
    public String getMa(){
        return ma;
    }
    public  String getTen(){
        return ten;
    }
    public  String getDiachi(){
        return diachi;
    }
    public  String getSDT(){
        return SDT;
    }
}
