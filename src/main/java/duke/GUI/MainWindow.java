package duke.GUI;

import duke.Green_Floyd;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Green_Floyd bot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nerd_emoji_by_mar34568_dihon0i.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/skibidi_toilet_png_meme_by_kylewithem_dhpztml.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setGreenFloyd(Green_Floyd g) {
        bot = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = bot.handleCommand(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, botImage)
            );
            userInput.clear();
        } catch (Exception e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog("Oops, an unknown error occurred...", botImage)
            );
            userInput.clear();
        }

    }
}
