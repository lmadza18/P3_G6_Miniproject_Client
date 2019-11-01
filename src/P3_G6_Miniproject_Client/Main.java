package P3_G6_Miniproject_Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// DOCUMENTATION: https://www.sciss.de/netutil/doc/api/index.html


public class Main extends Application {
    public static RootUI root = new RootUI();




    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Band Room");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //
        //root.runIpWindow();
        root.start();

        // sending status to server
        new Thread(() -> {
            while (primaryStage.isShowing()) {
                System.out.print("");
                if (OSC.connected) {
                    try {

                        OSC.sendStatus();
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    public static void main(String[] args) {
        launch(args);
    }



}


