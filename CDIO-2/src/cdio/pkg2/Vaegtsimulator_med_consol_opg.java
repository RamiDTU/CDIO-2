

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Vaegtsimulator_med_consol_opg {
	
	static ServerSocket listener;
	private static double brutto = 0, tara = 0;
	private static String client, server;
	private static String InstruktionsDisplay = "";
	private static int portdst = 8000;
	private static Socket sock;
	private static BufferedReader instream;
	private static DataOutputStream outstream;

	public static void printmenu() {
		for (int i = 0; i < 10; i++)
			System.out.println(" ");
		System.out.println("*************************************************");
		System.out.println("Netto: " + (brutto - tara) + " kg");
		System.out.println("Instructiondisplay: " + InstruktionsDisplay);
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Debug info: ");
		System.out.println("Hooked up to " + sock.getInetAddress());
		System.out.println("Brutto: " + (brutto) + " kg");
		System.out.println("String received: " /*string is needed for RM20*/);
		System.out.println(" ");
		System.out.println("This weight simulator is listening to the commands ");
		System.out.println("S, T, B, D, DW, E, Q ");
		System.out.println("on the communication-port. ");
		System.out.println("******");
		System.out.println("Enter S for print menu, Enter T for tara, Enter B (number) for new brutto,");
		System.out.println("Enter D (text) for new text on display, Enter DW to remove text on display");
		System.out.println("Enter E for exiting weight console, Enter Q for quitting the program");
		System.out.print("Please Enter here: ");		
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		String temp;
		boolean clientRun, serverRun;
		System.out.println("Waiting for connection on standard port: " + portdst);
		System.out.println("Do you want to change he port number? (Y/N)");
		temp = sc.nextLine().toUpperCase();
		if (temp.equals("Y")){
			System.out.println("Enter new port number:");
			portdst = sc.nextInt();
		}
		System.out.println("Waiting for connections on port: " + portdst);
		listener = new ServerSocket(portdst);
		sock = listener.accept();
		instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		outstream = new DataOutputStream(sock.getOutputStream());
		printmenu();
		
		client = ""; // client skal være tom fra start. Ellers vil den være null hvilket programmet ikke forstår
		server = ""; // server skal være tom fra start. Ellers vil den være null hvilket programmet ikke forstår
		
		while(true){
		try {     
				//while (((server = sc.nextLine().toUpperCase()).matches("[A-Z]")) || !(client = instream.readLine().toUpperCase()).isEmpty()) {
				 // server string tager imod input fra server consolen - client string modtager TCPClient input hvis der er noget
			//temp = instream.readLine();
			//if(temp.equals("C")){
				clientRun = true;
			while(clientRun){
				client = instream.readLine();
				if (client.startsWith("S")) {
					outstream.writeBytes((brutto - tara) + "\r\n");
				}
				else if (client.startsWith("T")) {
					tara = brutto;
					outstream.writeBytes("T S\r\n");
					printmenu();
				}
				else if (client.startsWith("B")) { 
					temp = client.substring(2, client.length());
					brutto = Double.parseDouble(temp);
					outstream.writeBytes("DB\r\n");
					printmenu();
				}
				else if (client.startsWith("Z")) {
					brutto = 0;
					outstream.writeBytes("Z S\r\n");
					printmenu();
				}
				else if (client.equals("DW")) {
					InstruktionsDisplay = "";
					printmenu(); 
					outstream.writeBytes("DW A " + (brutto - tara) + " kg " + "\r\n");
				}
				else if (client.startsWith("D")) {
					if (client.equals("D")) {
						InstruktionsDisplay = "";
						printmenu(); 
						outstream.writeBytes("D A\r\n");
					} else if (client.startsWith("D ")) {
						InstruktionsDisplay = (client.substring(2, client.length()));
						printmenu();
						outstream.writeBytes("D A\r\n");
					}
				}
				else if ((client.startsWith("RM20 8"))) {
					InstruktionsDisplay = (client.substring(6, client.length()));
					printmenu();
					outstream.writeBytes("RM20 B\r\n");
					System.out.println("\nWrite a response to client:");
					temp = sc.nextLine();
					printmenu();
					outstream.writeBytes("RM20 A " + temp + "\r\n");
				}
				else if (client.startsWith("P111")) {
					InstruktionsDisplay = (client.substring(5, client.length()));
					printmenu();
					outstream.writeBytes("P111 A\r\n");
					}
				else if (client.startsWith("Exit")) {
					clientRun = false;
					}
				// client for fjernet sin seneste kommando
				client = "";
			}
		//}
			
			if(temp.equals("W")){
				serverRun = true;
			while(serverRun) {
				server = sc.nextLine().toUpperCase();
				if (server.equals("S")) {
					printmenu();
				}
				else if (server.equals("T")) {
					tara = brutto;
					printmenu();
				}
				else if (server.startsWith("B")) {
					temp = server.substring(2, server.length());
					brutto = Double.parseDouble(temp);
					printmenu();
				}
				else if (server.equals("DW")) {
					InstruktionsDisplay = "";
					printmenu();
				}
				else if (server.startsWith("D")) {
					if (server.equals("D")){
						InstruktionsDisplay = "";
						printmenu();
					}
					else if (server.startsWith("D ")){
						InstruktionsDisplay = server.substring(2, server.length());
						printmenu();
					}
				}
				else if (server.equals("E")) {
					System.out.println("The weight console is not available.\nYou can start it by exiting the TCP client.");
					outstream.writeBytes("Exit\r\n");
					serverRun = false;
					}
				else if (server.equals("Q")) {
					System.out.println("");
					System.out.println("Program terminated. Q recieved on com port");
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
					System.exit(0);
				}
				// server for fjernet sin seneste kommando
				server = "";
			}
		}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		}
	}
}