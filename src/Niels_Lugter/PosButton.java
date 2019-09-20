package Niels_Lugter;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class PosButton {
    double x;
    double y;
    String title;

    PosButton(String title, double x, double y, Pane pane){
        this.x = x;
        this.y = y;

        Button button1 = new Button(title);
        //  ImageView imgButton = new ImageView("images/drummer1.png");
        //  ImageView imgButton2 = new ImageView("images/drummer2.png");
        //  imgButton.setFitWidth(150);
        //  imgButton.setFitHeight(150);
        //  button.setGraphic(imgButton);
        //  button1.setStyle("-fx-background-color: transparent");
        button1.setTranslateX(x);
        button1.setTranslateY(y);
        pane.getChildren().add(button1);

        /*button1.setOnAction(actionEvent -> {
            FlowPane menu = new FlowPane();
            menu.getChildren().add(new Button("Hello"));
            bg.getChildren().addAll(menu);
        });*/
    }
}
