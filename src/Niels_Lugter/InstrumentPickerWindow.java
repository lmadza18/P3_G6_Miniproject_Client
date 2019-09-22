package Niels_Lugter;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InstrumentPickerWindow extends Pane {

    Button closeButton;

    public InstrumentPickerWindow(){

        Rectangle r = new Rectangle(25,25,250,250);
        r.setFill(Color.BLUE);

        closeButton = new Button("close");

        this.getChildren().addAll(r, closeButton);
        buttonPress();


    }

    void buttonPress() {
        closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(this);
            closeButton.setManaged(false);
            closeButton.setVisible(false);


        });
    }

}
