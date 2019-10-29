package P3_G6_Miniproject_Client;

import de.sciss.net.OSCClient;
import de.sciss.net.OSCListener;
import de.sciss.net.OSCMessage;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;

public class OSC {
    static OSCClient client;
    StageSpot[] SPreference;


    static public void sendMessage(String string, int spotId, int instrumentId, String operation) {
        Object args[] = new Object[3];
        args[0] = spotId;
        args[1] = instrumentId;
        args[2] = operation;

        try {
            client.send(new OSCMessage("/" + string, args));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    OSC(StageSpot[] spr) {
        SPreference = spr;
        System.out.println(spr[0].taken);

        try {
            client = OSCClient.newUsing(OSCClient.UDP);    // create UDP client with any free port number
            client.setTarget(new InetSocketAddress("192.168.43.10", 8000));  // talk to scsynth on the same machine
            //client.setTarget(new InetSocketAddress("localhost", 8000));  // talk to scsynth on the same machine
            client.start();  // open channel and (in the case of TCP) connect, then start listening for replies
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        // register a listener for incoming osc messages
        client.addOSCListener(new OSCListener() {
            public void messageReceived(OSCMessage message, SocketAddress address, long time) {
                System.out.println("MESSAGE:" + message.getName() + " RECEIVED FROM: " + address);

                // store Id's from message
                int spotId = (int) message.getArg(0);
                int InstrumentId = (int) message.getArg(1);

                if (message.getName().contains("/server/setPlayerId")) {
                    int sID = (int) message.getArg(0);
                    System.out.println("You are player " + sID);
                }
                // Receiving GUI messages
                if (message.getName().contains("/GUImessage")) {

                    //String operation = (String)message.getArg(2);
                    if (message.getArg(2).equals("take")) {
                        SPreference[spotId].displayBandPlayer(InstrumentId, false);
                    }
                    if (message.getArg(2).equals("leave")) {
                        SPreference[spotId].removeBandPlayer();
                    }
                    if (message.getArg(2).equals("reserve")) {
                        SPreference[spotId].stageSpotButton.setVisible(false);
                    }
                }
                // Receiving sound messages
                if (message.getName().contains("/Sound")) {
                    try {
                        String[] parts = message.getName().split("/");
                        String type = parts[3]; //The type of instrument which is being used by other clients
                        String key = parts[4]; //The key pressed by other clients
                        System.out.println("Key: " + key);

                        // local reference to specific instruments from which the message is received
                        Instrument instrument = SPreference[spotId].bandPlayer.instrument;
                        if (type.equals(instrument.type)) {
                            instrument.playSound(instrument.map.get(key).getMedia());
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                    }

                }
            }
        });
        try {
            // ok, unsubscribe getting info messages
            client.send(new OSCMessage("/hello", new Object[]{new Integer(0)}));

            // ok, stop the client
            // ; this isn't really necessary as we call dispose soon
        } catch (IOException /* | InterruptedException */ e11) {
            e11.printStackTrace();
        }

    }
}
