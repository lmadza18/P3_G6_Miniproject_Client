package Niels_Lugter;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InstrumentPickerWindow extends Pane {

    Button closeButton;
    Button chooseButton;

    double x = getHeight()/2;
    double y= getWidth()/2;
    double size = this.getHeight()/2;


    public InstrumentPickerWindow(){
        Rectangle r = new Rectangle(x-size/2, y-size/2, size, size);
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
