package duke.gui;

import duke.BrainrotException;
import duke.GreenFloyd;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private GreenFloyd bot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nerd_emoji_by_mar34568_dihon0i.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/skibidi_toilet_png_meme_by_kylewithem_dhpztml.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the GreenFloyd instance */
    public void setGreenFloyd(GreenFloyd g) {
        bot = g;
    }

    public void showGreeting() {
        String greetingMessage = "Nihao fine shi😛 \nHow can I assist you today?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greetingMessage, botImage)
        );
    }

    /**
     * Creates two dialog boxes for user input and chatbot's reply and then appends them to
     * the dialog container.
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
        } catch (BrainrotException e) {
            // Handle known user errors (e.g., invalid format, duplicate task)
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog("Error: " + e.getMessage(), botImage)
            );
        } catch (Exception e) {
            // Catch all other unexpected errors
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog("Oops, an unknown error occurred...", botImage)
            );
            e.printStackTrace();
        } finally {
            userInput.clear();
        }
    }
}
