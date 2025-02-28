package awebo;
import java.io.IOException;

import awebo.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Awebo awebo = new Awebo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // inject the awebo instance
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417); // No resize
            fxmlLoader.<MainWindow>getController().setAwebo(awebo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
