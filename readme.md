# Megahay

Have you ever dreamt about being a rock star? Now's your chance! 
Join the infamous rock band Megahay on their World Tour. 
Choose your favourite instrument and jam with all your friends in front of a live audience.


![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")

## Releases 
[Release version 1.0](somelink)

## Instructions
When the client side of the program is running, the client have to input an IP-address. 
This is the IP-address which host the server (hostname).

The client can then choose a stagespot and a bandplayer, using the mouse to select and confirm their choice. 
Depending on the chosen bandplayer the client can play notes using the following keys:
* A
* S
* D
* F
* G
* H
* J
* K

The client can also change the duration of the notes, by turning the pedal on or off using the 
P key.

It's possible for the client to change their chosen stagespot and bandplayer at any time,
 by pressing the button in the upper left corner saying "get my ass outta here". 


## Dependencies

### Java 11
The project is built in Java 11 using the JavaFX platform for GUI-elements.
 
### [NetUtil](https://github.com/Sciss/NetUtil)
An [OpenSoundControl](http://opensoundcontrol.org/introduction-osc) (OSC) communication library for Java. Documentation for it can be found [here](https://www.sciss.de/netutil/doc/api/index.html). The JAR-file is in the intellij project

## Communication

This repository is the client side of the application. The server side can be found [here](https://github.com/malteerasmussen/P3_G6_Miniproject_Server).
Running the server is needed in order for multiple players to play together. The server runs the NetUtil() . 
We use a UDP server, which runs on port 8000. We import several OSC libraries (e.g. OSCClient, OSCListener and OSCMessage), 
which are used to set up, listen for, receive and send OSC messages between the client and server. 

All OSC messages are divided into 4 categories. The OSC receiver distinguish between the four types, 
through the String put in the message.getName() entry. The 4 categories are:
* Establish communication ("hello")
* Status ("status")
* GUI Messages ("GUImessage")
* Sounds ("Sound")

The Establish communication is sent as soon as the client finds the server 
and are used to notify the server that a player has joined.
The Status is continuously sent to the server to confirm that the client is still active.
The GUI Messages contains all GUI chances (e.g. when choosing a bandplayer).
The Sounds contains all information necessary for the system to play the correct notes.

## Authors
* **Casper Skaarup Ovesen**
* **Kristinn Bragi Garðarsson**
* **Malte Elkær Rasmussen** 
* **Mikkel Kappel Persson**
* **Niels Erik Raursø**
* **Tor Arnth Petersen**

See also the list of [contributors](https://github.com/lmadza18/P3_G6_Miniproject_Client/contributors) 
who participated in this project.


## Acknowledgments

* Thanks to [Molly and Joy-Joy](https://www.goatslive.com/)
* etc
