package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Monel Pro");
        BorderPane root = fxml.load();
        Scene scene = new Scene(root);

        MainWindow_Controller main = fxml.getController();
        main.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        //tab.setGraphic(buildImage("../resources/iconAkt.png");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
/*
    private static ImageView buildImage(String imgPatch) {
        Image i = new Image(imgPatch);
        ImageView imageView = new ImageView();
        //You can set width and height
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        imageView.setImage(i);
        return imageView;
    }
    */

}