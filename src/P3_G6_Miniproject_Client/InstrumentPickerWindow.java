package P3_G6_Miniproject_Client;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InstrumentPickerWindow extends VBox {

    Button closeButton;
    Button chooseButton;


    double size = 200;
    double x = 0;
    double y= 0;


    public InstrumentPickerWindow(){
        this.setAlignment(Pos.CENTER);
        Rectangle r = new Rectangle(x ,y, size, size);
        r.setFill(Color.BLUE);

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
