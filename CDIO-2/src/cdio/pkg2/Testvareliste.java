/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rami
 */
public class Testvareliste {

	public static void main(String[] arg) throws IOException, Exception {
		int port = 8000, vægt = 0, indexNr = 0;
		String hostname = "localhost", modifiedSentence, linje2 = null;
		boolean run = true;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));

		Socket clientSocket = new Socket(hostname, port);

		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		System.out.println("Connected to scale");

		vareliste List = new vareliste();
		Scanner indtastning = new Scanner(System.in);
		
		BufferedReader ind;

		while (run) {
			System.out.println("Indtast venligst dit operatør nummer");
			String kode = indtastning.next();
			List.OperatørNr(kode); // kode for at logge ind som montÃ¸r

			if (List.OperatørNr() == true) {
				Scanner tastatur = new Scanner(System.in);

				System.out.println("Velkommen til Testvareliste Build 1.01");
				System.out
						.println("Indtastning af skandinaviske bogstaver, ae, oe, aa");
				System.out.println();

				while (run) {
					System.out
							.println("-----------------------------------------------");
					System.out.println("Indtast det ønskede varenavn: ");
					String input = tastatur.nextLine().toLowerCase();
					// tastatur.nextLine();

					ArrayList<vareliste> vareTypeListe;
					vareTypeListe = new ArrayList<vareliste>();
					boolean fundet = false;

					ind = new BufferedReader(new FileReader("store.txt"));
					String linje = ind.readLine();
					int vareNummer = 0;
					String vareNavn = null;
					int weight = 0;

					while (linje != null) {
						String[] bidder = linje.split(","); // opdel i bidder efter komma
						vareNummer = Integer.parseInt(bidder[0]);
						vareNavn = bidder[1]; // brug fÃ¸rste bid
						weight = Integer.parseInt(bidder[2]); // brug andet bid

						vareliste vt = new vareliste();
						vt.vareNavn = vareNavn;
						vt.vareNummer = vareNummer;
						vt.weight = weight;

						vareTypeListe.add(vt);

						linje = ind.readLine();
						System.out.println("Læst: " + vt.toString());
						if (!vareTypeListe.isEmpty()) {
							for (int i = 0; i < vareTypeListe.size(); i++) {
								if (vareTypeListe.get(i).vareNavn.startsWith(input)) {
									indexNr = Integer.parseInt(bidder[0]);
									vægt = Integer.parseInt(bidder[2]);
									System.out.println("Vi fandt den ønskede vare: "+ vareNavn + " " + Integer.parseInt(bidder[2])+ " kg");
									fundet = true;
									run = false;
								}

							}
							if (fundet == true) {
								break;
							}
						}
					}
					if (!fundet) {
						System.out.println("Eksisterer ikke");
					}

				}
			}

			else {
				System.out.println();
			}
		}
		// InstruÃ©r operatÃ¸r om at pÃ¥sÃ¦tte skÃ¥l eller lignende og kvittere
		// bagefter
		System.out.println("Saet en skaal eller lignende paa vaegten.");
		System.out.println("Tryk vilkaarlig tast og enter, naar du er klar til at tera'ere");
		String temp = indtastning.next();
		System.out.println("Vaegten bliver tera'eret");
		outToServer.writeBytes("T\r\n");
		modifiedSentence = inFromServer.readLine();
		System.out.println("REPLY FROM SERVER: " + modifiedSentence);

		System.out.println("Indtast den oensekde maengde af varen i kg:");
		int afvejet = indtastning.nextInt();
		
		String netto = String.valueOf(afvejet);
		outToServer.writeBytes("B " + netto + "\r\n");
		modifiedSentence = inFromServer.readLine();
		System.out.println("REPLY FROM SERVER: " + modifiedSentence);

		System.out.println("Vægten bliver nulstillet.");
		outToServer.writeBytes("Z\r\n");
		modifiedSentence = inFromServer.readLine();
		System.out.println("REPLY FROM SERVER: " + modifiedSentence);

		// Tarer igen
		System.out.println("Vaegten bliver tera'eret");
		outToServer.writeBytes("T\r\n");
		modifiedSentence = inFromServer.readLine();
		System.out.println("REPLY FROM SERVER: " + modifiedSentence);

		outToServer.writeBytes("S\r\n");
		temp = inFromServer.readLine();

		if (temp.equals("0.0")) {
			System.out.println("BRUTTO KONTROL OK");
		} else {
			System.out.println("HVAD FUCK HAR DU GANG I?!?!?!?!?!");
		}

		vægt -= afvejet;
		ind = new BufferedReader(new FileReader("store.txt"));
		while(linje2 != null){
		linje2 = ind.readLine();
		}
		String [] splittet = linje2.trim().split(",");
		String splittet2 = splittet[2];
		System.out.println(splittet2);
		splittet2 = String.valueOf(vægt);
		
		temp = indtastning.next();
		

	}
}
