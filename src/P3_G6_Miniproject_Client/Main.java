package P3_G6_Miniproject_Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

    // DOCUMENTATION: https://www.sciss.de/netutil/doc/api/index.html
import de.sciss.net.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;


public class Main extends Application {
    public static RootUI root = new RootUI();


    @Override
    public void start(Stage primaryStage) throws Exception {



        // ------------------------------------------- OSC TEST
        final OSCClient c;

        try {
            c = OSCClient.newUsing(OSCClient.UDP);    // create UDP client with any free port number
            c.setTarget(new InetSocketAddress("127.0.0.1", 8000));  // talk to scsynth on the same machine
            c.start();  // open channel and (in the case of TCP) connect, then start listening for replies
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        Object args[] = new Object[2];
        args[0] = 3;
        args[1] = "hello";
        OSCMessage msg = new OSCMessage("/test", args);

        try {
            c.send(new OSCMessage("/hello", new Object[]{msg}));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // register a listener for incoming osc messages
        c.addOSCListener(new OSCListener() {
            @Override
            public void messageReceived(OSCMessage oscMessage, SocketAddress socketAddress, long l) {
                System.out.println("Message received: " + oscMessage.getName());
            }
        });


        // ------------------------------------------- OSC TEST

        primaryStage.setTitle("Band Room");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        root.start();


    }

    public static void main(String[] args) {
        launch(args);
    }
}


