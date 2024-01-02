package sample.entity;

public class LoaiTb {
    private  String maloaitb;
    private  String tenloaitb;
    private  String mota;
    public LoaiTb(String maloaitb,String tenloaitb,String mota) {
        this.maloaitb = maloaitb;
        this.tenloaitb = tenloaitb;
        this.mota = mota;
    }
    public String getMaloaitb(){
        return maloaitb;
    }
    public  String getTenloaitb(){
        return tenloaitb;
    }
    public  String getMota(){
        return mota;
    }
}
