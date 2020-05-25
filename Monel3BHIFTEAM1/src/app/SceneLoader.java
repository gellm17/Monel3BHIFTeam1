package app;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class SceneLoader {

    private Stage primStage = new Stage();
    public void showScene(String name) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/"+name+".fxml"));
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

            SceneLoader loader = fxml.getController();
            loader.setPrimaryStage(primStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getPrimStage() {
        return primStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primStage = primaryStage;
    }
}