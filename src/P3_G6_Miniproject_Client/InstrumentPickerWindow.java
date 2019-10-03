package P3_G6_Miniproject_Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class InstrumentPickerWindow extends Pane {

    Pane background = new Pane();
    BorderPane window = new BorderPane();


    double x = Main.root.getWidth() / 8;
    double y = Main.root.getHeight() / 8;
    double width = Main.root.getWidth() / 8 * 6;
    double height = Main.root.getHeight() / 8 * 6;
    Button closeButton;
    Button chooseButton;
    Button leftButton;
    Button rightButton;
    ImageView img;

    public InstrumentPickerWindow(){

        background.setMinSize(Main.root.getWidth(), Main.root.getHeight());
        background.setStyle("-fx-background-color: #111111; -fx-opacity: 0.7;");


        this.getChildren().addAll(background, window);


        window.setTranslateX(x);
        window.setTranslateY(y);
        window.setMinSize(width, height);

        window.setStyle("-fx-background-color: #4a4a4a;");

        img = new ImageView("images/drummer1.png");
        img.setFitWidth(width/2);
        img.setFitHeight(width/2);

        closeButton = new Button("close");
        chooseButton = new Button("choose");
        leftButton = new Button("left");
        rightButton = new Button("right");

        window.setCenter(img);
        window.setTop(closeButton);
        window.setBottom(chooseButton);
        window.setLeft(leftButton);
        window.setRight(rightButton);
        BorderPane.setMargin(img, new Insets(10));
        BorderPane.setAlignment(leftButton, Pos.CENTER);
        BorderPane.setAlignment(rightButton, Pos.CENTER);
        BorderPane.setAlignment(chooseButton, Pos.BOTTOM_RIGHT);
        thisSpotKillsMyMojo();
        chooseSpot();


    }

    void thisSpotKillsMyMojo() {
        closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(this);
        });
    }

    void chooseSpot() {
        chooseButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(this);

        });
    }
}
