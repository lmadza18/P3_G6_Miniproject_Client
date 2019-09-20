package Niels_Lugter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MalteMain extends Application {


    @Override
    public void start(Stage primaryStage) {

        Pane bg = new Pane();
        bg.setPrefSize(100, 100);

        ImageView imageView = new ImageView("images/stage.jpg");
        imageView.setFitWidth(1200);
        imageView.setFitHeight(800);
        bg.getChildren().add(imageView);
        Scene scene = new Scene(bg, 1200, 800);
        //  scene.getStylesheets().add("style.css");

        PosButton button1 = new PosButton("Position1", bg.getWidth() / 8, bg.getHeight() / 8 * 6, bg);


        primaryStage.setTitle("Something");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

