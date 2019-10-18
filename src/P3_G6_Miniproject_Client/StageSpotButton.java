package P3_G6_Miniproject_Client;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class StageSpotButton extends StackPane {

    Button button;
    ImageView buttonImg;
    ImageView buttonHoverImg;
    int buttonSize;

    int imageSize;

    double x;
    double y;

    //Put button in here
    StageSpotButton() {

        imageSize = (int) Math.round(Main.root.getWidth() / 10);
        buttonSize = Math.round(imageSize / 6 * 4);
        int buttonTransform = Math.round(imageSize / 6);
        buttonImg = new ImageView("Takespot_button.png");
        buttonHoverImg = new ImageView("Takespot_button_hover.png");

        buttonImg.setFitWidth(imageSize);
        buttonImg.setFitHeight(imageSize);
        buttonHoverImg.setFitWidth(imageSize);
        buttonHoverImg.setFitHeight(imageSize);

        button = new Button(null);
        button.setStyle("-fx-background-color: transparent");
        button.setMinSize(buttonSize, buttonSize);
        button.setTranslateX(buttonTransform);
        button.setTranslateY(buttonTransform);

        getChildren().addAll(buttonImg, buttonHoverImg, button);

        //button.setPickOnBounds(true);
        buttonHoverImg.setVisible(false);

        button.setOnMouseEntered(t -> {
            buttonImg.setVisible(false);
            buttonHoverImg.setVisible(true);
        });

        button.setOnMouseExited(t -> {
            buttonImg.setVisible(true);
            buttonHoverImg.setVisible(false);
        });

//        this.x = x - (imageSize / 2);
//        this.y = y - (imageSize / 2);
//        this.setTranslateX(this.x);
//        this.setTranslateY(this.y);


    }
}
