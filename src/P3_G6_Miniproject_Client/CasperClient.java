package P3_G6_Miniproject_Client;

import de.sciss.net.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class CasperClient {


    public static void main(String[] args) {
        // write your code here
        final Object sync = new Object();
        final OSCClient client;
        final OSCBundle bndl1, bndl2;
        final Integer nodeID;

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
                // if we get the /n_end message, wake up the main thread
                // ; note: we should better also check for the node ID to make sure
                // the message corresponds to our synth
                if (message.getName().equals("/n_end")) {
                    synchronized (sync) {
                        sync.notifyAll();
                    }
                }
            }
        });
        // let's see what's going out and coming in
        client.dumpOSC(OSCChannel.kDumpBoth, System.err);

        try {
            /*
            // the /notify message tells scsynth to send info messages back to us
            client.send(new OSCMessage("/notify", new Object[]{new Integer(1)}));
            // two bundles, one immediately (with 50ms delay), the other in 1.5 seconds
            bndl1 = new OSCBundle(System.currentTimeMillis() + 50);
            bndl2 = new OSCBundle(System.currentTimeMillis() + 1550);
            // this is going to be the node ID of our synth
            nodeID = new Integer(1001 );
            // this next messages creates the synth
            bndl1.addPacket(new OSCMessage("/s_new", new Object[]{"default", nodeID, new Integer(1), new Integer(0)}));
            // this next messages starts to releases the synth in 1.5 seconds (release time is 2 seconds)
            bndl2.addPacket(new OSCMessage("/n_set", new Object[]{nodeID, "gate", new Float(-(2f + 1f))}));
            // send both bundles (scsynth handles their respective timetags)
            client.send(bndl1);
            client.send(bndl2);

            // now wait for the signal from our osc listener (or timeout in 10 seconds)
            synchronized (sync) {
                sync.wait(10000);
            }
            */
            // ok, unsubscribe getting info messages
            client.send(new OSCMessage("/notify", new Object[]{new Integer(0)}));

            client.send(new OSCMessage("/test", new Object[]{new Integer(0)}));

            // ok, stop the client
            // ; this isn't really necessary as we call dispose soon
            client.stop();
        } catch (IOException /* | InterruptedException */ e11) {
            e11.printStackTrace();
        }

        // dispose the client (it gets stopped if still running)
        client.dispose();
    }
}
