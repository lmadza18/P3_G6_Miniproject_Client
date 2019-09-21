package Niels_Lugter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    static Pane root = new Pane();

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Band Room");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        ImageView imageView = new ImageView("images/stage.jpg");
        imageView.setFitWidth(root.getWidth());
        imageView.setFitHeight(root.getHeight());



        PosButton button1 = new PosButton("Position1", root.getWidth() / 8, root.getHeight() / 8 * 6);
        PosButton button2 = new PosButton("Position2", root.getWidth() / 8 * 3, root.getHeight() / 8 * 5.5);
        PosButton button3 = new PosButton("Position3", root.getWidth() / 8 * 5, root.getHeight() / 8 * 5.5);
        PosButton button4 = new PosButton("Position4", root.getWidth() / 8 * 7, root.getHeight() / 8 * 6);

        root.getChildren().addAll(imageView, button1, button2, button3, button4);
    }

    public void openInstrumentPickerWindow(){
    }




    public static void main(String[] args) {
        launch(args);
    }
}


