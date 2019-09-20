package Niels_Lugter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        Pane buttonPane = new Pane();
        primaryStage.setTitle("Band Room");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        ImageView imageView = new ImageView("images/stage.jpg");
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        root.getChildren().addAll(imageView);


        PosButton button1 = new PosButton("Position1", root.getWidth() / 8, root.getHeight() / 8 * 6, root);
        PosButton button2 = new PosButton("Position2", root.getWidth() / 8 * 3, root.getHeight() / 8 * 5.5, root);
        PosButton button3 = new PosButton("Position3", root.getWidth() / 8 * 5, root.getHeight() / 8 * 5.5, root);
        PosButton button4 = new PosButton("Position4", root.getWidth() / 8 * 7, root.getHeight() / 8 * 6, root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
