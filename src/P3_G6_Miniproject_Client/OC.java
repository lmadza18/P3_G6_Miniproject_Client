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

public class OC {
    static OSCClient client;
    StageSpot[] SPreference;
    Instrument[] IPreference;



    static public void sendMessage(String string) {
        try {
            System.out.println("Client sending: " + string);
            client.send(new OSCMessage("/" + string, new Object[]{new Integer(0)}));
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

    OC(StageSpot[] spr, Instrument[] ipr) {
        SPreference = spr;
        IPreference = ipr;
        System.out.println(spr[0].taken);

        try {
            client = OSCClient.newUsing(OSCClient.UDP);    // create UDP client with any free port number
            client.setTarget(new InetSocketAddress("192.168.43.207", 8000));  // talk to scsynth on the same machine
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
                        for(int i = 0; i <= IPreference.length; i++){
                            if (type.equals(IPreference[i].type)){
                                IPreference[i].playSound(IPreference[i].map.get(key));
                            }
                        }
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
