package Niels_Lugter;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InstrumentPickerWindow extends Pane {

    double x;
    double y;
    double size = 250;

    public InstrumentPickerWindow(double x, double y){
        this.x = x;
        this.y = y;
        Rectangle r = new Rectangle(x-size/2, y-size/2, size, size);
        r.setFill(Color.BLUE);

        this.getChildren().add(r);

    }
}
