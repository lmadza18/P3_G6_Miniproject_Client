package P3_G6_Miniproject_Client;

import de.sciss.net.OSCClient;
import de.sciss.net.OSCListener;
import de.sciss.net.OSCMessage;

import javax.management.Notification;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.UnresolvedAddressException;


/**
 * This class is for handling client-server communication. It sets up a OSC Listener which detects incoming
 * messages and calls a specific method depending on the content of the message.
 * Furthermore, it is used to send message to the server.
 */


public class OSC {
    static OSCClient client; // This is the client
    StageSpot[] SPreference; // This is a reference to all the stageSpots
    static int sID;
    static String hostName;
    static boolean connected;


    OSC(StageSpot[] spr) {
        SPreference = spr;
        System.out.println(spr[0].taken);

    }

    static public void sendStatus() {
        try {
            client.send(new OSCMessage("/status", new Object[]{sID}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // We send messages to the server
    static void sendMessage(String string, int spotId, int instrumentId, String operation) {
        Object[] args = new Object[3];
        args[0] = spotId;
        args[1] = instrumentId;
        args[2] = operation;

        try {
            client.send(new OSCMessage("/" + string, args));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void runOSC() {

        try {
            client = OSCClient.newUsing(OSCClient.UDP);    // create UDP client
            client.setTarget(new InetSocketAddress(hostName, 8000));  // Find server host and specify port
            client.start();  // open channel and (in the case of TCP) connect, then start listening for replies
            client.send(new OSCMessage("/",new Object[]{}));
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        } catch (UnresolvedAddressException e2){
            System.out.println("Server not found. Playing locally.");
            client.setTarget(new InetSocketAddress("localhost", 8000));
        }

        // register a listener for incoming osc messages
        client.addOSCListener(new OSCListener() {
            public void messageReceived(OSCMessage message, SocketAddress address, long time) {
                System.out.println("MESSAGE:" + message.getName() + " RECEIVED FROM: " + address);

                // store Id's from message
                int spotId = (int) message.getArg(0);
                int InstrumentId = (int) message.getArg(1);

                // Set player ID
                if (message.getName().contains("/server/setPlayerId")) {
                    connected = true;
                    sID = (int) message.getArg(0);
                    System.out.println("You are player " + sID);
                }
                // Receiving GUI messages
                if (message.getName().contains("/GUImessage")) {

                    // If the operation received in the message is "take"
                    // display a bandPlayer on that spotID included in the message
                    if (message.getArg(2).equals("take")) {
                        SPreference[spotId].displayBandPlayer(InstrumentId, false);
                    }
                    // If the operation received in the message is "leave"
                    // remove a bandPlayer on that spotID included in the message
                    if (message.getArg(2).equals("leave")) {
                        SPreference[spotId].removeBandPlayer();
                    }
                    // If the operation received in the message is "reserve"
                    // remove a stageSpot button on that spotID included in the message
                    if (message.getArg(2).equals("reserve")) {
                        SPreference[spotId].stageSpotButton.setVisible(false);
                        SPreference[spotId].taken = true;
                    }
                    // If the operation received in the message is "release"
                    // display a stageSpot button on that spotID included in the message again
                    if (message.getArg(2).equals("release")) {
                        if (!Main.root.playing) {
                            SPreference[spotId].stageSpotButton.setVisible(true);
                        }
                        SPreference[spotId].taken = false;
                    }
                }
                // Receiving sound messages
                if (message.getName().contains("/Sound")) {
                    try {
                        //Split the message into an array of strings
                        String[] parts = message.getName().split("/");
                        String type = parts[3]; //The type of instrument which is being used by other clients
                        String key = parts[4]; //The key pressed by other clients

                        // local reference to specific instruments from which the message is received
                        Instrument instrument = SPreference[spotId].bandPlayer.instrument;

                        // Make sure you play the right instrument and then play the sound
                        if (type.equals(instrument.type)) {
                            if (message.getArg(2).equals("play")) {
                                instrument.map.get(key).playSound();
                            }
                            if (message.getArg(2).equals("stop")) {
                                instrument.map.get(key).stopSound();
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                    }

                }
            }
        });
        try {
            // Letting the server know, you are here
            client.send(new OSCMessage("/hello", new Object[]{0}));

        } catch (IOException | UnresolvedAddressException e11) {
            e11.printStackTrace();
        }
    }
}
