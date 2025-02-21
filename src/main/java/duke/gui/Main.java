package duke.gui;

import duke.GreenFloyd;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for GreenFloyd using FXML.
 */
public class Main extends Application {
    private GreenFloyd bot = new GreenFloyd("data/task_history.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("TOTALLY Normal Conversation");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/display/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGreenFloyd(bot);
            fxmlLoader.<MainWindow>getController().showGreeting();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

