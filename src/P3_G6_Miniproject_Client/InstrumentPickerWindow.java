package P3_G6_Miniproject_Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class InstrumentPickerWindow extends BorderPane {

    double width = Main.root.getWidth() / 8 * 4;
    double height = Main.root.getHeight() / 8 * 4;
    double x = Main.root.getWidth() / 2 - width / 2;
    double y = Main.root.getHeight() / 2 - height / 2;
    Button closeButton;
    Button chooseButton;
    Button leftButton;
    Button rightButton;

    int switchIndex = 0;

    // Add all images to an array
    ImageView[] images = new ImageView[]{
            new ImageView("images/drummer1.png"),
            new ImageView("images/guitarist.png"),
            new ImageView("images/bassist.jpg")
    };

    public InstrumentPickerWindow() {
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setMinSize(width, height);

        this.setStyle("-fx-background-color: #4a4a4a;");

        // Resizing all images
        for (int i = 0; i < images.length; i++) {
            images[i].setFitWidth(width / 2);
            images[i].setFitHeight(width / 2);
        }

        closeButton = new Button("close");
        chooseButton = new Button("choose");
        leftButton = new Button("left");
        rightButton = new Button("right");

        this.setCenter(images[switchIndex]);
        this.setTop(closeButton);
        this.setBottom(chooseButton);
        this.setLeft(leftButton);
        this.setRight(rightButton);
        setMargin(images[switchIndex], new Insets(10));
        setAlignment(leftButton, Pos.CENTER);
        setAlignment(rightButton, Pos.CENTER);
        setAlignment(chooseButton, Pos.BOTTOM_RIGHT);
        thisSpotKillsMyMojo();
        chooseSpot();
        switchRight();
        switchLeft();
    }

    void thisSpotKillsMyMojo() {
        closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(this);
        });
    }

    void switchRight() {
        rightButton.setOnAction(actionEvent -> {
            if (switchIndex == images.length - 1) {
                switchIndex = 0;
            } else {
                switchIndex++;
            }
            this.setCenter(images[switchIndex]);
        });
    }

    void switchLeft() {
        leftButton.setOnAction(actionEvent -> {
            if (switchIndex == 0) {
                switchIndex = images.length - 1;
            } else {
                switchIndex--;
            }
            this.setCenter(images[switchIndex]);
        });
    }


    void chooseSpot() {
        chooseButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(this);
        });
    }
}
