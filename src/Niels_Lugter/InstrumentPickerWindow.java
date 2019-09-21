package Niels_Lugter;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InstrumentPickerWindow extends Pane {

    public InstrumentPickerWindow(){

        Rectangle r = new Rectangle(25,25,250,250);
        r.setFill(Color.BLUE);

        this.getChildren().add(r);


    }
}
