package P3_G6_Miniproject_Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class InstrumentPickerWindow extends BorderPane {

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
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setMinSize(width, height);

        this.setStyle("-fx-background-color: #4a4a4a;");

        img = new ImageView("images/drummer1.png");
        img.setFitWidth(width/2);
        img.setFitHeight(width/2);

        closeButton = new Button("close");
        chooseButton = new Button("choose");
        leftButton = new Button("left");
        rightButton = new Button("right");

        this.setCenter(img);
        this.setTop(closeButton);
        this.setBottom(chooseButton);
        this.setLeft(leftButton);
        this.setRight(rightButton);
        setMargin(img, new Insets(10));
        setAlignment(leftButton, Pos.CENTER);
        setAlignment(rightButton, Pos.CENTER);
        setAlignment(chooseButton,Pos.BOTTOM_RIGHT);
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
