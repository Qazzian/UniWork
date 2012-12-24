// Distributed Computing - Assignment 2
// Contact Mark Stokes (ssu97mas@reading.ac.uk)


import java.util.*;
import java.net.*;
import java.io.*;


// Create a thread to continually watch for messages coming in from the Multicast group
class messagesIn extends Thread {
	
	// create a variable to for the Multicast Socket connection
	private MulticastSocket socket;

	public messagesIn(MulticastSocket s) throws IOException	{
		socket = s;
		// Start calls Run()
		start();
	}

	public void run() {
		try {
			String localIP = InetAddress.getLocalHost().toString();
			while(true)
			{
				// create a buffer to hold incoming messages from the Multicast group
				byte[] buffer= new byte[1000];
				// create a Datagram Packet to hold an individual message from the Multicast group.
				DatagramPacket remoteMsg = new DatagramPacket(buffer, buffer.length);
				// Wait until a message is received from the multicast group, and copy it to remoteMsg
				socket.receive(remoteMsg);

				// Find out the IP address of the message sender
				String groupIP = remoteMsg.getAddress().toString();
				// Check to see if the Message came from local host
				if (localIP.toUpperCase().equals(groupIP.toUpperCase()))
				{
					// If it did, then don't output hte message
					// Output the input> prompt to the local host.
					System.out.println();
					System.out.print("Input> ");
				}
				else
				{
					// If message IS from a different computer, output the message...
					// Trim the received message so that lots of annoying spaces aren't output,
					// and output the message to the local host
					System.out.println();
					System.out.println(new String(remoteMsg.getData(), 0, 0, remoteMsg.getLength()));
					// Output the input> prompt to the local host.
					System.out.println();
					System.out.print("Input> ");
				}
			}
		}catch(IOException e){System.out.println("IO: " + e.getMessage());
		}
	}
}




public class MulticastPeer{
	// Set up port number
	public static final int PORT = 2468;

	public static void main (String args[]){
		// args give message contents & destination multicast group (e.g. "228.5.6.7")
		if (args.length != 2)
			// If not, output an error message:
			System.out.println("Usage: Java MulticastPeer <User Name> <Multicast Address>");
		else {

			// output a pointless but pretty header.
	    	System.out.println("           Welcome to Mark Stokes' JAVA Chat Client");
	    	System.out.println();
	    	System.out.println("              Distributed Computing Assignment 2");
	    	System.out.println("          Contact Mark Stokes (ssu97mas@reading.ac.uk)");
	    	System.out.println();
	    	System.out.println("         The date is: " + new Date());
	    	System.out.println("         Type '/close' to end.");
	    	System.out.println();

			// Get username from the arguments passed on the comamnd line
			String username = args[0];

			try {
				// Create a new group based on the multicast IP address passed from the command line.
			  	InetAddress group = InetAddress.getByName(args[1]);
				// Create a new socket connection to the Multicast IP address na dpre-define port.
				MulticastSocket s = new MulticastSocket(PORT);
				// Actually join the multicast group.
				s.joinGroup(group);

				// Start a new thread to watch for messages from the group
				new messagesIn(s);

				// Create a variable to hold Local input strings.
				BufferedReader localInput = new BufferedReader(new InputStreamReader(System.in));
				// Output the input> prompt to the local host.
				System.out.println();
				System.out.print("Input> ");

				// loop forever (until /close is entered)
				while(true) {
					// Read input from the command line
					String localMessage = localInput.readLine();
					// If local input = /close, then quit the program
					if (localMessage.equals("/close")) break;
					else {
						// If message is NOT /close then add username to start of message
						localMessage = username + ": " + localMessage;
						// Create a byte of data to be sent to the group....
						byte[] msgByte = localMessage.getBytes();
						DatagramPacket Data = new DatagramPacket(msgByte, msgByte.length, group, PORT);
						// Send the message to the group
						s.send(Data);
					}
				}
				// Leave the multicast group
				s.leaveGroup(group);
				// Display pointless but pretty exitting text
				System.out.println();
				System.out.println("JAVA Chat Client now quitting...");
			    System.out.println("Thank you for using Mark Stokes' JAVA Chat Client");
			}catch(SocketException e){System.out.println("Socket: " + e.getMessage());
			}catch(IOException e){System.out.println("IO: " + e.getMessage());
			}
			// Shuts down all threads.
			System.exit(0);
		}
	}
}