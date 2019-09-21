package Niels_Lugter;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class PosButton extends Button {
    double x;
    double y;
    String title;


    PosButton(String title, double x, double y) {
        this.x = x;
        this.y = y;
        this.title = title;

        //  ImageView imgButton = new ImageView("images/drummer1.png");
        //  ImageView imgButton2 = new ImageView("images/drummer2.png");
        //  imgButton.setFitWidth(150);
        //  imgButton.setFitHeight(150);
        //  button.setGraphic(imgButton);
        //  button1.setStyle("-fx-background-color: transparent");
        this.setTranslateX(x);
        this.setTranslateY(y);
        buttonPress();
    }

    void buttonPress() {
        this.setOnAction(actionEvent -> {
            InstrumentPickerWindow instrumentPickerWindow = new InstrumentPickerWindow();
            Main.root.getChildren().add(instrumentPickerWindow);
            System.out.println(title);

        });
    }
}
