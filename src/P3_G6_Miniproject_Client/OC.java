package P3_G6_Miniproject_Client;

import de.sciss.net.OSCClient;
import de.sciss.net.OSCListener;
import de.sciss.net.OSCMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class OC {
    static OSCClient client;


    static public void sendMessage(String string){
        try{
            client.send(new OSCMessage("/" + string, new Object[]{new Integer(0)}));
        } catch (IOException /* | InterruptedException */ e11) {
            e11.printStackTrace();
        }
    }

    OC(){
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

            // ok, stop the client
            // ; this isn't really necessary as we call dispose soon
        } catch (IOException /* | InterruptedException */ e11) {
            e11.printStackTrace();
        }

    }
}
