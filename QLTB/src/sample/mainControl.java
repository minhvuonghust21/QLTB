package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.entity.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class mainControl implements Initializable {
    private PreparedStatement prestt;
    private Connection conn;
    private ResultSet rs,rs1;
    private Statement stt;
    //main_form
    @FXML
    private AnchorPane form_DOIMK;

    @FXML
    private AnchorPane form_QLKHAC;

    @FXML
    private AnchorPane form_QLMT;

    @FXML
    private AnchorPane form_QLNMPM;

    @FXML
    private AnchorPane form_QLNV;

    @FXML
    private AnchorPane form_QLTB;

    @FXML
    private AnchorPane form_QLTK;

    @FXML
    private AnchorPane form_background;


    @FXML
    private Button main_close;
    public void close_btn(){
        System.exit(0);
    }

    @FXML
    private Button main_doimkbtn;
    public  void main_doimkbtn(){
    form_DOIMK.setVisible(true);
    form_QLKHAC.setVisible(false);
    form_QLMT.setVisible(false);
    form_QLNMPM.setVisible(false);
    form_QLNV.setVisible(false);
    form_QLTB.setVisible(false);
    form_QLTK.setVisible(false);
    form_background.setVisible(false);
    displaytaikhoan();
    }

    @FXML
    private Button main_logoutbtn;
    private double x = 0;
    private double y = 0;
    public void logout_btn(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
            alert.setContentText(" Bạn có chắc chắn muốn đăng xuất");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                // ẩn form dashboard
                main_logoutbtn.getScene().getWindow().hide();
                //liên kết tới form login và hiển thị form login
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        x = event.getX();
                        y = event.getY();
                    }
                });
                // event khi nhan va keos chuot .
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event){
                        stage.setX(event.getScreenX() -x);
                        stage.setY(event.getScreenY() -y);
                        stage.setOpacity(.8);// set do mo
                    }
                });
                root.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event){

                        stage.setOpacity(1);
                    }
                });


                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            }} catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private MenuItem main_menuMT;
    public void Main_menuMT(){
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(true);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLM_comboPM();
        QLT_comboPM();
        display_listTBcothemuon();
        display_listTBtrongphieu();
    }

    @FXML
    private MenuItem main_menuQLLTB;
    public void Main_menuQLLTB(){
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(true);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLNCCSX_form.setDisable(true);
        QLVT_form.setDisable(true);
        QLLTB_form.setDisable(false);
        display_listLoaiTb();
    }

    @FXML
    private MenuItem main_menuQLNCCNSX;
    public void Main_menuQLNCCNSX(){
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(true);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLNCCSX_form.setDisable(false);
        QLVT_form.setDisable(true);
        QLLTB_form.setDisable(true);
        QLNCCSX_Loai();
    }

    @FXML
    private MenuItem man_menuQLVT;
    public void Main_menuQLVT(){
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(true);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLNCCSX_form.setDisable(true);
        QLVT_form.setDisable(false);
        QLLTB_form.setDisable(true);
        QLVT_combonv();
        display_listVT();
    }

    @FXML
    private MenuItem main_menuSUANM;

    public void main_menuSUANM() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(true);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPM_form.setDisable(true);
        QLNM_form.setDisable(false);
        QLNM_addbtn.setDisable(true);
        QLNM_updatebtn.setDisable(false);
        QLNM_deletebtn.setDisable(true);
        QLNM_tfmanm.setEditable(true);
        QLNM_tfhoten.setEditable(true);
        QLNM_tfchucvu.setEditable(true);
        QLNM_tfkhoavien.setEditable(true);
        QLNM_tfemail.setEditable(true);
        display_listNM();
    }

    @FXML
    private MenuItem main_menuSUANV;
    public void main_menuSUANV() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(true);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLNV_addbtn.setDisable(true);
        QLNV_updatebtn.setDisable(false);
        QLNV_deletebtn.setDisable(true);
        QLNV_clearbtn.setDisable(false);
        QLNV_tfManv.setEditable(true);
        QLNV_hoTen.setEditable(true);
        QLNV_CCCD.setEditable(true);
        QLNV_ngaycongtac.setEditable(true);
        QLNV_email.setEditable(true);
        display_listNV();}

    @FXML
    private MenuItem main_menuSUAPM;
    public void main_menuSUAPM() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(true);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPM_form.setDisable(false);
        QLNM_form.setDisable(true);
        QLPM_addbtn.setDisable(true);
        QLPM_updatebtn.setDisable(false);
        QLPM_deletebtn.setDisable(true);
        QLPM_tfmanm.setEditable(true);
        QLPM_tfmapm.setEditable(true);
        QLPM_tfngaytra.setEditable(true);
        QLPM_combonv();
        display_listPM();
    }

    @FXML
    private MenuItem main_menuSUAPN;
    public void main_menuSUAPN() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(true);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPN_deletebtn.setDisable(true);
        QLPN_addbtn.setDisable(true);
        QLPN_updatebtn.setDisable(false);
        QLTB_NhapTB_form.setDisable(true);
        QLPN_form.setDisable(false);
        QLPN_tfmaPN.setEditable(true);
        QLPN_tfnhanvienlap.setEditable(true);
        QLPN_comboncc();
        display_listPN();
    }


    @FXML
    private MenuItem main_menuSUATB;
    public void main_menuSUATB() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(true);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLTB_NhapTB_form.setDisable(false);
        QLPN_form.setDisable(true);
        QLTB_NhapTB_addTBbtn.setDisable(true);
        QLTB_NhapTB_updateTBbtn.setDisable(false);
        QLTB_NhapTB_deleteTBbtn.setDisable(true);
        QLTB_NhapTB_tfmatb.setEditable(true);
        QLTB_NhapTB_tfmota.setEditable(true);
        QLTB_NhapTB_tftentb.setEditable(true);
        QLTB_NhapTB_tfnamsx.setEditable(true);
        QLPN_comboNSX();
        QLTB_NhapTB_tfkieutimkiem();
        QLTB_NhapTB_maPN();
        QLTB_NhapTB_maVT();
        QLTB_NhapTB_comboMaLTB();
        QLTB_NhapTB_tinhtrangTB();
        display_listTB();
    }



    @FXML
    private MenuItem main_menuTAOPN;
    public void Main_menuTAOPN() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(true);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPN_deletebtn.setDisable(true);
        QLPN_addbtn.setDisable(false);
        QLPN_updatebtn.setDisable(true);
        QLTB_NhapTB_form.setDisable(true);
        QLPN_form.setDisable(false);
        QLPN_tfmaPN.setEditable(true);
        QLPN_tfnhanvienlap.setEditable(true);
        QLPN_comboncc();
        display_listPN();
    }


    @FXML
    private MenuItem main_menuTHEMNM;
    public void main_menuTHEMNM() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(true);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPM_form.setDisable(true);
        QLNM_form.setDisable(false);
        QLNM_addbtn.setDisable(false);
        QLNM_updatebtn.setDisable(true);
        QLNM_deletebtn.setDisable(true);
        QLNM_tfmanm.setEditable(true);
        QLNM_tfhoten.setEditable(true);
        QLNM_tfchucvu.setEditable(true);
        QLNM_tfkhoavien.setEditable(true);
        QLNM_tfemail.setEditable(true);
        display_listNM();
    }
    @FXML
    private MenuItem main_menuTHEMNV;
    public void main_menuTHEMNV() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(true);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLNV_addbtn.setDisable(false);
        QLNV_updatebtn.setDisable(true);
        QLNV_deletebtn.setDisable(true);
        QLNV_clearbtn.setDisable(false);
        QLNV_tfManv.setEditable(true);
        QLNV_hoTen.setEditable(true);
        QLNV_CCCD.setEditable(true);
        QLNV_ngaycongtac.setEditable(true);
        QLNV_email.setEditable(true);
        display_listNV();}
    @FXML
    private MenuItem main_menuTHEMTB;
    public void main_menuTHEMTB() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(true);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLTB_NhapTB_form.setDisable(false);
        QLPN_form.setDisable(true);
        QLTB_NhapTB_addTBbtn.setDisable(false);
        QLTB_NhapTB_updateTBbtn.setDisable(true);
        QLTB_NhapTB_deleteTBbtn.setDisable(true);
        QLTB_NhapTB_tfmatb.setEditable(true);
        QLTB_NhapTB_tfmota.setEditable(true);
        QLTB_NhapTB_tftentb.setEditable(true);
        QLTB_NhapTB_tfnamsx.setEditable(true);
        QLPN_comboNSX();
        QLTB_NhapTB_tfkieutimkiem();
        QLTB_NhapTB_maPN();
        QLTB_NhapTB_maVT();
        QLTB_NhapTB_comboMaLTB();
        QLTB_NhapTB_tinhtrangTB();
        display_listTB();
    }
    @FXML
    private MenuItem main_menuTaoPM;
    public void main_menuTAOPM() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(true);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPM_form.setDisable(false);
        QLNM_form.setDisable(true);
        QLPM_addbtn.setDisable(false);
        QLPM_updatebtn.setDisable(true);
        QLPM_deletebtn.setDisable(true);
        QLPM_tfmanm.setEditable(true);
        QLPM_tfmapm.setEditable(true);
        QLPM_tfngaytra.setEditable(true);
        QLPM_combonv();
        display_listPM();
    }

    @FXML
    private MenuItem main_menuThemTK;
    public void main_menuThemTK(){
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(true);
        form_background.setVisible(false);
        display_listTK();
        QLTK_btndelete.setDisable(true);
        QLTK_btnadd.setDisable(false);
        QLTK_tftentk.setEditable(true);
        QLTK_pfmk.setEditable(true);
        QLTK_ListChucvu();
        QLTK_btnClear.setDisable(false);
    }

    @FXML
    private MenuItem main_menuXOANM;
    public void main_menuXOANM() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(true);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPM_form.setDisable(true);
        QLNM_form.setDisable(false);
        QLNM_addbtn.setDisable(true);
        QLNM_updatebtn.setDisable(true);
        QLNM_deletebtn.setDisable(false);
        QLNM_tfmanm.setEditable(false);
        QLNM_tfhoten.setEditable(false);
        QLNM_tfchucvu.setEditable(false);
        QLNM_tfkhoavien.setEditable(false);
        QLNM_tfemail.setEditable(false);
        display_listNM();
    }
    @FXML
    private MenuItem main_menuXOANV;
    public void main_menuXoaNV() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(true);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLNV_addbtn.setDisable(true);
        QLNV_updatebtn.setDisable(true);
        QLNV_deletebtn.setDisable(false);
        QLNV_clearbtn.setDisable(false);
        QLNV_tfManv.setEditable(false);
        QLNV_hoTen.setEditable(false);
        QLNV_CCCD.setEditable(false);
        QLNV_ngaycongtac.setEditable(false);
        QLNV_email.setEditable(false);
        display_listNV();
    }
    @FXML
    private MenuItem main_menuXOAPM;
    public void main_menuXOAPM() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(true);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPM_form.setDisable(false);
        QLNM_form.setDisable(true);
        QLPM_addbtn.setDisable(true);
        QLPM_updatebtn.setDisable(true);
        QLPM_deletebtn.setDisable(false);
        QLPM_tfmanm.setEditable(false);
        QLPM_tfmapm.setEditable(false);
        QLPM_tfngaytra.setEditable(false);
        display_listPM();
    }
    @FXML
    private MenuItem main_menuXOAPN;
    public void main_menuXOAPN() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(true);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLPN_deletebtn.setDisable(false);
        QLPN_addbtn.setDisable(true);
        QLPN_updatebtn.setDisable(true);
        QLTB_NhapTB_form.setDisable(true);
        QLPN_form.setDisable(false);
        QLPN_tfmaPN.setEditable(false);
        QLPN_tfnhanvienlap.setEditable(false);
        QLPN_comboncc();
        display_listPN();
    }


    @FXML
    private MenuItem main_menuXOATB;
    public void main_menuXOATB() {
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(true);
        form_QLTK.setVisible(false);
        form_background.setVisible(false);
        QLTB_NhapTB_form.setDisable(false);
        QLPN_form.setDisable(true);
        QLTB_NhapTB_addTBbtn.setDisable(true);
        QLTB_NhapTB_updateTBbtn.setDisable(true);
        QLTB_NhapTB_deleteTBbtn.setDisable(false);
        QLTB_NhapTB_tfmatb.setEditable(false);
        QLTB_NhapTB_tfmota.setEditable(false);
        QLTB_NhapTB_tftentb.setEditable(false);
        QLTB_NhapTB_tfnamsx.setEditable(false);
        QLPN_comboNSX();
        QLTB_NhapTB_tfkieutimkiem();
        QLTB_NhapTB_maPN();
        QLTB_NhapTB_maVT();
        QLTB_NhapTB_comboMaLTB();
        QLTB_NhapTB_tinhtrangTB();
        display_listTB();
    }

    @FXML
    private MenuItem main_menuXOATK;
    public void main_menuXoaTK(){
        form_DOIMK.setVisible(false);
        form_QLKHAC.setVisible(false);
        form_QLMT.setVisible(false);
        form_QLNMPM.setVisible(false);
        form_QLNV.setVisible(false);
        form_QLTB.setVisible(false);
        form_QLTK.setVisible(true);
        form_background.setVisible(false);
        display_listTK();
        QLTK_btnadd.setDisable(true);
        QLTK_tftentk.setEditable(false);
        QLTK_pfmk.setEditable(false);
        QLTK_btndelete.setDisable(false);
        QLTK_btnClear.setDisable(true);
    }

    @FXML
    private Button main_minisize;
    public void minisize(){
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private Label main_user;
    public void displayUser(){
        String user = taikhoan.taikhoan;
       main_user.setAlignment(Pos.CENTER);
       main_user.setText(user.substring(0,1).toUpperCase() + user.substring(1));
    }
    @FXML
    private BorderPane main_form;
    //end main form
    //DOIMK_FORM
    @FXML
    private Button DMK_doibtn;
    public void DMK_doibtn(){
    String sql = "UPDATE nguoisudung SET matkhau = '"+DMK_mkmoi.getText()+"' WHERE taikhoan = '"
            +DMK_tentk.getText()+"'";
    String sqlCheck = "SELECT * FROM nguoisudung WHERE taikhoan = '"+DMK_tentk.getText()+"' AND matkhau = '"
            +DMK_matkhauht.getText()+"'";
    conn = dataBase.connectDB();
    try {
        Alert alert;
        if(DMK_matkhauht.getText().isEmpty() || DMK_mkmoi.getText().isEmpty() || DMK_xacnhanmk.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" ERROR MESSAGE");
            alert.setHeaderText(null);
            alert.setContentText("Hoàn thiện thông tin");
            alert.showAndWait();
        }else {
            prestt = conn.prepareStatement(sqlCheck);
            rs1 = prestt.executeQuery();
            if (rs1.next()){
            if(DMK_xacnhanmk.getText().equals(DMK_mkmoi.getText())){
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn đổi mật khẩu");
                Optional<ButtonType> option = alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("ĐỔI MẬT KHẨU THÀNH CÔNG");
                alert.showAndWait();}
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Kiểm tra lại mật khẩu mới");
                alert.showAndWait();
            }

            }else {
                System.out.println(DMK_matkhauht.getText());
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Kiểm tra lại mật khẩu hiện tại");
                alert.showAndWait();
            }

        }

    }catch (Exception e){e.printStackTrace();
    } }

    @FXML
    private TextField DMK_matkhauht;

    @FXML
    private PasswordField DMK_mkmoi;

    @FXML
    private TextField DMK_tentk;
    public void displaytaikhoan(){
        String user = taikhoan.taikhoan;
        DMK_tentk.setText(user);
    }
    @FXML
    private PasswordField DMK_xacnhanmk;
    //end DOIMKFORM


   // quan li tai khoan
    @FXML
    private TableColumn<nguoisudung, String> QLTK_ColChucVu;

    @FXML
    private TableColumn<nguoisudung, String> QLTK_ColMk;

    @FXML
    private TableColumn<nguoisudung, String> QLTK_ColTk;

    @FXML
    private TableView<nguoisudung> QLTK_tableview;
    public ObservableList<nguoisudung> select_listTk(){
        String sql = "SELECT * FROM nguoisudung";
        conn = dataBase.connectDB();
        ObservableList<nguoisudung> listTK = FXCollections.observableArrayList();
        try {
            nguoisudung taikhoan;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
               taikhoan = new nguoisudung(rs.getString("taikhoan"),
                       rs.getString("chucvu"),rs.getString("matkhau"));
               listTK.add(taikhoan);
            }

        }catch (Exception e){e.printStackTrace();}
        return listTK;
    }

    private ObservableList<nguoisudung> listTK;
    public void display_listTK(){
        listTK = select_listTk();
        QLTK_ColTk.setCellValueFactory(new PropertyValueFactory<>("taikhoan"));
        QLTK_ColChucVu.setCellValueFactory(new PropertyValueFactory<>("chucvu"));
        QLTK_ColMk.setCellValueFactory(new PropertyValueFactory<>("matkhau"));
        QLTK_tableview.setItems(listTK);
    }

    public void taikhoanSlect(){
        nguoisudung taikhoan = QLTK_tableview.getSelectionModel().getSelectedItem();
        int num = QLTK_tableview.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
       QLTK_pfmk.setText(String.valueOf(taikhoan.getMatkhau()));
       QLTK_tftentk.setText(String.valueOf(taikhoan.getTaikhoan()));
    }
    @FXML
    private Button QLTK_btnClear;
    public void QLTK_btnClear(){
        QLTK_combochucvu.setValue(null);
        QLTK_tftentk.setText("");
        QLTK_pfmk.setText("");
    }

    @FXML
    private Button QLTK_btnadd;
    public void QLTK_addbtn(){
         String sql = "INSERT INTO nguoisudung (taikhoan,matkhau,chucvu)"
                 + "VALUES(?,?,?)";
         conn = dataBase.connectDB();
         String sqlCheck = "SELECT * FROM nguoisudung WHERE taikhoan = '"+QLTK_tftentk.getText()+"'";
         try{
             Alert alert;
             prestt = conn.prepareStatement(sqlCheck);
             rs = prestt.executeQuery();
             if(QLTK_tftentk.getText().isEmpty() || QLTK_pfmk.getText().isEmpty() ||
                     QLTK_combochucvu.getSelectionModel().getSelectedItem() == null ){
                 alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle(" ERROR MESSAGE");
                 alert.setHeaderText(null);
                 alert.setContentText("Hoàn thiên thông tin");
                 alert.showAndWait();
             }
             else {
             if(rs.next()){
                 alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle(" ERROR MESSAGE");
                 alert.setHeaderText(null);
                 alert.setContentText("Tài khoản đã tồn tại");
                 alert.showAndWait();
             }else {
                 prestt = conn.prepareStatement(sql);
                 prestt.setString(1, QLTK_tftentk.getText());
                 prestt.setString(2, QLTK_pfmk.getText());
                 prestt.setString(3, (String) QLTK_combochucvu.getSelectionModel().getSelectedItem());
                 prestt.executeUpdate();

                 alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle(" INFORMATION");
                 alert.setHeaderText(null);
                 alert.setContentText("Thêm tài khoản thành công");
                 alert.showAndWait();
                 display_listTK();
                 QLTK_btnClear();


             }
             }
         }catch (Exception e){e.printStackTrace();}

    }

    @FXML
    private Button QLTK_btndelete;
    public void QLTK_btndelete(){
        String sql = "DELETE FROM nguoisudung WHERE taikhoan = '"+QLTK_tftentk.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
            alert.setContentText(" Bạn có chắc chắn muốn xóa tài khoản này?");
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText(" xóa tài khoản thành công ");
                alert.showAndWait();
                display_listTK();
                QLTK_btnClear();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private Button QLTK_btntimkiem;
    public void QLTK_btnTimkiem(){
        String sql = "SELECT * FROM nguoisudung WHERE taikhoan = '"+QLTK_tftimkiem.getText()+"'";
        conn =dataBase.connectDB();
        try{
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            ObservableList<nguoisudung> listTimkiem = FXCollections.observableArrayList();
            nguoisudung taiKhoanTim;
            while (rs.next()){
               taiKhoanTim = new nguoisudung(rs.getString("taikhoan"),rs.getString("chucvu")
               ,rs.getString("matkhau"));
               listTimkiem.add(taiKhoanTim);}

                QLTK_ColTk.setCellValueFactory(new PropertyValueFactory<>("taikhoan"));
                QLTK_ColChucVu.setCellValueFactory(new PropertyValueFactory<>("chucvu"));
                QLTK_ColMk.setCellValueFactory(new PropertyValueFactory<>("matkhau"));
                QLTK_tableview.setItems(listTimkiem);


        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private ComboBox<?> QLTK_combochucvu;
    private String[] ListChucvu = {"Nhân viên","Lãnh đạo","admin"};
    public void QLTK_ListChucvu() {
        List<String> listChucvu = new ArrayList<>();
        for (String data : ListChucvu) {
            listChucvu.add(data);
        }
        ObservableList listDataChucvu = FXCollections.observableArrayList(listChucvu);
        QLTK_combochucvu.setItems(listDataChucvu);
    }

    @FXML
    private TextField QLTK_pfmk;

    @FXML
    private TextField QLTK_tftentk;

    @FXML
    private TextField QLTK_tftimkiem;

    //end quanlitk

    //Quanlynhanvien
    @FXML
    private TextField QLNV_CCCD;

    @FXML
    private Button QLNV_addbtn;
    public void QLNV_addbtn(){
        String sql = "INSERT INTO nhanvien (manhanvien,hoten,CCCD,ngaycongtac,email)"
                + "VALUES(?,?,?,?,?)";
        conn = dataBase.connectDB();
        String sqlCheck = "SELECT * FROM nhanvien WHERE manhanvien = '"+QLNV_tfManv.getText()+"'";
        try{
            Alert alert;
            prestt = conn.prepareStatement(sqlCheck);
            rs = prestt.executeQuery();
            if(QLNV_tfManv.getText().isEmpty() || QLNV_hoTen.getText().isEmpty() ||
                    QLNV_CCCD.getText().isEmpty() || QLNV_ngaycongtac.getText().isEmpty() || QLNV_email.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(" ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("nhân viên đã có trong hệ thống");
                    alert.showAndWait();
                }else {
                    prestt = conn.prepareStatement(sql);
                    prestt.setString(1, QLNV_tfManv.getText());
                    prestt.setString(2,QLNV_hoTen.getText());
                    prestt.setString(3, QLNV_CCCD.getText());
                    prestt.setString(4, QLNV_ngaycongtac.getText());
                    prestt.setString(5, QLNV_email.getText());
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm tài khoản thành công");
                    alert.showAndWait();
                    display_listNV();
                    QLNV_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private Button QLNV_clearbtn;
    public void QLNV_clearbtn(){
        QLNV_tfManv.setText("");
        QLNV_hoTen.setText("");
        QLNV_CCCD.setText("");
        QLNV_ngaycongtac.setText("");
        QLNV_email.setText("");
    }

    @FXML
    private TableView<nhanvien> QLNV_tableView;

    @FXML
    private TableColumn<nhanvien, String> QLNV_colCCCD;

    @FXML
    private TableColumn<nhanvien, String> QLNV_colEmail;

    @FXML
    private TableColumn<nhanvien, String> QLNV_colHoTen;

    @FXML
    private TableColumn<nhanvien, String> QLNV_colMaNv;

    @FXML
    private TableColumn<nhanvien, String> QLNV_colNgaycongtac;
    public ObservableList<nhanvien> select_listNV(){
        String sql = "SELECT * FROM nhanvien";
        conn = dataBase.connectDB();
        ObservableList<nhanvien> listNV = FXCollections.observableArrayList();
        try {
            nhanvien Nhanvien;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
                Nhanvien = new nhanvien(rs.getString("manhanvien"),rs.getString("hoten")
                ,rs.getString("CCCD"),rs.getDate("ngaycongtac"),rs.getString("email"));
                listNV.add(Nhanvien);
            }

        }catch (Exception e){e.printStackTrace();}
        return listNV;
    }

    private ObservableList<nhanvien> listNV;
    public void display_listNV(){
        listNV = select_listNV();
        QLNV_colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoten"));
        QLNV_colMaNv.setCellValueFactory(new PropertyValueFactory<>("manhanvien"));
        QLNV_colCCCD.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        QLNV_colNgaycongtac.setCellValueFactory(new PropertyValueFactory<>("ngaycongtac"));
        QLNV_colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        QLNV_tableView.setItems(listNV);
    }

    public void nhanvienSelect(){
        nhanvien Nhanvien = QLNV_tableView.getSelectionModel().getSelectedItem();
        int num = QLNV_tableView.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLNV_tfManv.setText(String.valueOf(Nhanvien.getManhanvien()));
        QLNV_hoTen.setText(String.valueOf(Nhanvien.getHoten()));
        QLNV_CCCD.setText(String.valueOf(Nhanvien.getCCCD()));
        QLNV_ngaycongtac.setText(String.valueOf(Nhanvien.getNgaycongtac()));
        QLNV_email.setText(String.valueOf(Nhanvien.getEmail()));
    }

    @FXML
    private Button QLNV_deletebtn;
    public void QLNV_deletebtn(){
        String sql = "DELETE FROM nhanvien WHERE manhanvien = '"+QLNV_tfManv.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            if(QLNV_tfManv.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn tài khoản cần xóa");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn xóa nhân viên này ra khỏi hệ thống ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prestt = conn.prepareStatement(sql);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText(" xóa thành công ");
                    alert.showAndWait();
                    display_listNV();
                    QLNV_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private TextField QLNV_email;

    @FXML
    private TextField QLNV_hoTen;

    @FXML
    private TextField QLNV_ngaycongtac;

    @FXML
    private TextField QLNV_tfManv;

    @FXML
    private TextField QLNV_tftimkiem;

    @FXML
    private Button QLNV_timkiembtn;
    public void QLNV_timkiembtn(){
        String sql = "SELECT * FROM nhanvien WHERE manhanvien = '"+QLNV_tftimkiem.getText()+"'";
        conn =dataBase.connectDB();
        try{
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            Alert alert;
            ObservableList<nhanvien> listTimkiem = FXCollections.observableArrayList();
            nhanvien nhanvientimkiem;
            if (QLNV_tftimkiem.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy điền thông tin tìm kiếm");
                alert.showAndWait();
            }else {
            while (rs.next()){
                nhanvientimkiem = new nhanvien(rs.getString("manhanvien"),rs.getString("hoten"),
                        rs.getString("CCCD"),rs.getDate("ngaycongtac"),rs.getString("email"));
                listTimkiem.add(nhanvientimkiem);}
                QLNV_colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoten"));
                QLNV_colMaNv.setCellValueFactory(new PropertyValueFactory<>("manhanvien"));
                QLNV_colCCCD.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
                QLNV_colNgaycongtac.setCellValueFactory(new PropertyValueFactory<>("ngaycongtac"));
                QLNV_colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                QLNV_tableView.setItems(listTimkiem);
            }

        }catch (Exception e){e.printStackTrace();}
    }


    @FXML
    private Button QLNV_updatebtn;
    public void QLNV_update(){
        conn = dataBase.connectDB();
        Alert alert;
        String sql = "UPDATE nhanvien SET hoten = '"
                +QLNV_hoTen.getText()+"', CCCD = '"
                +QLNV_CCCD.getText()+"', ngaycongtac = '"
                +QLNV_ngaycongtac.getText()+"', email = '"
                +QLNV_email.getText()+"' WHERE manhanvien = '"+QLNV_tfManv.getText()+"'";
        try{
            if(QLNV_tfManv.getText().isEmpty() || QLNV_hoTen.getText().isEmpty() ||
                    QLNV_CCCD.getText().isEmpty() || QLNV_ngaycongtac.getText().isEmpty() || QLNV_email.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
                display_listNV();
            }
        }catch (Exception e){e.printStackTrace();}
    }
    //QUanlynhanvien

    //quanlyphieunhap

    @FXML
    private ComboBox<?> QLPN_Nhacungcap;
    public void QLPN_comboncc(){
        String sql = "SELECT mancc FROM nhacungcap";
        conn = dataBase.connectDB();
        try{
            ObservableList listNCC = FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
                listNCC.add(rs.getString("mancc"));
            }
            QLPN_Nhacungcap.setItems(listNCC);
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private AnchorPane QLPN_form;

    @FXML
    private Button QLPN_addbtn;
    public void QLPN_addbtn(){
        String sql = "INSERT INTO phieunhap (maphieunhap,ngaynhap,manhanvien,mancc)"
                + "VALUES(?,?,?,?)";
        conn = dataBase.connectDB();
        String sqlCheck = "SELECT * FROM phieunhap WHERE maphieunhap = '"+QLPN_tfmaPN.getText()+"'";
        try{
            Alert alert;
            prestt = conn.prepareStatement(sqlCheck);
            rs = prestt.executeQuery();
            if(QLPN_tfmaPN.getText().isEmpty() || QLPN_tfnhanvienlap.getText().isEmpty() || QLPN_Nhacungcap.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(" ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("phiếu nhập đã có trong hệ thống");
                    alert.showAndWait();
                }else {
                    prestt = conn.prepareStatement(sql);
                    prestt.setString(1, QLPN_tfmaPN.getText());
                    prestt.setString(3, QLPN_tfnhanvienlap.getText());
                    prestt.setString(4,(String) QLPN_Nhacungcap.getSelectionModel().getSelectedItem());
                    Date date = new Date();
                    Date sqlDate = new Date(date.getTime());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(sqlDate);
                    prestt.setString(2,formattedDate);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm phiếu nhập thành công");
                    alert.showAndWait();
                    display_listPN();
                    QLPN_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }


    @FXML
    private Button QLPN_clearbtn;
    public void QLPN_clearbtn(){
        QLPN_tfnhanvienlap.setText("");
        QLPN_tfmaPN.setText("");
        QLPN_Nhacungcap.setSelectionModel(null);
    }

    @FXML
    private TableView<PhieuNhap> QLPN_tableview;

    @FXML
    private TableColumn<PhieuNhap, String> QLPN_colNCC;

    @FXML
    private TableColumn<PhieuNhap, String> QLPN_coldate;

    @FXML
    private TableColumn<PhieuNhap, String> QLPN_colmaNV;

    @FXML
    private TableColumn<PhieuNhap, String> QLPN_colmaPN;
    public ObservableList<PhieuNhap> select_phieunhap(){
        String sql = "SELECT * FROM phieunhap";
        conn = dataBase.connectDB();
        ObservableList<PhieuNhap> ListPN = FXCollections.observableArrayList();
        try {
            PhieuNhap PN;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
               PN = new PhieuNhap(rs.getString("maphieunhap"),rs.getDate("ngaynhap")
                        ,rs.getString("manhanvien"),rs.getString("mancc"));
                ListPN.add(PN);
            }

        }catch (Exception e){e.printStackTrace();}
        return ListPN;
    }

    private ObservableList<PhieuNhap> ListPN ;
    public void display_listPN(){
        ListPN = select_phieunhap();
        QLPN_colmaPN.setCellValueFactory(new PropertyValueFactory<>("maphieunhap"));
        QLPN_coldate.setCellValueFactory(new PropertyValueFactory<>("ngaynhap"));
        QLPN_colmaNV.setCellValueFactory(new PropertyValueFactory<>("manhanvien"));
        QLPN_colNCC.setCellValueFactory(new PropertyValueFactory<>("mancc"));
        QLPN_tableview.setItems(ListPN);
    }

    public void PhieunhapTableselect(){
        PhieuNhap PN = QLPN_tableview.getSelectionModel().getSelectedItem();
        int num = QLPN_tableview.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLPN_tfmaPN.setText(String.valueOf(PN.getMaphieunhap()));
        QLPN_tfnhanvienlap.setText(String.valueOf(PN.getManhanvien()));
    }

    @FXML
    private Button QLPN_deletebtn;
    public void QLPN_deletebtn(){
        String sql = "DELETE FROM phieunhap WHERE maphieunhap = '"+QLPN_tfmaPN.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            if(QLPN_tfmaPN.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn phiếu cần xóa");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn xóa PHIẾU này?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prestt = conn.prepareStatement(sql);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText(" xóa thành công ");
                    alert.showAndWait();
                    display_listPN();
                    QLPN_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private TextField QLPN_tfmaPN;

    @FXML
    private TextField QLPN_tfnhanvienlap;

    @FXML
    private TextField QLPN_tftimkiem;

    @FXML
    private Button QLPN_timkiembtn;
    public void QLPN_timkiembtn(){
        String sql = "SELECT * FROM phieunhap WHERE manhanvien = '"+QLPN_tfmaPN.getText()+"'";
        conn =dataBase.connectDB();
        try{
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            Alert alert;
            ObservableList<PhieuNhap> listTimkiem = FXCollections.observableArrayList();
            PhieuNhap PN;
            if (QLPN_tfmaPN.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy điền thông tin tìm kiếm");
                alert.showAndWait();
            }else {
                while (rs.next()){
                    PN= new PhieuNhap(rs.getString("maphieunhap"),rs.getDate("ngaynhap"),
                            rs.getString("manhanvien"),rs.getString("mancc"));
                    listTimkiem.add(PN);}
                    QLPN_colmaPN.setCellValueFactory(new PropertyValueFactory<>("maphieunhap"));
                    QLPN_coldate.setCellValueFactory(new PropertyValueFactory<>("ngaynhap"));
                    QLPN_colmaNV.setCellValueFactory(new PropertyValueFactory<>("manhanvien"));
                    QLPN_colNCC.setCellValueFactory(new PropertyValueFactory<>("mancc"));
                    QLPN_tableview.setItems(listTimkiem);
                }

        }catch (Exception e){e.printStackTrace();}
    }


    @FXML
    private Button QLPN_updatebtn;
    public void QLPN_updatebtn(){
        conn = dataBase.connectDB();
        Date date = new Date();
        Date sqlDate = new Date(date.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(sqlDate);
        Alert alert;
        String sql = "UPDATE phieunhap SET ngaynhap = '"
                +formattedDate+"', manhanvien = '"
                +QLPN_tfnhanvienlap.getText()+"', mancc = '"
                +QLPN_Nhacungcap.getSelectionModel().getSelectedItem()+"' WHERE maphieunhap = '"+QLPN_tfmaPN.getText()+"'";
        try{
            if(QLPN_tfmaPN.getText().isEmpty() || QLPN_tfnhanvienlap.getText().isEmpty() ||
                    QLPN_Nhacungcap.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
                display_listPN();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    //quanlyphieunhap

    //QLTB_NHapTB

    @FXML
    private ComboBox<?> QLTB_NhapTB_MaNSX;
    public void QLPN_comboNSX(){
        String sql = "SELECT mansx FROM nhasanxuat";
        conn = dataBase.connectDB();
        try{
            ObservableList listNSX= FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
                listNSX.add(rs.getString("mansx"));
            }
            QLTB_NhapTB_MaNSX.setItems(listNSX);
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private ComboBox<?> QLTB_NhapTB_MaloaiTB;
    public void QLTB_NhapTB_comboMaLTB(){
        String sql = "SELECT maloaitb FROM loaithietbi";
        conn = dataBase.connectDB();
        try{
            ObservableList listLTB= FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
                listLTB.add(rs.getString("maloaitb"));
            }
            QLTB_NhapTB_MaloaiTB.setItems(listLTB);
        }catch (Exception e){e.printStackTrace();}
    }
    @FXML
    private Button QLTB_NhapTB_addTBbtn;
    public void QLTB_NhapTB_addbtn(){
        String sql = "INSERT INTO thietbi (matb,tentb,namsanxuat,mota,tinhtrang,maloaitb,mansx,mavt,maphieunhap)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        conn = dataBase.connectDB();
        String sqlCheck = "SELECT * FROM thietbi WHERE matb = '"+QLTB_NhapTB_tfmatb.getText()+"'";
        try{
            Alert alert;
            prestt = conn.prepareStatement(sqlCheck);
            rs = prestt.executeQuery();
            if(QLTB_NhapTB_tfmatb.getText().isEmpty() || QLTB_NhapTB_tftentb.getText().isEmpty() || QLTB_NhapTB_tfnamsx.getText().isEmpty() ||
                    QLTB_NhapTB_tfmota.getText().isEmpty() || QLTB_NhapTB_tinhtrangTB.getSelectionModel().getSelectedItem() == null ||
            QLTB_NhapTB_MaloaiTB.getSelectionModel().getSelectedItem() == null ||
            QLTB_NhapTB_maVT.getSelectionModel().getSelectedItem() == null ||
            QLTB_NhapTB_maPN.getSelectionModel().getSelectedItem() == null ||
            QLTB_NhapTB_MaNSX.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(" ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Thiết bị đã tồn tại");
                    alert.showAndWait();
                }else {
                    prestt = conn.prepareStatement(sql);
                    prestt.setString(1, QLTB_NhapTB_tfmatb.getText());
                    prestt.setString(2, QLTB_NhapTB_tftentb.getText());
                    prestt.setString(3, QLTB_NhapTB_tfnamsx.getText());
                    prestt.setString(4, QLTB_NhapTB_tfmota.getText());
                    prestt.setString(5,(String) QLTB_NhapTB_tinhtrangTB.getSelectionModel().getSelectedItem());
                    prestt.setString(6,(String) QLTB_NhapTB_MaloaiTB.getSelectionModel().getSelectedItem());
                    prestt.setString(7,(String) QLTB_NhapTB_MaNSX.getSelectionModel().getSelectedItem());
                    prestt.setString(8,(String) QLTB_NhapTB_maVT.getSelectionModel().getSelectedItem());
                    prestt.setString(9,(String) QLTB_NhapTB_maPN.getSelectionModel().getSelectedItem());

                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm thiết bị thành công");
                    alert.showAndWait();
                    display_listTB();
                    QLTB_NhapTB_clearTBbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }


    @FXML
    private Button QLTB_NhapTB_clearTBbtn;
    public void QLTB_NhapTB_clearTBbtn(){
        QLTB_NhapTB_tfmatb.setText("");
        QLTB_NhapTB_tftentb.setText("");
        QLTB_NhapTB_tfnamsx.setText("");
        QLTB_NhapTB_tfmota.setText("");
        QLTB_NhapTB_MaloaiTB.setValue(null);
        QLTB_NhapTB_MaNSX.setValue(null);
        QLTB_NhapTB_maVT.setValue(null);
        QLTB_NhapTB_maPN.setValue(null);
        QLTB_NhapTB_tinhtrangTB.setValue(null);
    }
    @FXML
    private TableView<thietbi> QLTB_NhapTB_tablevoew;
    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colMaLoaiTB;

    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colMaNSX;

    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colMaTB;

    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colNamsxTB;

    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colPN;

    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colTenTB;

    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colTinhtrang;

    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colVT;

    @FXML
    private TableColumn<thietbi, String> QLTB_NhapTB_colmota;
    public ObservableList<thietbi> select_thietbi(){
        String sql = "SELECT * FROM thietbi";
        conn = dataBase.connectDB();
        ObservableList<thietbi> ListTB= FXCollections.observableArrayList();
        try {
            thietbi TB;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
                TB = new thietbi(rs.getString("matb"),rs.getString("tentb")
                        ,rs.getString("namsanxuat"),rs.getString("mota"),rs.getString("tinhtrang"),rs.getString("maloaitb"),
                        rs.getString("mansx"),rs.getString("mavt"),rs.getString("maphieunhap"));
                ListTB.add(TB);
            }

        }catch (Exception e){e.printStackTrace();}
        return ListTB;
    }

    private ObservableList<thietbi> ListTB ;
    public void display_listTB(){
        ListTB = select_thietbi();
        QLTB_NhapTB_colMaTB.setCellValueFactory(new PropertyValueFactory<>("matb"));
        QLTB_NhapTB_colTenTB.setCellValueFactory(new PropertyValueFactory<>("tentb"));
        QLTB_NhapTB_colNamsxTB.setCellValueFactory(new PropertyValueFactory<>("namsanxuat"));
        QLTB_NhapTB_colTinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
        QLTB_NhapTB_colmota.setCellValueFactory(new PropertyValueFactory<>("mota"));
        QLTB_NhapTB_colMaLoaiTB.setCellValueFactory(new PropertyValueFactory<>("maloaitb"));
        QLTB_NhapTB_colMaNSX.setCellValueFactory(new PropertyValueFactory<>("mansx"));
        QLTB_NhapTB_colVT.setCellValueFactory(new PropertyValueFactory<>("mavt"));
        QLTB_NhapTB_colPN.setCellValueFactory(new PropertyValueFactory<>("maphieunhap"));
        QLTB_NhapTB_tablevoew.setItems(ListTB);
    }

    public void TBTableselect(){
        thietbi TB = QLTB_NhapTB_tablevoew.getSelectionModel().getSelectedItem();
        int num =QLTB_NhapTB_tablevoew.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLTB_NhapTB_tfmatb.setText(String.valueOf(TB.getMatb()));
        QLTB_NhapTB_tftentb.setText(String.valueOf(TB.getTentb()));
        QLTB_NhapTB_tfmota.setText(String.valueOf(TB.getMota()));
        QLTB_NhapTB_tfnamsx.setText(String.valueOf(TB.getNamsanxuat()));
    }

    @FXML
    private Button QLTB_NhapTB_deleteTBbtn;
    public void QLTB_NhapTB_deleteTBbtn(){
        String sql = "DELETE FROM thietbi WHERE matb = '"+QLTB_NhapTB_tfmatb.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            if(QLTB_NhapTB_tfmatb.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn thiết bị cần xóa");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn xóa thiết bị này?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prestt = conn.prepareStatement(sql);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText(" xóa thành công ");
                    alert.showAndWait();
                    display_listTB();
                    QLTB_NhapTB_clearTBbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private AnchorPane QLTB_NhapTB_form;

    @FXML
    private ComboBox<?> QLTB_NhapTB_maPN;
    public void QLTB_NhapTB_maPN(){
        String sql = "SELECT maphieunhap FROM phieunhap";
        conn = dataBase.connectDB();
        try{
            ObservableList listPN= FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
                listPN.add(rs.getString("maphieunhap"));
            }
            QLTB_NhapTB_maPN.setItems(listPN);
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private ComboBox<?> QLTB_NhapTB_maVT;
    public void QLTB_NhapTB_maVT(){
        String sql = "SELECT mavt FROM vitri";
        conn = dataBase.connectDB();
        try{
            ObservableList listVT= FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
                listVT.add(rs.getString("mavt"));
            }
            QLTB_NhapTB_maVT.setItems(listVT);
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private ComboBox<?> QLTB_NhapTB_tfkieutimkiem;
    private String[] Typetimkiem = {"Tìm theo mã","Tìm theo tình trạng","Tìm theo vị trí"};
    public void QLTB_NhapTB_tfkieutimkiem(){
        List<String> listTypetimkiem = new ArrayList<>();
        for(String data: Typetimkiem){
            listTypetimkiem.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listTypetimkiem);
        QLTB_NhapTB_tfkieutimkiem.setItems(listData);
    }


    @FXML
    private TextField QLTB_NhapTB_tfmatb;

    @FXML
    private TextArea QLTB_NhapTB_tfmota;

    @FXML
    private TextField QLTB_NhapTB_tfnamsx;

    @FXML
    private TextField QLTB_NhapTB_tftentb;

    @FXML
    private TextField QLTB_NhapTB_tftimkiemTB;

    @FXML
    private Button QLTB_NhapTB_timkiembtn;
    public void QLTB_NhapTB_timkiembtn(){
        String sql1 = "SELECT * FROM thietbi WHERE matb = '"+QLTB_NhapTB_tftimkiemTB.getText()+"'";
        String sql2 = "SELECT * FROM thietbi WHERE mavt= '"+QLTB_NhapTB_tftimkiemTB.getText()+"'";
        String sql3 = "SELECT * FROM thietbi WHERE tinhtrang= '"+QLTB_NhapTB_tftimkiemTB.getText()+"'";
        conn =dataBase.connectDB();
        try{
            Alert alert;
            if(QLTB_NhapTB_tftimkiemTB.getText().isEmpty() || QLTB_NhapTB_tfkieutimkiem.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn kiểu tìm kiếm và điền thông tin tìm kiếm");
                alert.showAndWait();
            }else {
                if((String) QLTB_NhapTB_tfkieutimkiem.getSelectionModel().getSelectedItem() == "Tìm theo mã"){
                prestt = conn.prepareStatement(sql1);
                rs = prestt.executeQuery();
                ObservableList<thietbi> listTimkiem = FXCollections.observableArrayList();
                thietbi TB;
               while (rs.next()) {
                        TB = new thietbi(rs.getString("matb"),rs.getString("tentb")
                                ,rs.getString("namsanxuat"),rs.getString("mota"),rs.getString("tinhtrang"),rs.getString("maloaitb"),
                                rs.getString("mansx"),rs.getString("mavt"),rs.getString("maphieunhap"));
                        listTimkiem.add(TB);}
                    QLTB_NhapTB_colMaTB.setCellValueFactory(new PropertyValueFactory<>("matb"));
                    QLTB_NhapTB_colTenTB.setCellValueFactory(new PropertyValueFactory<>("tentb"));
                    QLTB_NhapTB_colNamsxTB.setCellValueFactory(new PropertyValueFactory<>("namsanxuat"));
                    QLTB_NhapTB_colTinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
                    QLTB_NhapTB_colmota.setCellValueFactory(new PropertyValueFactory<>("mota"));
                    QLTB_NhapTB_colMaLoaiTB.setCellValueFactory(new PropertyValueFactory<>("maloaitb"));
                    QLTB_NhapTB_colMaNSX.setCellValueFactory(new PropertyValueFactory<>("mansx"));
                    QLTB_NhapTB_colVT.setCellValueFactory(new PropertyValueFactory<>("mavt"));
                    QLTB_NhapTB_colPN.setCellValueFactory(new PropertyValueFactory<>("maphieunhap"));
                    QLTB_NhapTB_tablevoew.setItems(listTimkiem);
                }
                if((String) QLTB_NhapTB_tfkieutimkiem.getSelectionModel().getSelectedItem() == "Tìm theo vị trí") {
                    prestt = conn.prepareStatement(sql2);
                    rs = prestt.executeQuery();
                    ObservableList<thietbi> listTimkiem = FXCollections.observableArrayList();
                    thietbi TB;
                    while (rs.next()) {
                        TB = new thietbi(rs.getString("matb"), rs.getString("tentb")
                                , rs.getString("namsanxuat"), rs.getString("mota"), rs.getString("tinhtrang"), rs.getString("maloaitb"),
                                rs.getString("mansx"), rs.getString("mavt"), rs.getString("maphieunhap"));
                        listTimkiem.add(TB);}
                        QLTB_NhapTB_colMaTB.setCellValueFactory(new PropertyValueFactory<>("matb"));
                        QLTB_NhapTB_colTenTB.setCellValueFactory(new PropertyValueFactory<>("tentb"));
                        QLTB_NhapTB_colNamsxTB.setCellValueFactory(new PropertyValueFactory<>("namsanxuat"));
                        QLTB_NhapTB_colTinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
                        QLTB_NhapTB_colmota.setCellValueFactory(new PropertyValueFactory<>("mota"));
                        QLTB_NhapTB_colMaLoaiTB.setCellValueFactory(new PropertyValueFactory<>("maloaitb"));
                        QLTB_NhapTB_colMaNSX.setCellValueFactory(new PropertyValueFactory<>("mansx"));
                        QLTB_NhapTB_colVT.setCellValueFactory(new PropertyValueFactory<>("mavt"));
                        QLTB_NhapTB_colPN.setCellValueFactory(new PropertyValueFactory<>("maphieunhap"));
                        QLTB_NhapTB_tablevoew.setItems(listTimkiem);
                }
                if((String) QLTB_NhapTB_tfkieutimkiem.getSelectionModel().getSelectedItem() == "Tìm theo tình trạng") {
                    prestt = conn.prepareStatement(sql3);
                    rs = prestt.executeQuery();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tìm kiếm theo tình trạng chỉ Hỏng và tốt");
                    alert.showAndWait();
                    ObservableList<thietbi> listTimkiem = FXCollections.observableArrayList();
                    thietbi TB;
                    while(rs.next()) {
                        TB = new thietbi(rs.getString("matb"), rs.getString("tentb")
                                , rs.getString("namsanxuat"), rs.getString("mota"), rs.getString("tinhtrang"), rs.getString("maloaitb"),
                                rs.getString("mansx"), rs.getString("mavt"), rs.getString("maphieunhap"));
                        listTimkiem.add(TB);}
                        QLTB_NhapTB_colMaTB.setCellValueFactory(new PropertyValueFactory<>("matb"));
                        QLTB_NhapTB_colTenTB.setCellValueFactory(new PropertyValueFactory<>("tentb"));
                        QLTB_NhapTB_colNamsxTB.setCellValueFactory(new PropertyValueFactory<>("namsanxuat"));
                        QLTB_NhapTB_colTinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
                        QLTB_NhapTB_colmota.setCellValueFactory(new PropertyValueFactory<>("mota"));
                        QLTB_NhapTB_colMaLoaiTB.setCellValueFactory(new PropertyValueFactory<>("maloaitb"));
                        QLTB_NhapTB_colMaNSX.setCellValueFactory(new PropertyValueFactory<>("mansx"));
                        QLTB_NhapTB_colVT.setCellValueFactory(new PropertyValueFactory<>("mavt"));
                        QLTB_NhapTB_colPN.setCellValueFactory(new PropertyValueFactory<>("maphieunhap"));
                        QLTB_NhapTB_tablevoew.setItems(listTimkiem);
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private ComboBox<?> QLTB_NhapTB_tinhtrangTB;
    private String[] Tinhtrang = {"Hỏng","tốt"};
    public void QLTB_NhapTB_tinhtrangTB(){
        List<String> listTinhtrang = new ArrayList<>();
        for(String data: Tinhtrang){
           listTinhtrang.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listTinhtrang);
        QLTB_NhapTB_tinhtrangTB.setItems(listData);
    }

    @FXML
    private Button QLTB_NhapTB_updateTBbtn;
    public void QLTB_NhapTB_updateTBbtn(){
        conn = dataBase.connectDB();
        Alert alert;
        String sql = "UPDATE thietbi SET tentb = '"
                +QLTB_NhapTB_tftentb.getText()+"', namsanxuat = '"
                +QLTB_NhapTB_tfnamsx.getText()+"', mota = '"
                +QLTB_NhapTB_tfmota.getText()+"', tinhtrang = '"+QLTB_NhapTB_tinhtrangTB.getSelectionModel().getSelectedItem()+"', maloaitb = '"
                +QLTB_NhapTB_MaloaiTB.getSelectionModel().getSelectedItem()+"', mansx = '"+QLTB_NhapTB_MaNSX.getSelectionModel().getSelectedItem()+
                "', mavt = '"+QLTB_NhapTB_maVT.getSelectionModel().getSelectedItem()+"', maphieunhap = '"
                +QLTB_NhapTB_maPN.getSelectionModel().getSelectedItem()+"' WHERE matb = '"+QLTB_NhapTB_tfmatb.getText()+"'";

        try{
            if(QLTB_NhapTB_tfmatb.getText().isEmpty() || QLTB_NhapTB_tftentb.getText().isEmpty() || QLTB_NhapTB_tfnamsx.getText().isEmpty() ||
                    QLTB_NhapTB_tfmota.getText().isEmpty() || QLTB_NhapTB_tinhtrangTB.getSelectionModel().getSelectedItem() == null ||
                    QLTB_NhapTB_MaloaiTB.getSelectionModel().getSelectedItem() == null ||
                    QLTB_NhapTB_maVT.getSelectionModel().getSelectedItem() == null ||
                    QLTB_NhapTB_maPN.getSelectionModel().getSelectedItem() == null ||
                    QLTB_NhapTB_MaNSX.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
                display_listTB();
            }
        }catch (Exception e){e.printStackTrace();}
    }


    //QLTB_NhapTB

    // quanlyNCC/NCC
    @FXML
    private Button QLNCCSX_addbtn;
    public void QLNCCSX_addbtn(){
        String sql1 = "INSERT INTO nhacungcap (mancc,tenncc,diachi,sdt)"
                + "VALUES(?,?,?,?)";
        String sql2 = "INSERT INTO nhasanxuat (mansx,tennsx,diachi,sdt)"
                + "VALUES(?,?,?,?)";
        conn = dataBase.connectDB();
        String sqlCheck1 = "SELECT * FROM nhacungcap WHERE mancc = '"+QLNCCSX_tfma.getText()+"'";
        String sqlCheck2 = "SELECT * FROM nhasanxuat WHERE mansx = '"+QLNCCSX_tfma.getText()+"'";
        try{
            Alert alert;
            if(QLNCCSX_tfma.getText().isEmpty() || QLNCCSX_tfdiachi.getText().isEmpty() ||
                    QLNCCSX_tften.getText().isEmpty() || QLNCCSX_tfsdt.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(QLNCCSX_comboloai.getSelectionModel().getSelectedItem() == "Nhà cung cấp"){
                    prestt = conn.prepareStatement(sqlCheck1);
                    rs = prestt.executeQuery();
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(" ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Nhà cung cấp đã tồn tại");
                    alert.showAndWait();
                }else {
                    prestt = conn.prepareStatement(sql1);
                    prestt.setString(1,QLNCCSX_tfma.getText());
                    prestt.setString(2,QLNCCSX_tften.getText());
                    prestt.setString(3, QLNCCSX_tfdiachi.getText());
                    prestt.setString(4, QLNCCSX_tfsdt.getText());
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm  thành công");
                    alert.showAndWait();
                    display_listNCCSX();
                    QLNCCSX_clearbtn();
                }
                }
                if(QLNCCSX_comboloai.getSelectionModel().getSelectedItem() == "Nhà sản xuất"){
                    prestt = conn.prepareStatement(sqlCheck2);
                    rs = prestt.executeQuery();
                    if(rs.next()){
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(" ERROR MESSAGE");
                        alert.setHeaderText(null);
                        alert.setContentText("Nhà sản xuất đã tồn tại");
                        alert.showAndWait();
                    }else {
                        prestt = conn.prepareStatement(sql2);
                        prestt.setString(1,QLNCCSX_tfma.getText());
                        prestt.setString(2,QLNCCSX_tften.getText());
                        prestt.setString(3, QLNCCSX_tfdiachi.getText());
                        prestt.setString(4, QLNCCSX_tfsdt.getText());
                        prestt.executeUpdate();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(" INFORMATION");
                        alert.setHeaderText(null);
                        alert.setContentText("Thêm  thành công");
                        alert.showAndWait();
                        display_listNCCSX();
                        QLNCCSX_clearbtn();
                    }
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private Button QLNCCSX_clearbtn;
    public void  QLNCCSX_clearbtn(){
        QLNCCSX_tfma.setText("");
        QLNCCSX_tften.setText("");
        QLNCCSX_tfsdt.setText("");
        QLNCCSX_tfdiachi.setText("");
    }

    @FXML
    private TableView<NCCSX> QLNCCSX_table;
    @FXML
    private TableColumn<NCCSX, String> QLNCCSX_colSDT;

    @FXML
    private TableColumn<NCCSX, String> QLNCCSX_coldiachi;

    @FXML
    private TableColumn<NCCSX, String> QLNCCSX_colma;

    @FXML
    private TableColumn<NCCSX, String> QLNCCSX_colten;
    public ObservableList<NCCSX> select_listNCCSX(){
        String sql1 = "SELECT * FROM nhasanxuat";
        String sql2 = "SELECT * FROM nhacungcap";
        conn = dataBase.connectDB();
        ObservableList<NCCSX> listNCCSX = FXCollections.observableArrayList();
        try {
            NCCSX nhasanxuat;
            if((String)QLNCCSX_comboloai.getSelectionModel().getSelectedItem() == "Nhà sản xuất"){
                prestt = conn.prepareStatement(sql1);
                rs = prestt.executeQuery();
                while (rs.next()){
                    nhasanxuat = new NCCSX(rs.getString("mansx"),rs.getString("tennsx")
                            ,rs.getString("diachi"),rs.getString("sdt"));
                    listNCCSX.add(nhasanxuat);
                }
            }
            if((String)QLNCCSX_comboloai.getSelectionModel().getSelectedItem() == "Nhà cung cấp"){
                prestt = conn.prepareStatement(sql2);
                rs = prestt.executeQuery();
                while (rs.next()){
                    nhasanxuat = new NCCSX(rs.getString("mancc"),rs.getString("tenncc")
                            ,rs.getString("diachi"),rs.getString("sdt"));
                    listNCCSX.add(nhasanxuat);
                }
            }


        }catch (Exception e){e.printStackTrace();}
        return listNCCSX;
    }

    private ObservableList<NCCSX> listNCCSX;
    public void display_listNCCSX(){
        listNCCSX = select_listNCCSX();
        QLNCCSX_colma.setCellValueFactory(new PropertyValueFactory<>("ma"));
        QLNCCSX_colten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        QLNCCSX_coldiachi.setCellValueFactory(new PropertyValueFactory<>("diachi"));
        QLNCCSX_colSDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        QLNCCSX_table.setItems(listNCCSX);
    }

    public void NCCSX_select(){
        NCCSX nccsx = QLNCCSX_table.getSelectionModel().getSelectedItem();
        int num = QLNCCSX_table.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLNCCSX_tfma.setText(String.valueOf(nccsx.getMa()));
        QLNCCSX_tften.setText(String.valueOf(nccsx.getTen()));
        QLNCCSX_tfdiachi.setText(String.valueOf(nccsx.getDiachi()));
        QLNCCSX_tfsdt.setText(String.valueOf(nccsx.getSDT()));
    }
    @FXML
    private ComboBox<?> QLNCCSX_comboloai;
    private String[] Loai = {"Nhà sản xuất","Nhà cung cấp"};
    public void QLNCCSX_Loai() {
        List<String> listloai = new ArrayList<>();
        for (String data : Loai) {
            listloai.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listloai);
        QLNCCSX_comboloai.setItems(listData);
    }

    @FXML
    private Button QLNCCSX_deletebtn;
    public void QLNCCSX_deletebtn(){
        String sql1 = "DELETE FROM nhasanxuat WHERE mansx = '"+QLNCCSX_tfma.getText()+"'";
        String sql2 = "DELETE FROM nhacungcap WHERE mancc = '"+QLNCCSX_tfma.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            if(QLNCCSX_tfma.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn NSX/NCC cần xóa");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn xóa NSX/NCC này ra khỏi hệ thống ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    if (QLNCCSX_comboloai.getSelectionModel().getSelectedItem() == "Nhà sản xuất") {
                        prestt = conn.prepareStatement(sql1);
                        prestt.executeUpdate();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText(" xóa thành công ");
                        alert.showAndWait();
                        display_listNCCSX();
                        QLNCCSX_clearbtn();
                    }
                    if (QLNCCSX_comboloai.getSelectionModel().getSelectedItem() == "Nhà cung cấp") {
                        prestt = conn.prepareStatement(sql2);
                        prestt.executeUpdate();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText(" xóa thành công ");
                        alert.showAndWait();
                        display_listNCCSX();
                        QLNCCSX_clearbtn();
                    }
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private AnchorPane QLNCCSX_form;

    @FXML
    private TextArea QLNCCSX_tfdiachi;

    @FXML
    private TextField QLNCCSX_tfma;

    @FXML
    private TextField QLNCCSX_tfsdt;

    @FXML
    private TextField QLNCCSX_tften;

    @FXML
    private Button QLNCCSX_updatebtn;
    public void QNCCSX_updatebtn(){
        conn = dataBase.connectDB();
        Alert alert;
        String sql1 = "UPDATE nhasanxuat SET tennsx = '"
                +QLNCCSX_tften.getText()+"', diachi = '"
                +QLNCCSX_tfdiachi.getText()+"', sdt = '"
                +QLNCCSX_tfsdt.getText()+"' WHERE mansx = '"+QLNCCSX_tfma.getText()+"'";
        String sql2 = "UPDATE nhacungcap SET tenncc = '"
                +QLNCCSX_tften.getText()+"', diachi = '"
                +QLNCCSX_tfdiachi.getText()+"', sdt = '"
                +QLNCCSX_tfsdt.getText()+"' WHERE mancc = '"+QLNCCSX_tfma.getText()+"'";
        try{
            if(QLNCCSX_tfma.getText().isEmpty() || QLNCCSX_tften.getText().isEmpty() ||
                    QLNCCSX_tfdiachi.getText().isEmpty() || QLNCCSX_tfsdt.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(QLNCCSX_comboloai.getSelectionModel().getSelectedItem() == "Nhà sản xuất"){
                prestt = conn.prepareStatement(sql1);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
                display_listNCCSX();}
                if(QLNCCSX_comboloai.getSelectionModel().getSelectedItem() == "Nhà cung cấp"){
                    prestt = conn.prepareStatement(sql2);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Cập nhật thành công");
                    alert.showAndWait();
                   display_listNCCSX();}
            }
        }catch (Exception e){e.printStackTrace();}
    }
    // quanlyNCC

    //quanlyloaiTB
    @FXML
    private AnchorPane QLLTB_form;

    @FXML
    private Button QLLTB_addbtn;
    public void QLLTB_addbtn(){
        String sql = "INSERT INTO loaithietbi (maloaitb,tenloaitb,mota)"
                + "VALUES(?,?,?)";
        conn = dataBase.connectDB();
        String sqlCheck = "SELECT * FROM loaithietbi WHERE maloaitb = '"+QLLTB_tfma.getText()+"'";
        try{
            Alert alert;
            prestt = conn.prepareStatement(sqlCheck);
            rs = prestt.executeQuery();
            if(QLLTB_tfma.getText().isEmpty() || QLLTB_tfmota.getText().isEmpty() ||
                    QLLTB_tften.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(" ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Loại thiết  đã có trong hệ thống");
                    alert.showAndWait();
                }else {
                    prestt = conn.prepareStatement(sql);
                    prestt.setString(1, QLLTB_tfma.getText());
                    prestt.setString(2,QLLTB_tften.getText());
                    prestt.setString(3, QLLTB_tfmota.getText());
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm thành công");
                    alert.showAndWait();
                    display_listLoaiTb();
                    QLLTB_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }
    @FXML
    private Button QLLTB_clearbtn;
    public void QLLTB_clearbtn(){
        QLLTB_tfma.setText("");
        QLLTB_tften.setText("");
        QLLTB_tfmota.setText("");
    }

    @FXML
    private TableView<LoaiTb> QLLTB_table;
    @FXML
    private TableColumn<LoaiTb, String> QLLTB_colma;

    @FXML
    private TableColumn<LoaiTb, String> QLLTB_colmota;

    @FXML
    private TableColumn<LoaiTb, String> QLLTB_colten;
    public ObservableList<LoaiTb> select_listLoaiTB(){
        String sql = "SELECT * FROM loaithietbi";
        conn = dataBase.connectDB();
        ObservableList<LoaiTb> listLoaiTB = FXCollections.observableArrayList();
        try {
            LoaiTb loai;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
                loai = new LoaiTb(rs.getString("maloaitb"),rs.getString("tenloaitb")
                        ,rs.getString("mota"));
                listLoaiTB.add(loai);
            }

        }catch (Exception e){e.printStackTrace();}
        return listLoaiTB;
    }

    private ObservableList<LoaiTb> listloaiTB ;
    public void display_listLoaiTb(){
        listloaiTB = select_listLoaiTB();
        QLLTB_colma.setCellValueFactory(new PropertyValueFactory<>("maloaitb"));
        QLLTB_colten.setCellValueFactory(new PropertyValueFactory<>("tenloaitb"));
        QLLTB_colmota.setCellValueFactory(new PropertyValueFactory<>("mota"));
        QLLTB_table.setItems(listloaiTB);
    }

    public void LoaiTBSelect(){
        LoaiTb loaiTb = QLLTB_table.getSelectionModel().getSelectedItem();
        int num = QLLTB_table.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLLTB_tfma.setText(String.valueOf(loaiTb.getMaloaitb()));
        QLLTB_tften.setText(String.valueOf(loaiTb.getTenloaitb()));
        QLLTB_tfmota.setText(String.valueOf(loaiTb.getMota()));
    }


    @FXML
    private Button QLLTB_deletebtn;
    public void QLLTB_deletebtn(){
        String sql = "DELETE FROM loaithietbi WHERE maloaitb = '"+QLLTB_tfma.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            if(QLLTB_tfma.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn loại thiết bị cần xóa ");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn xóa loai TB này ra khỏi hệ thống ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prestt = conn.prepareStatement(sql);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText(" xóa thành công ");
                    alert.showAndWait();
                    display_listLoaiTb();
                    QLLTB_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private TextField QLLTB_tfma;

    @FXML
    private TextArea QLLTB_tfmota;

    @FXML
    private TextField QLLTB_tften;

    @FXML
    private Button QLLTB_updatebtn;
    public void QLLTB_updatebtn(){
        conn = dataBase.connectDB();
        Alert alert;
        String sql = "UPDATE loaithietbi SET tenloaitb = '"
                +QLLTB_tften.getText()+"', mota = '"
                +QLLTB_tfmota.getText()+"' WHERE maloaitb = '"+QLLTB_tfma.getText()+"'";
        try{
            if(QLLTB_tfma.getText().isEmpty() || QLLTB_tfmota.getText().isEmpty() ||
                    QLLTB_tften.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
                display_listLoaiTb();
            }
        }catch (Exception e){e.printStackTrace();}
    }
    //loattb

    //quanlyvitri
    @FXML
    private AnchorPane QLVT_form;
    @FXML
    private Button QLVT_addbtn;
    public void QLVT_addbtn(){
        String sql = "INSERT INTO vitri (mavt,tenvt,mota,manhanvien)"
                + "VALUES(?,?,?,?)";
        conn = dataBase.connectDB();
        String sqlCheck = "SELECT * FROM vitri WHERE mavt = '"+QLVT_tfmavt.getText()+"'";
        try{
            Alert alert;
            prestt = conn.prepareStatement(sqlCheck);
            rs = prestt.executeQuery();
            if(QLVT_tfmavt.getText().isEmpty() || QLVT_tfmota.getText().isEmpty() ||
                    QLVT_tftenVT.getText().isEmpty() || QLVT_manv.getSelectionModel().getSelectedItem() == null ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(" ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Vị trí này đã có trong hệ thống");
                    alert.showAndWait();
                }else {
                    prestt = conn.prepareStatement(sql);
                    prestt.setString(1, QLVT_tfmavt.getText());
                    prestt.setString(2,QLVT_tftenVT.getText());
                    prestt.setString(3, QLVT_tfmota.getText());
                    prestt.setString(4,(String) QLVT_manv.getSelectionModel().getSelectedItem());
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm thành công");
                    alert.showAndWait();
                    display_listVT();
                    QLVT_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }
    @FXML
    private Button QLVT_clearbtn;
    public void QLVT_clearbtn(){
        QLVT_tfmavt.setText("");
        QLVT_tftenVT.setText("");
        QLVT_tfmota.setText("");
    }

    @FXML
    private TableView<vitri> QLVT_tableview;

    @FXML
    private TableColumn<vitri, String> QLVT_colmaxvt;

    @FXML
    private TableColumn<vitri, String> QLVT_colmota;

    @FXML
    private TableColumn<vitri, String> QLVT_colnhanvien;

    @FXML
    private TableColumn<vitri, String> QLVT_colten;
    public ObservableList<vitri> select_vitri(){
        String sql = "SELECT * FROM vitri";
        conn = dataBase.connectDB();
        ObservableList<vitri> Listvitri = FXCollections.observableArrayList();
        try {
            vitri VT;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
               VT = new vitri(rs.getString("mavt"),rs.getString("tenvt")
                        ,rs.getString("mota"),rs.getString("manhanvien"));
                Listvitri.add(VT);
            }

        }catch (Exception e){e.printStackTrace();}
        return Listvitri;
    }

    private ObservableList<vitri> ListVT ;
    public void display_listVT(){
        ListVT = select_vitri();
        QLVT_colmaxvt.setCellValueFactory(new PropertyValueFactory<>("mavt"));
        QLVT_colten.setCellValueFactory(new PropertyValueFactory<>("tenvt"));
        QLVT_colmota.setCellValueFactory(new PropertyValueFactory<>("mota"));
        QLVT_colnhanvien.setCellValueFactory(new PropertyValueFactory<>("manhanvien"));
        QLVT_tableview.setItems(ListVT);
    }

    public void vitriTableselect(){
        vitri vitri = QLVT_tableview.getSelectionModel().getSelectedItem();
        int num = QLVT_tableview.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLVT_tfmavt.setText(String.valueOf(vitri.getMavt()));
        QLVT_tftenVT.setText(String.valueOf(vitri.getTenvt()));
        QLVT_tfmota.setText(String.valueOf(vitri.getMota()));
    }

    @FXML
    private Button QLVT_deletebtn;
    public void QLVT_deletebtn(){
        String sql = "DELETE FROM vitri WHERE mavt = '"+QLVT_tfmavt.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            if(QLVT_tfmavt.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn vị trí cần xóa khỏi hệ thống");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn xóa vị trí này ra khỏi hệ thống ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prestt = conn.prepareStatement(sql);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText(" xóa thành công ");
                    alert.showAndWait();
                    display_listVT();
                    QLVT_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }


    @FXML
    private ComboBox<?> QLVT_manv;
    public void QLVT_combonv(){
        String sql = "SELECT manhanvien FROM nhanvien ";
        conn = dataBase.connectDB();
        try{
            ObservableList listNhanvien = FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
               listNhanvien.add(rs.getString("manhanvien"));
            }
            QLVT_manv.setItems(listNhanvien);
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private TextField QLVT_tfmavt;

    @FXML
    private TextArea QLVT_tfmota;

    @FXML
    private TextField QLVT_tftenVT;

    @FXML
    private Button QLVT_updatebtn;
    public void QLVT_updatebtn(){
        conn = dataBase.connectDB();
        Alert alert;
        String sql = "UPDATE vitri SET tenvt = '"
                +QLVT_tftenVT.getText()+"', mota = '"
                +QLVT_tfmota.getText()+"', manhanvien = '"+QLVT_manv.getSelectionModel().getSelectedItem()+"' WHERE mavt = '"+QLVT_tfmavt.getText()+"'";
        try{
            if(QLVT_tfmavt.getText().isEmpty() || QLVT_tfmota.getText().isEmpty() ||
                    QLVT_tftenVT.getText().isEmpty() || QLVT_manv.getSelectionModel().getSelectedItem() == null ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
                display_listVT();
                QLVT_clearbtn();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    //quanlyvitri

    // Quanlynguoimuon
    @FXML
    private Button QLNM_addbtn;
    public void QLNM_addbtn(){
        String sql = "INSERT INTO nguoimuon (manguoimuon,tennguoimuon,khoavien,chucvu,email)"
                + "VALUES(?,?,?,?,?)";
        conn = dataBase.connectDB();
        String sqlCheck = "SELECT * FROM nguoimuon WHERE manguoimuon = '"+QLNM_tfmanm.getText()+"'";
        try{
            Alert alert;
            prestt = conn.prepareStatement(sqlCheck);
            rs = prestt.executeQuery();
            if(QLNM_tfmanm.getText().isEmpty() || QLNM_tfhoten.getText().isEmpty() ||
                    QLNM_tfchucvu.getText().isEmpty() ||  QLNM_tfkhoavien.getText().isEmpty() ||  QLNM_tfemail.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(" ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã có người mượn này trong hệ thống");
                    alert.showAndWait();
                }else {
                    prestt = conn.prepareStatement(sql);
                    prestt.setString(1, QLNM_tfmanm.getText());
                    prestt.setString(2,QLNM_tfhoten.getText());
                    prestt.setString(3,QLNM_tfkhoavien.getText());
                    prestt.setString(4, QLNM_tfchucvu.getText());
                    prestt.setString(5,QLNM_tfemail.getText());
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm thành công");
                    alert.showAndWait();
                    display_listNM();
                    QLNM_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private Button QLNM_clearbtn;
    public void QLNM_clearbtn(){
        QLNM_tfemail.setText("");
        QLNM_tfhoten.setText("");
        QLNM_tfchucvu.setText("");
        QLNM_tfkhoavien.setText("");
        QLNM_tfmanm.setText("");
    }

    @FXML
    private TableColumn<?, ?> QLNM_colChucvu;

    @FXML
    private TableColumn<?, ?> QLNM_colEmail;

    @FXML
    private TableColumn<?, ?> QLNM_colHoten;

    @FXML
    private TableColumn<?, ?> QLNM_colKhoavien;

    @FXML
    private TableColumn<?, ?> QLNM_colManm;
    public ObservableList<Nguoimuon> select_NM(){
        String sql = "SELECT * FROM nguoimuon";
        conn = dataBase.connectDB();
        ObservableList<Nguoimuon> ListNguoimuon = FXCollections.observableArrayList();
        try {
            Nguoimuon NM;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
                NM = new Nguoimuon(rs.getString("manguoimuon"),rs.getString("tennguoimuon")
                        ,rs.getString("khoavien"),rs.getString("chucvu"),rs.getString("email"));
                ListNguoimuon.add(NM);
            }

        }catch (Exception e){e.printStackTrace();}
        return ListNguoimuon;
    }

    private ObservableList<Nguoimuon> ListNM ;
    public void display_listNM(){
        ListNM = select_NM();
        QLNM_colManm.setCellValueFactory(new PropertyValueFactory<>("manguoimuon"));
        QLNM_colHoten.setCellValueFactory(new PropertyValueFactory<>("tennguoimuon"));
        QLNM_colKhoavien.setCellValueFactory(new PropertyValueFactory<>("khoavien"));
        QLNM_colChucvu.setCellValueFactory(new PropertyValueFactory<>("chucvu"));
        QLNM_colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        QLNM_tableview.setItems(ListNM);
    }

    public void NMTableselect(){
        Nguoimuon NM  = QLNM_tableview.getSelectionModel().getSelectedItem();
        int num = QLNM_tableview.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLNM_tfmanm.setText(String.valueOf(NM.getManguoimuon()));
        QLNM_tfhoten.setText(String.valueOf(NM.getTennguoimuon()));
        QLNM_tfkhoavien.setText(String.valueOf(NM.getKhoavien()));
        QLNM_tfchucvu.setText(String.valueOf(NM.getChucvu()));
        QLNM_tfemail.setText(String.valueOf(NM.getEmail()));
    }
    @FXML
    private Button QLNM_deletebtn;
    public void QLNM_deletebtn(){
        String sql = "DELETE FROM nguoimuon WHERE manguoimuon = '"+QLNM_tfmanm.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            if(QLNM_tfmanm.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn người mượn cần xóa khỏi hệ thống");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn xóa phiếu mượn này ra khỏi hệ thống ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prestt = conn.prepareStatement(sql);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText(" xóa thành công ");
                    alert.showAndWait();
                    display_listNM();
                   QLNM_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private AnchorPane QLNM_form;

    @FXML
    private TableView<Nguoimuon> QLNM_tableview;

    @FXML
    private TextField QLNM_tfchucvu;

    @FXML
    private TextField QLNM_tfemail;

    @FXML
    private TextField QLNM_tfhoten;

    @FXML
    private TextField QLNM_tfkhoavien;

    @FXML
    private TextField QLNM_tfmanm;

    @FXML
    private Button QLNM_updatebtn;
    public void QLNM_updatebtn(){
        conn = dataBase.connectDB();
        Alert alert;
        String sql = "UPDATE nguoimuon SET tennguoimuon = '"
                +QLNM_tfhoten.getText()+"', khoavien = '"
                +QLNM_tfkhoavien.getText()+"', chucvu = '"+QLNM_tfchucvu.getText()+"', email = '"+QLNM_tfemail.getText()+
        "' WHERE manguoimuon= '"+QLNM_tfmanm.getText()+"'";
        try{
            if(QLNM_tfmanm.getText().isEmpty() || QLNM_tfchucvu.getText().isEmpty() ||
                    QLNM_tfhoten.getText().isEmpty() || QLNM_tfkhoavien.getText().isEmpty() || QLNM_tfemail.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
                display_listNM();
                QLNM_clearbtn();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    //Quản lí ngươi mượn

    // Quản lí phiếu mượn
    @FXML
    private Button QLPM_addbtn;
    public void QLPM_addbtn(){
        String sql = "INSERT INTO phieumuon (maphieumuon,ngaylap,ngaytra,manguoimuon,manhanvien)"
                + "VALUES(?,?,?,?,?)";
        conn = dataBase.connectDB();
        String sqlCheck = "SELECT * FROM phieumuon WHERE maphieumuon = '"+QLPM_tfmapm.getText()+"'";
        try{
            Alert alert;
            prestt = conn.prepareStatement(sqlCheck);
            rs = prestt.executeQuery();
            if(QLPM_tfmapm.getText().isEmpty() || QLPM_tfngaytra.getText().isEmpty() ||
                    QLPM_tfmanm.getText().isEmpty() || QLPM_tfnhanvien.getSelectionModel().getSelectedItem() == null ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(" ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã có phiếu với mã này");
                    alert.showAndWait();
                }else {
                    prestt = conn.prepareStatement(sql);
                    prestt.setString(1, QLPM_tfmapm.getText());
                    Date date = new Date();
                    Date sqlDate = new Date(date.getTime());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(sqlDate);
                    prestt.setString(2,formattedDate);
                    prestt.setString(3,QLPM_tfngaytra.getText());
                    prestt.setString(4, QLPM_tfmanm.getText());
                    prestt.setString(5,(String) QLPM_tfnhanvien.getSelectionModel().getSelectedItem());
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm thành công");
                    alert.showAndWait();
                    display_listPM();
                    QLPM_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private Button QLPM_cleabtn;
    public void QLPM_clearbtn(){
        QLPM_tfmapm.setText("");
        QLPM_tfngaytra.setText("");
        QLPM_tfmanm.setText("");
        QLPM_tfnhanvien.setValue(null);
    }

    @FXML
    private TableView<Phieumuon> QLPM_tableview;

    @FXML
    private TableColumn<Phieumuon, String> QLPM_colmanm;

    @FXML
    private TableColumn<Phieumuon, String> QLPM_colmapm;

    @FXML
    private TableColumn<Phieumuon, String> QLPM_colngaymuon;

    @FXML
    private TableColumn<Phieumuon, String> QLPM_colngaytra;

    @FXML
    private TableColumn<Phieumuon, String> QLPM_colnhsnvien;
    public ObservableList<Phieumuon> select_PM(){
        String sql = "SELECT * FROM phieumuon";
        conn = dataBase.connectDB();
        ObservableList<Phieumuon> ListPhieumuon = FXCollections.observableArrayList();
        try {
            Phieumuon phieumuon;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
                phieumuon = new Phieumuon(rs.getString("maphieumuon"),rs.getDate("ngaylap")
                        ,rs.getDate("ngaytra"),rs.getString("manhanvien"),rs.getString("manguoimuon"));
                ListPhieumuon.add(phieumuon);
            }

        }catch (Exception e){e.printStackTrace();}
        return ListPhieumuon;
    }

    private ObservableList<Phieumuon> ListPM ;
    public void display_listPM(){
        ListPM = select_PM();
        QLPM_colmapm.setCellValueFactory(new PropertyValueFactory<>("maphieumuon"));
        QLPM_colngaymuon.setCellValueFactory(new PropertyValueFactory<>("ngaylap"));
        QLPM_colngaytra.setCellValueFactory(new PropertyValueFactory<>("ngaytra"));
        QLPM_colnhsnvien.setCellValueFactory(new PropertyValueFactory<>("manhanvien"));
        QLPM_colmanm.setCellValueFactory(new PropertyValueFactory<>("manguoimuon"));
        QLPM_tableview.setItems(ListPM);
    }

    public void PMTableselect(){
       Phieumuon PM  = QLPM_tableview.getSelectionModel().getSelectedItem();
        int num = QLPM_tableview.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLPM_tfmapm.setText(String.valueOf(PM.getMaphieumuon()));
        QLPM_tfmanm.setText(String.valueOf(PM.getManguoimuon()));
        QLPM_tfngaytra.setText(String.valueOf(PM.getNgaytra()));
    }

    @FXML
    private Button QLPM_deletebtn;
    public void QLPM_deletebtn(){
        String sql = "DELETE FROM phieumuon WHERE maphieumuon = '"+QLPM_tfmapm.getText()+"'";
        conn = dataBase.connectDB();
        try{
            Alert alert;
            if(QLPM_tfmapm.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Hãy chọn phiếu mượn cần xóa khỏi hệ thống");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText(" Bạn có chắc chắn muốn xóa phiếu mượn này ra khỏi hệ thống ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prestt = conn.prepareStatement(sql);
                    prestt.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText(" xóa thành công ");
                    alert.showAndWait();
                    display_listPM();
                   QLPM_clearbtn();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }



    @FXML
    private AnchorPane QLPM_form;

    @FXML
    private TextField QLPM_tfmanm;

    @FXML
    private TextField QLPM_tfmapm;

    @FXML
    private TextField QLPM_tfngaytra;

    @FXML
    private ComboBox<?> QLPM_tfnhanvien;
    public void QLPM_combonv(){
        String sql = "SELECT manhanvien FROM nhanvien ";
        conn = dataBase.connectDB();
        try{
            ObservableList listNhanvien = FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
                listNhanvien.add(rs.getString("manhanvien"));
            }
            QLPM_tfnhanvien.setItems(listNhanvien);
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private Button QLPM_updatebtn;
    public void QLPM_updatebtn(){
        conn = dataBase.connectDB();
        Alert alert;
        String sql = "UPDATE phieumuon SET ngaytra = '"
                +QLPM_tfngaytra.getText()+"', manguoimuon = '"
                +QLPM_tfmanm.getText()+"', manhanvien = '"+QLPM_tfnhanvien.getSelectionModel().getSelectedItem()+"' WHERE maphieumuon = '"+QLPM_tfmapm.getText()+"'";
        try{
            if(QLPM_tfmapm.getText().isEmpty() || QLPM_tfmanm.getText().isEmpty() ||
                    QLPM_tfngaytra.getText().isEmpty() || QLPM_tfnhanvien.getSelectionModel().getSelectedItem() == null ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }
            else {
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
                display_listPM();
                QLPM_clearbtn();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    // QUuản lí phiếu mượn

    //QLmuon
    @FXML
    private TableView<thietbi> QLM_tableview;
    @FXML
    private TableColumn<thietbi, String> QLM_colloaiTB;

    @FXML
    private TableColumn<thietbi, String> QLM_colmatb;

    @FXML
    private TableColumn<thietbi, String> QLM_coltentb;

    @FXML
    private TableColumn<thietbi, String> QLM_coltinhtrang;

    public ObservableList<thietbi> select_TBcothemuon(){
        String sql = "SELECT * FROM thietbi";
        String sqlCheck = " SELECT * FROM muon WHERE matb = ? AND trangthaiTB = 'Đang mượn'";
        conn = dataBase.connectDB();
        ObservableList<thietbi> ListTBcothemuon = FXCollections.observableArrayList();
        try {
            thietbi thietbicothemuon;
            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while (rs.next()){
             prestt = conn.prepareStatement((sqlCheck));
             prestt.setString(1,rs.getString("matb"));
             rs1 = prestt.executeQuery();
             if(rs1.next()){
             }else {
              thietbicothemuon =   new thietbi(rs.getString("matb"),rs.getString("tentb")
                         ,rs.getString("namsanxuat"),rs.getString("mota"),rs.getString("tinhtrang"),rs.getString("maloaitb"),
                         rs.getString("mansx"),rs.getString("mavt"),rs.getString("maphieunhap"));
              ListTBcothemuon.add(thietbicothemuon);
             }
            }
        }catch (Exception e){e.printStackTrace();}
        return ListTBcothemuon;
    }

    private ObservableList<thietbi> ListTBcothmuon ;
    public void display_listTBcothemuon(){
        ListTBcothmuon = select_TBcothemuon();
        QLM_colmatb.setCellValueFactory(new PropertyValueFactory<>("matb"));
        QLM_coltentb.setCellValueFactory(new PropertyValueFactory<>("tentb"));
        QLM_colloaiTB.setCellValueFactory(new PropertyValueFactory<>("maloaitb"));
        QLM_coltinhtrang.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
        QLM_tableview.setItems(ListTBcothmuon);
    }

    public void TBcothemuonTableselect(){
        thietbi TB  = QLM_tableview.getSelectionModel().getSelectedItem();
        int num = QLM_tableview.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLM_tfmatbmuon.setText(String.valueOf(TB.getMatb()));

    }

    @FXML
    private ComboBox<?> QLM_comboPM;
    public void QLM_comboPM(){
        String sql = "SELECT maphieumuon FROM phieumuon ";
        try{
            ObservableList listData = FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
                listData.add(rs.getString("maphieumuon"));
            }
            QLM_comboPM.setItems(listData);
        }catch (Exception e){e.printStackTrace();}
    }
    @FXML
    private Button QLM_muonbtn;
    public void QLM_muonbtn(){
        String sql1 = " INSERT INTO muon (maphieumuon, matb, ngaytra, trangthaiTB, tentb) "+"VALUES(?,?,?,?,?)";
        String sql2 = "SELECT * FROM thietbi WHERE matb = '"+QLM_tfmatbmuon.getText()+"'";
        String sql3 = "SELECT * FROM phieumuon WHERE maphieumuon = '"+QLM_comboPM.getSelectionModel().getSelectedItem()+"'";
        conn =dataBase.connectDB();
        try{
            Alert alert;

            if(QLM_tfmatbmuon.getText().isEmpty() || QLM_comboPM.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Hoàn thiên thông tin");
                alert.showAndWait();
            }else{
                prestt = conn.prepareStatement(sql2);
                rs = prestt.executeQuery();
                prestt =conn.prepareStatement(sql3);
                rs1 = prestt.executeQuery();
                prestt = conn.prepareStatement(sql1);
                prestt.setString(1,(String) QLM_comboPM.getSelectionModel().getSelectedItem());
                prestt.setString(2,QLM_tfmatbmuon.getText());
                if(rs1.next()){
                prestt.setString(3, rs1.getString("ngaytra"));}
                prestt.setString(4,"Đang mượn");
                if(rs.next()){
                prestt.setString(5,rs.getString("tentb"));}
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" INFOR");
                alert.setHeaderText(null);
                alert.setContentText("Mượn thành công");
                alert.showAndWait();
                display_listTBcothemuon();
                display_listTBtrongphieu();
            }

        }catch (Exception e){e.printStackTrace();}
    }



    @FXML
    private TextField QLM_tfmatbmuon;

    //QLmuon

    //Quanlitra
    @FXML
    private TableColumn<muon, String> QLT_colTrangthai;

    @FXML
    private TableColumn<muon, String> QLT_colngaytra;

    @FXML
    private TableColumn<muon, String> QLT_colmatb;

    @FXML
    private TableColumn<muon, String> QLT_coltentb;
    @FXML
    private TableView<muon> QLT_tableview;

    public ObservableList<muon> select_TBtrongPM(){
        String sql = "SELECT * FROM muon WHERE maphieumuon = ?";
        conn = dataBase.connectDB();
        ObservableList<muon> ListTBtrongphieu = FXCollections.observableArrayList();
        try {
            muon Tbtrongphieu;
            prestt = conn.prepareStatement(sql);
            prestt.setString(1,(String) QLT_combomaPM.getSelectionModel().getSelectedItem());
            rs = prestt.executeQuery();
            while (rs.next()){
                Tbtrongphieu = new muon(rs.getString("maphieumuon"),rs.getString("matb"),rs.getDate("ngaytra"),rs.getString("trangthaiTB"),rs.getString("tentb"));
                ListTBtrongphieu.add(Tbtrongphieu);
            }

        }catch (Exception e){e.printStackTrace();}
        return ListTBtrongphieu;
    }

    private ObservableList<muon> ListTBTrongphieu ;
    public void display_listTBtrongphieu(){
        ListTBTrongphieu = select_TBtrongPM();
        QLT_colmatb.setCellValueFactory(new PropertyValueFactory<>("matb"));
        QLT_coltentb.setCellValueFactory(new PropertyValueFactory<>("tentb"));
        QLT_colngaytra.setCellValueFactory(new PropertyValueFactory<>("ngaytra"));
        QLT_colTrangthai.setCellValueFactory(new PropertyValueFactory<>("tinhtrangTB"));
        QLT_tableview.setItems(ListTBTrongphieu);
    }

    public void TBTRONGPHIEUTableselect(){
        muon TB  = QLT_tableview.getSelectionModel().getSelectedItem();
        int num = QLT_tableview.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){return;}
        QLT_tfmatb.setText(String.valueOf(TB.getMatb()));

    }

    @FXML
    private ComboBox<?> QLT_combomaPM;
    public void QLT_comboPM(){
        String sql = "SELECT maphieumuon FROM phieumuon ";
        try{
            ObservableList listData = FXCollections.observableArrayList();

            prestt = conn.prepareStatement(sql);
            rs = prestt.executeQuery();
            while(rs.next()){
                listData.add(rs.getString("maphieumuon"));
            }
            QLT_combomaPM.setItems(listData);
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private TextField QLT_tfmatb;

    @FXML
    private Button QLT_trabtn;
    public void QLT_trabtn(){
        String sql = "UPDATE muon SET trangthaiTB = 'Đã trả' WHERE matb = '"+QLT_tfmatb.getText()+"' AND maphieumuon = '"
                +QLT_combomaPM.getSelectionModel().getSelectedItem()+"'";
        try{
            Alert alert;
            conn = dataBase.connectDB();
            if(QLT_tfmatb.getText().isEmpty() || QLT_combomaPM.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Chọn phiếu mượn và thiết bị cần trả");
                alert.showAndWait();
            }else {
                prestt = conn.prepareStatement(sql);
                prestt.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFOR");
                alert.setHeaderText(null);
                alert.setContentText("Đã trả thành công");
                alert.showAndWait();
                display_listTBcothemuon();
                display_listTBtrongphieu();
            }

        }catch (Exception e){e.printStackTrace(); }
    }

    //Quanlitra


    public void initialize(URL location, ResourceBundle resources) {
        displayUser();
        displaytaikhoan();
        display_listTK();
        QLTK_ListChucvu();
        display_listNV();
        QLNCCSX_Loai();
        display_listLoaiTb();
        QLVT_combonv();
        display_listVT();
        QLPN_comboncc();
        display_listPN();
        QLPN_comboNSX();
        QLTB_NhapTB_tfkieutimkiem();
        QLTB_NhapTB_maPN();
        QLTB_NhapTB_maVT();
        QLTB_NhapTB_comboMaLTB();
        QLTB_NhapTB_tinhtrangTB();
        display_listTB();
        QLPM_combonv();
        display_listPM();
        display_listNM();
        QLM_comboPM();
        QLT_comboPM();
        display_listTBcothemuon();
        display_listTBtrongphieu();
    }
}
