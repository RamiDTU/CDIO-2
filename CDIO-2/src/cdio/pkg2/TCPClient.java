package pkg2;


import java.io.*; 
import java.net.*; 
import java.util.Scanner;
public class TCPClient {//implements Runnable { 

   
	public static void printmenu() {
		System.out.println();
		System.out.println();
		System.out.println("Please choose one of the following options: ");
		System.out.println("1. Read Weight");
		System.out.println("2. Tare Weight");
		System.out.println("3. Set Weight");
		System.out.println("4. Display Text");
		System.out.println("5. Display Weight");
		System.out.println("6. RM20");
		System.out.println("7. P111");
		System.out.println("8. Exit TCP client");
		System.out.println("9. Quit");
	}
	
	public static void main(String argv[]) throws Exception { 
	//public void run(){ 
		try {
        String sentence, temp, modifiedSentence = "", hostname, text1, text2, text3, clientStart;
        int port;
        boolean start = true, rm20, clientRunning;
        
        Scanner sc = new Scanner(System.in);
		System.out.println("Enter port number (xxxx): "); 
		port = sc.nextInt();
		System.out.println("Enter IP address (Ex 192.168.0.1): "); 
		hostname = sc.next();
		
        BufferedReader inFromUser = 
          new BufferedReader(new InputStreamReader(System.in)); 

        Socket clientSocket = new Socket(hostname, port); 

        DataOutputStream outToServer = 
          new DataOutputStream(clientSocket.getOutputStream());
        
        BufferedReader inFromServer = 
                new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream())); 
        
        while(start) {
        	clientRunning = true;
            System.out.println("Do you want to operate from TCPClient(C) or the Weight console(W)?\nType " + "\"C\" " + "or " + "\"W\"");
            clientStart = sc.next().toUpperCase();
            outToServer.writeBytes(clientStart + "\r\n");
            
        	if(clientStart.equals("C")){
        		while(clientRunning){
        	printmenu();
        	sentence = inFromUser.readLine();
        	System.out.println();
        	
        switch(sentence) {

        case "1":
            	outToServer.writeBytes("S\r\n");
            	modifiedSentence = inFromServer.readLine();
            	System.out.println("REPLY FROM SERVER: " + modifiedSentence);  
            	break;
        
        case "2":
                 outToServer.writeBytes("T\r\n");
                 modifiedSentence = inFromServer.readLine();
                 System.out.println("REPLY FROM SERVER: " + modifiedSentence);
                 break;
        
        case "3":
                System.out.println("Write the brutto weight: ");
                temp = inFromUser.readLine();
        		outToServer.writeBytes("B" + "\"" + temp + "\"" + "\r\n");
                modifiedSentence = inFromServer.readLine();
                System.out.println("REPLY FROM SERVER: " + modifiedSentence);
                break;
        
        case "4":
                System.out.println("Write the new display message: ");
                temp = inFromUser.readLine();
                outToServer.writeBytes("D " + temp + "\r\n");
                modifiedSentence = inFromServer.readLine();
                System.out.println("REPLY FROM SERVER: " + modifiedSentence);
                break;
        
        case "5":
                outToServer.writeBytes("DW\r\n");
                modifiedSentence = inFromServer.readLine();
                System.out.println("REPLY FROM SERVER: " + modifiedSentence);
                break;
        
        case "6":
        		System.out.println("Please write something:");
    			text1 = inFromUser.readLine();
    			if(!text1.matches("[A-Za-z0-9]+")){
    				rm20 = true;
        			while(rm20) {
        			System.out.println("Not a valid input, please try again:");
        			text1 = inFromUser.readLine();
        				if(text1.matches("[A-Za-z0-9]+")){
        					rm20 = false;
        				}
        			}
        		}
    			System.out.println("Please write something:");
    			text2 = inFromUser.readLine();
        		if(!text2.matches("[A-Za-z0-9]+")){
        			rm20 = true;
        			while(rm20) {
        			System.out.println("Not a valid input, please try again:");
        			text2 = inFromUser.readLine();
        				if(text2.matches("[A-Za-z0-9]+")){
        					rm20 = false;
        				}
        			}
        		}
        		System.out.println("Please write something:");
    			text3 = inFromUser.readLine();
    			if(!text3.matches("[A-Za-z0-9]+")){
    				rm20 = true;
        			while(rm20) {
        			System.out.println("Not a valid input, please try again:");
        			text3 = inFromUser.readLine();
        				if(text3.matches("[A-Za-z0-9]+")){
        					rm20 = false;
        				}
        			}
        		}
    			outToServer.writeBytes("RM20 8 " + "\"" + text1 + "\" "+ "\"" + text2 + "\" " + "\"" + text3 + "\"" + "\r\n");
    			modifiedSentence = inFromServer.readLine();
    	        System.out.println("REPLY FROM SERVER: " + modifiedSentence);
    	        modifiedSentence = inFromServer.readLine();
    		    System.out.println("REPLY FROM SERVER: " + modifiedSentence);
            	break;
        
        case "7": 
        	System.out.println("Write the new display message: ");
            temp = inFromUser.readLine();
            outToServer.writeBytes("P111 " + temp + "\r\n");
            modifiedSentence = inFromServer.readLine();
            System.out.println("REPLY FROM SERVER: " + modifiedSentence);
            break;
                
        case "8":
        	clientRunning = false;
        	System.out.println("The TCP client is not available.\nYou can start it by exiting the weight console.\n");
        	outToServer.writeBytes("Exit\r\n");
        	System.out.println("Do you want to operate from TCPClient(C) or the Weight console(W)?\nType " + "\"C\" " + "or " + "\"W\"");
            clientStart = sc.next().toUpperCase();
            outToServer.writeBytes(clientStart + "\r\n");
            break;
            
        case "9":
        	clientSocket.close();
            System.out.println("BYE BYE - Please come again");
            start = false;
            clientRunning = false;
            break;
            
        default:     
        	System.out.println("Non existing option. Please ty again:");
        	}
        	}
        }
        else {
        	System.out.println("The TCP client is not available.\nYou can start it by exiting the weight console.\n");
        	while(!modifiedSentence.equals("Exit")) {
        	modifiedSentence = inFromServer.readLine();
        		}
        	}
        }
	} catch(Exception e) {
    	e.printStackTrace();
    }
      //}
}
}
