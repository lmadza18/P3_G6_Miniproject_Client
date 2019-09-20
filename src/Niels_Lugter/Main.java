package Niels_Lugter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        primaryStage.setTitle("Band Room");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        ImageView imageView = new ImageView("images/stage.jpg");
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        root.getChildren().addAll(imageView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
