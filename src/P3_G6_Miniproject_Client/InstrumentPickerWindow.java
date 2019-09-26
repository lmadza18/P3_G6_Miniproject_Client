package P3_G6_Miniproject_Client;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class InstrumentPickerWindow extends Pane {

    Button closeButton;
    Button chooseButton;

    public InstrumentPickerWindow(){
        this.setTranslateX(Main.root.getWidth() / 8);
        this.setTranslateY(Main.root.getHeight() / 8);
        this.setStyle("-fx-background-color: #blue");


        Rectangle r = new Rectangle(0, 0, Main.root.getWidth() / 8 * 6, Main.root.getHeight() / 8 * 6);
        this.setStyle("-fx-border-color:red;");
        //r.setFill(Color.BLUE);

        closeButton = new Button("close");
        chooseButton = new Button("choose");

        this.getChildren().addAll(r, closeButton);
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
