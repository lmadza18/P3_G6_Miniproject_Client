package P3_G6_Miniproject_Client;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RootUI extends Pane {

    RootUI() {

    }

    public void start() {
        ImageView imageView = new ImageView("images/stage.jpg");
        imageView.setFitWidth(this.getWidth());
        imageView.setFitHeight(this.getHeight());

        PosButton button1 = new PosButton("Position1", this.getWidth() / 8, this.getHeight() / 8 * 6);
        PosButton button2 = new PosButton("Position2", this.getWidth() / 8 * 3, this.getHeight() / 8 * 5.5);
        PosButton button3 = new PosButton("Position3", this.getWidth() / 8 * 5, this.getHeight() / 8 * 5.5);
        PosButton button4 = new PosButton("Position4", this.getWidth() / 8 * 7, this.getHeight() / 8 * 6);

        this.getChildren().addAll(imageView, button1, button2, button3, button4);
    }


}
