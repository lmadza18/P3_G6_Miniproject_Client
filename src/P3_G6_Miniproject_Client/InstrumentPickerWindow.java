package P3_G6_Miniproject_Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class InstrumentPickerWindow extends Pane {
    Pane background = new Pane();
    BorderPane window = new BorderPane();

    double width = Main.root.getWidth() / 8 * 4;
    double height = Main.root.getHeight() / 8 * 4;
    double x = Main.root.getWidth() / 2 - width / 2;
    double y = Main.root.getHeight() / 2 - height / 2;
    Button closeButton;
    Button chooseButton;
    Button leftButton;
    Button rightButton;

    int switchIndex = 0;

    // Add all character images to an array
    ArrayList<ImageView> images = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();

    public InstrumentPickerWindow() {

        background.setMinSize(Main.root.getWidth(), Main.root.getHeight());
        background.setStyle("-fx-background-color: #111111; -fx-opacity: 0.7;");

        this.getChildren().addAll(background, window);

        window.setTranslateX(x);
        window.setTranslateY(y);
        window.setMinSize(width, height);

        window.setStyle("-fx-background-color: #4a4a4a;");


        // Resizing all character images
        for (int i=0; i < StageSpot.bandPlayers.length; i++) {
            if (!StageSpot.bandPlayers[i].taken) {
                images.add(StageSpot.bandPlayers[i].getImg());
                id.add(StageSpot.bandPlayers[i].getPlayerId());
            }
        }

        for (ImageView img: images){
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
        leftImg.setFitHeight(buttonSize*2);
        rightImg.setFitWidth(buttonSize);
        rightImg.setFitHeight(buttonSize*2);

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

        window.setCenter(images.get(switchIndex));
        window.setTop(closeButton);
        window.setBottom(chooseButton);
        window.setLeft(leftButton);
        window.setRight(rightButton);
        BorderPane.setMargin(images.get(switchIndex), new Insets(10));
        BorderPane.setAlignment(leftButton, Pos.CENTER);
        BorderPane.setAlignment(rightButton, Pos.CENTER);
        BorderPane.setAlignment(chooseButton, Pos.BOTTOM_RIGHT);
        thisSpotKillsMyMojo();
        //chooseSpot();
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
            if (switchIndex == images.size() - 1) {
                switchIndex = 0;
            } else {
                switchIndex++;
            }
            window.setCenter(images.get(switchIndex));

        });
    }

    void switchLeft() {
        leftButton.setOnAction(actionEvent -> {
            if (switchIndex == 0) {
                switchIndex = images.size() - 1;
            } else {
                switchIndex--;
            }
            window.setCenter(images.get(switchIndex));
        });
    }


    /*void chooseSpot() {
        chooseButton.setOnAction(actionEvent -> {
            System.out.println("Working!");
            //Main.root.getChildren().addAll(StageSpot.bandPlayers[switchIndex], StageSpot.bandPlayers[switchIndex].img);
            Main.root.getChildren().remove(this);

        });
    }*/
}
