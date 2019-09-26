package Niels_Lugter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static RootUI root = new RootUI();

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            primaryStage.setTitle("Band Room");
            primaryStage.setFullScreen(true);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            root.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FUCK");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


