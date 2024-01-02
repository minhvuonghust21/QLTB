package sample.entity;

public class thietbi {
   private String matb;
   private String tentb;
   private String namsanxuat;
   private String mota;
   private String tinhtrang;
   private String maloaitb;
   private String mansx;
   private String mavt;
   private String maphieunhap;

   public thietbi(String matb,String tentb,String namsanxuat, String mota,String tinhtrang, String maloaitb, String mansx
   ,String mavt,String maphieunhap){
       this.matb = matb;
       this.tentb = tentb;
       this.namsanxuat = namsanxuat;
       this.tinhtrang = tinhtrang;
       this.mota = mota;
       this.maloaitb = maloaitb;
       this.mavt = mavt;
       this.mansx = mansx;
       this.maphieunhap = maphieunhap;
   }
   public String getMatb(){
       return matb;
   }
   public String getTentb(){
       return tentb;
   }
   public String getNamsanxuat(){
       return namsanxuat;
   }
   public String getMota(){
       return mota;
   }
   public String getTinhtrang(){
       return tinhtrang;
   }
   public String getMaloaitb(){
       return maloaitb;
   }
   public String getMaphieunhap(){
       return maphieunhap;
   }
   public String getMavt(){
       return mavt;
   }
   public String getMansx(){
       return mansx;
   }
}
