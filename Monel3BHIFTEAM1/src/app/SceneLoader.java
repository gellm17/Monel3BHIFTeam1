package app;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class SceneLoader {

    private Stage primStage;
    public void showScene(String name) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource(name+".fxml"));
            BorderPane root = fxml.load();
            Scene scene = new Scene(root);
            primStage.setScene(scene);
            Screen screen = Screen.getPrimary();

            //Maximized
            Rectangle2D bounds = screen.getVisualBounds();
            primStage.setX(bounds.getMinX());
            primStage.setY(bounds.getMinY());
            primStage.setWidth(bounds.getWidth());
            primStage.setHeight(bounds.getHeight());
            primStage.show();

            //SceneLoader loader = fxml.getController();
            //loader.setPrimaryStage(primStage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primStage = primaryStage;
    }
}