package sample.entity;

public class vitri {
   private String mavt;
   private String tenvt;
   private  String mota;
   private  String manhanvien;

   public vitri(String mavt,String tenvt, String mota,String manhanvien){
      this.mavt = mavt;
      this.tenvt =tenvt;
      this.mota = mota;
      this.manhanvien = manhanvien;
   }
   public String getMavt(){
       return mavt;
   }
   public String getTenvt(){
       return tenvt;
   }
   public String getMota(){
       return mota;
   }
   public String getManhanvien(){
       return manhanvien;
   }
}
