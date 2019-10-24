package P3_G6_Miniproject_Client;

import de.sciss.net.OSCClient;
import de.sciss.net.OSCListener;
import de.sciss.net.OSCMessage;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;


public class RootUI extends Pane {


    StageSpot[] stageSpots = new StageSpot[4];
    BandPlayer[] bandPlayers = new BandPlayer[4];
    InstrumentPickerWindow instrumentPickerWindow;
    Button leaveStageSpotButton;

    OSCClient client;
    RootUI() {



    }

    public void start() {
        ImageView bgImg = new ImageView("images/stage.jpg");
        bgImg.setFitWidth(this.getWidth());
        bgImg.setFitHeight(this.getHeight());

        try {
            client = OSCClient.newUsing(OSCClient.UDP);    // create UDP client with any free port number
            client.setTarget(new InetSocketAddress("localhost", 8000));  // talk to scsynth on the same machine
            client.start();  // open channel and (in the case of TCP) connect, then start listening for replies
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        // register a listener for incoming osc messages
        client.addOSCListener(new OSCListener() {
            public void messageReceived(OSCMessage message, SocketAddress address, long time) {
            }
        });
        try {
            // ok, unsubscribe getting info messages
            client.send(new OSCMessage("/hello", new Object[]{new Integer(0)}));

            client.send(new OSCMessage("/test", new Object[]{new Integer(0)}));

            // ok, stop the client
            // ; this isn't really necessary as we call dispose soon
        } catch (IOException /* | InterruptedException */ e11) {
            e11.printStackTrace();
        }

        //client.send(new OSCMessage("/" + string, new Object[]{new Integer(0)}));

        bandPlayers[0] = new BandPlayer("images/guitarist.png", 0);
        bandPlayers[1] = new BandPlayer("images/drummer1.png", 1);
        bandPlayers[2] = new BandPlayer("images/bassist.png", 2);
        bandPlayers[3] = new BandPlayer("images/sprite.png", 3);

        stageSpots[0] = new StageSpot(this.getWidth() / 8, this.getHeight() / 8 * 6);
        stageSpots[1] = new StageSpot(this.getWidth() / 8 * 3, this.getHeight() / 8 * 5.5);
        stageSpots[2] = new StageSpot(this.getWidth() / 8 * 5, this.getHeight() / 8 * 5.5);
        stageSpots[3] = new StageSpot(this.getWidth() / 8 * 7, this.getHeight() / 8 * 6);

        instrumentPickerWindow = new InstrumentPickerWindow();

        leaveStageSpotButton = new Button("get my ass outta here!");

        this.getChildren().addAll(bgImg, stageSpots[0], stageSpots[1], stageSpots[2], stageSpots[3]);
    }

    public void ClientMessage(String string){
        try{
        client.send(new OSCMessage("/" + string, new Object[]{new Integer(0)}));
        } catch (IOException /* | InterruptedException */ e11) {
            e11.printStackTrace();
        }
    }
}
