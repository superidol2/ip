package awebo.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */

public class DialogBox extends HBox {

    private static final Color USER_COLOR = Color.rgb(0, 0, 0, 0);
    private static final Color AWEBO_COLOR = Color.rgb(0, 0, 0, 0);
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    // Constructor to load FXML and set text and image
    private DialogBox(String text, Image img, Color color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        // Set the background to the dialog box so awebo and user have different colours
        this.setBackground(background);
        this.setPadding(new Insets(10));
        this.setSpacing(10);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAweboAlignment();
        dialog.getStyleClass().add("reply-label");
    }

    private void setAweboAlignment() {
        setAlignment(Pos.TOP_LEFT);
    }

    private void setUserAlignment() {
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Creates a user dialog box, aligning it to the top right.
     *
     * @param text The message to display.
     * @param img  The user's image.
     * @return A {@code DialogBox} for the user's response.
     */

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, USER_COLOR);
        db.setUserAlignment(); // Align user dialog to the top right
        return db;
    }

    /**
     * Creates Awebo's dialog box, aligning it to the left.
     * Adds error styling if the message starts with "Invalid", "Warning", or "Unknown".
     *
     * @param text The message to display.
     * @param img  Awebo's image.
     * @return A styled {@code DialogBox} for Awebo's response.
     */
    public static DialogBox getAweboDialog(String text, Image img) {
        var db = new DialogBox(text, img, AWEBO_COLOR);
        db.flip();
        if (text.startsWith("Invalid") || text.startsWith("Warning") || text.startsWith("Unknown")) {
            db.dialog.getStyleClass().add("wrong-label"); // Apply error styling
        }
        return db;
    }
}
