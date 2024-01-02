package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double x = 0;
    private double y= 0;

    @Override
    public void start(final Stage Stage) throws Exception{
        final Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
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
                Stage.setX(event.getScreenX() -x);
                Stage.setY(event.getScreenY() -y);
                Stage.setOpacity(.8);// set do mo
            }
        });
        root.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){

                Stage.setOpacity(1);
            }
        });

        Stage.initStyle(StageStyle.TRANSPARENT);


        Stage.setTitle("LOGIN");
        Stage.setScene(new Scene(root, 750, 450));
        Stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
