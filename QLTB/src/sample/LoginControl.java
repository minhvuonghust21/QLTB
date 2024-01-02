package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.entity.nguoisudung;

import java.sql.*;

public class LoginControl {
    @FXML
    private Button btn_close;
    public void close(){
        System.exit(0);

    }

    @FXML
    private Button btn_mini;
    public void minisize(){
        Stage stage = (Stage) login_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private Button login_btndangnhap;
    private PreparedStatement prestt;
    private Connection conn;
    private ResultSet rs;
    private double x = 0;
    private double y = 0;

    public void btn_dangnhap() {
        conn =dataBase.connectDB();
        String stringSQL = " SELECT * FROM nguoisudung WHERE taikhoan = ? AND matkhau =?";

        try {
            prestt = conn.prepareStatement(stringSQL);
            prestt.setString(1,login_username.getText());
            prestt.setString(2,login_password.getText());
            rs = prestt.executeQuery();
            Alert alert;
            if(login_username.getText().isEmpty() || login_password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Hãy điền tài khoản, mật khẩu");
                alert.showAndWait();

            }else{
                if (rs.next()) {
                    taikhoan.taikhoan = rs.getString("taikhoan");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo ");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng nhập thành công");
                    alert.showAndWait();
                    login_btndangnhap.getScene().getWindow().hide();


                    //hiện form dashboard
                    Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                    Stage stage = new Stage ();
                    Scene scene = new Scene (root);
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
                    stage.setScene (scene);
                    stage.show();

                }
                else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error ");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài khoản hoặc mật khẩu không đúng");
                    alert.showAndWait();

                }

            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_username;
    @FXML
    private AnchorPane login_form;

}
