package P3_G6_Miniproject_Client;

import de.sciss.net.OSCClient;
import de.sciss.net.OSCListener;
import de.sciss.net.OSCMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class OSC {
    static OSCClient client; // This is the client
    StageSpot[] SPreference; // This is a reference to all the stagespots
    static int sID;
    String hostName;


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
        this.hostName = Main.root.ipWindow.getIpAddress();
        System.out.println(this.hostName);


        try {
            client = OSCClient.newUsing(OSCClient.UDP);    // create UDP client with any free port number
            client.setTarget(new InetSocketAddress(this.hostName, 8000));  // Find server host
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

                // Set player ID
                if (message.getName().contains("/server/setPlayerId")) {
                    sID = (int) message.getArg(0);
                    System.out.println("You are player " + sID);
                }
                // Receiving GUI messages
                if (message.getName().contains("/GUImessage")) {
                    System.out.println("OPERATION: " + message.getArg(2));

                    // If the operation received in the message is "take"
                    // display a bandplayer on that spotID included in the message
                    if (message.getArg(2).equals("take")) {
                        SPreference[spotId].displayBandPlayer(InstrumentId, false);
                    }
                    // If the operation received in the message is "leave"
                    // remove a bandplayer on that spotID included in the message
                    if (message.getArg(2).equals("leave")) {
                        SPreference[spotId].removeBandPlayer();
                    }
                    // If the operation received in the message is "reserve"
                    // remove a stagespot button on that spotID included in the message
                    if (message.getArg(2).equals("reserve")) {
                        SPreference[spotId].stageSpotButton.setVisible(false);
                        SPreference[spotId].taken = true;
                    }
                    // If the operation received in the message is "release"
                    // display a stagespot button on that spotID included in the message again
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
            client.send(new OSCMessage("/hello", new Object[]{new Integer(0)}));

        } catch (IOException e11) {
            e11.printStackTrace();
        }
    }
}
