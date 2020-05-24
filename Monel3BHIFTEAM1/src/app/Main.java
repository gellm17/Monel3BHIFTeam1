package app;

import controller.ClientList_Controller;
import db.DBManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/ClientList.fxml"));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Monel Pro");
        BorderPane root = fxml.load();
        Scene scene = new Scene(root);

        ClientList_Controller main = fxml.getController();
        main.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}