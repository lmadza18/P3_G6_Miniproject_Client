package P3_G6_Miniproject_Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class InstrumentPickerWindow extends BorderPane {

    Button closeButton;
    Button chooseButton;
    Button leftButton;
    Button rightButton;

    public InstrumentPickerWindow(){
        this.setTranslateX(Main.root.getWidth() / 8);
        this.setTranslateY(Main.root.getHeight() / 8);
        this.setMinSize(Main.root.getWidth() / 8 * 6, Main.root.getHeight() / 8 * 6);

        this.setStyle("-fx-background-color: #4a4a4a;");

        ImageView img = new ImageView("images/drummer1.png");

        closeButton = new Button("close");
        chooseButton = new Button("choose");
        leftButton = new Button("left");
        rightButton = new Button("right");

        this.setCenter(img);
        this.setTop(closeButton);
        this.setBottom(chooseButton);
        this.setLeft(leftButton);
        this.setRight(rightButton);
        setMargin(img, new Insets(50));
        setAlignment(leftButton, Pos.CENTER);
        setAlignment(rightButton, Pos.CENTER);
        setAlignment(chooseButton,Pos.BOTTOM_RIGHT);
        buttonPress();


    }

    void buttonPress() {
        closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(this);

        });
        chooseButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(this);

        });
    }
}
