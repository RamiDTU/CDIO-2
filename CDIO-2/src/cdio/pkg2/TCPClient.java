
import java.io.*; 
import java.net.*; 
import java.util.Scanner;
public class TCPClient { 

    public static void main(String argv[]) throws Exception { 
        String sentence, dtext, modifiedSentence, x1, atext1, atext2, atext3, text1, text2, text3;
        int port = 4567;
	String hostname = "localhost"; 
        boolean start = true, Quit = false, rm20 = true;
        
        Scanner sc = new Scanner(System.in);
		
		
		System.out.println();
		System.out.println("Please choose one of the following options: ");
		System.out.println("1. Read");
		System.out.println("2. Tare");
		System.out.println("3. Zero");
		System.out.println("4. Display Text");
		System.out.println("5. Display Weight");
		System.out.println("6. RM20");
		System.out.println("7. Quit");
		
        BufferedReader inFromUser = 
          new BufferedReader(new InputStreamReader(System.in)); 

        Socket clientSocket = new Socket(hostname, port); 

        DataOutputStream outToServer = 
          new DataOutputStream(clientSocket.getOutputStream());
        
        BufferedReader inFromServer = 
                new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream())); 
        
        while(start) {
        	sentence = inFromUser.readLine();
        	if(sentence.length() == 1){
        		
        	System.out.println();
        switch(sentence) {
        
        case "0":
        		System.out.println("Non existing option.");
        		break;
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
                outToServer.writeBytes("Z\r\n");
                modifiedSentence = inFromServer.readLine();
                System.out.println("REPLY FROM SERVER: " + modifiedSentence);
                break;
        case "4":
                System.out.println("Write the new display message: ");
                dtext = inFromUser.readLine();
                outToServer.writeBytes("D " + dtext + "\r\n");
                modifiedSentence = inFromServer.readLine();
                System.out.println("REPLY FROM SERVER: " + modifiedSentence);
                break;
        case "5":
                outToServer.writeBytes("DW\r\n");
                modifiedSentence = inFromServer.readLine();
                System.out.println("REPLY FROM SERVER: " + modifiedSentence);
                break;
        case "6":
        		System.out.println("1. integer");
        		System.out.println("2. alphanumeric");
    			x1 = inFromUser.readLine();
    				if(x1.equals("1")){
    					System.out.println("You have chosen integer");
    					System.out.println("Please write an integer:");
    					text1 = inFromUser.readLine();
    					System.out.println("Please write an integer:");
    					text2 = inFromUser.readLine();
    					System.out.println("Please write an integer:");
    					text3 = inFromUser.readLine();
    					System.out.println();
    					outToServer.writeBytes("RM20 4 " + "\"" + text1 + "\" "+ "\"" + text2 + "\" " + "\"" + text3 + "\"" + "\r\n");
    					modifiedSentence = inFromServer.readLine();
    	                System.out.println("REPLY FROM SERVER: " + modifiedSentence);
    	                modifiedSentence = inFromServer.readLine();
    		            System.out.println("REPLY FROM SERVER: " + modifiedSentence);
    				}
    				else if(x1.equals("2")){
        				System.out.println("You have chosen alphanumeric");
        				System.out.println("Please write something:");
    					atext1 = inFromUser.readLine();
    					System.out.println("Please write something:");
    					atext2 = inFromUser.readLine();
        				if(!atext2.matches("[A-Za-z0-9]+")){
        					while(rm20) {
        					System.out.println("Not a valid input, please try again:");
        					atext2 = inFromUser.readLine();
        						if(atext2.matches("[A-Za-z0-9]+")){
        							rm20 = false;
        						}
        					}
        				}
        				
        				System.out.println("Please write something:");
    					atext3 = inFromUser.readLine();
    					System.out.println();
    					outToServer.writeBytes("RM20 4 " + "\"" + atext1 + "\" "+ "\"" + atext2 + "\" " + "\"" + atext3 + "\"" + "\r\n");
    					modifiedSentence = inFromServer.readLine();
    	                System.out.println("REPLY FROM SERVER: " + modifiedSentence);
    	                modifiedSentence = inFromServer.readLine();
    		            System.out.println("REPLY FROM SERVER: " + modifiedSentence);
    				}
            	break;
        case "7":
                clientSocket.close();
                System.out.println("BYE BYE - Please come again");
                start = false;
                Quit = true;
                break;
        case "8":
        		System.out.println("Non existing option.");
            	break;
        case "9":
            	System.out.println("Non existing option.");
            	break;
        
        }
            	if(Quit == false) {
            		System.out.println();
            		System.out.println("Please choose one of the following options:");
            		System.out.println("1. Read");
            		System.out.println("2. Tare");
            		System.out.println("3. Zero");
            		System.out.println("4. Display Text");
            		System.out.println("5. Display Weight");
            		System.out.println("7. Quit");
            	}
        	}
        	else{
        		System.out.println("Non existing option.");
        		System.out.println();
        		System.out.println("Please choose one of the following options:");
        		System.out.println("1. Read");
        		System.out.println("2. Tare");
        		System.out.println("3. Zero");
        		System.out.println("4. Display Text");
        		System.out.println("5. Display Weight");
        		System.out.println("7. Quit");
        	}
           	}
      	}
        
    }
