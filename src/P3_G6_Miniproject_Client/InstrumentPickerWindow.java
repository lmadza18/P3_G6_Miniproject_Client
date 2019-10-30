package P3_G6_Miniproject_Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class InstrumentPickerWindow extends Pane {
    Pane background = new Pane(); //Pane for making background unclickable
    BorderPane window = new BorderPane();

    //Setup width and pos for instrumentPickerWindow
    double width = Main.root.getWidth() / 8 * 4;
    double height = Main.root.getHeight() / 8 * 4;
    double x = Main.root.getWidth() / 2 - width / 2;
    double y = Main.root.getHeight() / 2 - height / 2;
    Button closeButton;
    Button chooseButton;
    Button leftButton;
    Button rightButton;

    int switchIndex = 0; // Index for switching characters

    // Add all character images to an array
    ImageView[] imageViews = new ImageView[4];

    public InstrumentPickerWindow() {

        //Set pos and size for background
        background.setMinSize(Main.root.getWidth(), Main.root.getHeight());
        background.setStyle("-fx-background-color: #111111; -fx-opacity: 0.7;");


        //Set pos and size and color for instrumentPickerWindow
        window.setTranslateX(x);
        window.setTranslateY(y);
        window.setMinSize(width, height);
        window.setStyle("-fx-background-color: #4a4a4a;");

        this.getChildren().addAll(background, window);

        // Add images to the Imageviews
        for (int i = 0; i < Main.root.images.length; i++) {
            imageViews[i] = new ImageView(Main.root.images[i]);
        }

        // Resizing all character images
        for (ImageView img : imageViews) {
            img.setFitWidth(width / 2);
            img.setFitHeight(width / 2);
        }


        int buttonSize = (int) Math.round(Main.root.getWidth() / 30);

        // UI button images
        ImageView closeImg = new ImageView("images/close.png");
        ImageView leftImg = new ImageView("images/left.png");
        ImageView rightImg = new ImageView("images/right.png");

        // Resize UI button images
        closeImg.setFitWidth(buttonSize);
        closeImg.setFitHeight(buttonSize);
        leftImg.setFitWidth(buttonSize);
        leftImg.setFitHeight(buttonSize * 2);
        rightImg.setFitWidth(buttonSize);
        rightImg.setFitHeight(buttonSize * 2);

        // UI buttons
        chooseButton = new Button("choose");
        closeButton = new Button();
        leftButton = new Button();
        rightButton = new Button();

        // Add graphics to UI buttons
        closeButton.setGraphic(closeImg);
        leftButton.setGraphic(leftImg);
        rightButton.setGraphic(rightImg);
        closeButton.setStyle("-fx-background-color: transparent");
        leftButton.setStyle("-fx-background-color: transparent");
        rightButton.setStyle("-fx-background-color: transparent");

        window.setCenter(imageViews[switchIndex]);
        window.setTop(closeButton);
        window.setBottom(chooseButton);
        window.setLeft(leftButton);
        window.setRight(rightButton);
        BorderPane.setMargin(imageViews[switchIndex], new Insets(10));
        BorderPane.setAlignment(leftButton, Pos.CENTER);
        BorderPane.setAlignment(rightButton, Pos.CENTER);
        BorderPane.setAlignment(chooseButton, Pos.BOTTOM_RIGHT);


        rightButton.setOnAction(actionEvent -> {
            switchRightListener();
        });
        leftButton.setOnAction(actionEvent -> {
            switchLeftListener();
        });
    }

    //Listeners for switching right and left
    void switchRightListener() {
        switchIndex++;
        if (switchIndex >= imageViews.length) {
            switchIndex = 0;
        }
        window.setCenter(imageViews[switchIndex]);
    }

    void switchLeftListener() {
        switchIndex--;
        if (switchIndex < 0) {
            switchIndex = imageViews.length-1;
        }
        window.setCenter(imageViews[switchIndex]);
    }
}
