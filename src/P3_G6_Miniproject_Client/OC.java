package P3_G6_Miniproject_Client;

import de.sciss.net.OSCClient;
import de.sciss.net.OSCListener;
import de.sciss.net.OSCMessage;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;

public class OC {
    static OSCClient client;
    StageSpot[] SPreference;
    Instrument instrument;


    static public void sendMessage(String string) {
        try {
            System.out.println("Client sending: " + string);
            client.send(new OSCMessage("/" + string, new Object[]{new Integer(0)}));
        } catch (IOException /* | InterruptedException */ e11) {
            e11.printStackTrace();
        }
    }
    static public void sendMessage(String string, Media media) {
        Object[] arg = new Object[1];
        arg[0] = media;
        try {
            System.out.println("Client sending: " + string + " and a media object");
            client.send(new OSCMessage("/" + string, arg));
        } catch (IOException /* | InterruptedException */ e11) {
            e11.printStackTrace();
        }
    }

    static public void sendMessage(String string, int spotId, int instrumentId, String operation) {
        Object args[] = new Object[3];
        args[0] = spotId;
        args[1] = instrumentId;
        args[2] = operation;

        try {
            System.out.println("sending message");
            client.send(new OSCMessage("/" + string, args));
            System.out.println("sent message");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    OC(StageSpot[] spr) {
        SPreference = spr;
        System.out.println(spr[0].taken);

        try {
            client = OSCClient.newUsing(OSCClient.UDP);    // create UDP client with any free port number
            client.setTarget(new InetSocketAddress("192.168.8.103", 8000));  // talk to scsynth on the same machine
            client.start();  // open channel and (in the case of TCP) connect, then start listening for replies
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        // register a listener for incoming osc messages
        client.addOSCListener(new OSCListener() {
            public void messageReceived(OSCMessage message, SocketAddress address, long time) {
                System.out.println("MESSAGE:" + message.getName() + " RECEIVED FROM: " + address);
                if (message.getName().contains("/server/setPlayerId")) {
                    int sID = (int) message.getArg(0);
                    SPreference[sID].playerID = sID;
                    System.out.println("SPreference[id].playerID" + SPreference[sID].playerID);
                }
                // Receiving GUI messages
                if (message.getName().contains("/GUImessage")) {
                    System.out.println(message.getArgCount());
                    int spotId = (int) message.getArg(0);
                    int InstrumentId = (int) message.getArg(1);
                    //String operation = (String)message.getArg(2);
                    if (message.getArg(2).equals("take")) {
                        SPreference[spotId].displayBandPlayer(InstrumentId);
                    }
                    if (message.getArg(2).equals("leave")) {
                        SPreference[spotId].removeBandPlayer();
                    }
                }
                if (message.getName().contains("/Sound")) {
                    try{
                        String[] parts = message.getName().split("/");
                        String type = parts[1];
                        String key = parts[2];
                        String value = parts[3];
                        

                    }
                    catch (IllegalArgumentException e){
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
