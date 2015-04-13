/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdio.pkg2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rami
 */
public class Testvareliste {

    public static void main(String[] arg) throws IOException {
          vareliste login = new vareliste();
           while (true) {
          System.out.println("Indtast venligt dit operatør nummer");
                    Scanner indtastning = new Scanner(System.in);
                    String kode = indtastning.next();
                    login.OperatørNr(kode); // kode for at logge ind som montør
           
    if (login.OperatørNr()==true) {
        Scanner tastatur = new Scanner(System.in);
     
     System.out.println("Velkommen til Testvareliste Build 1.01");
        System.out.println("Indtastning af skandinaviske bogstaver, ae, oe");
        System.out.println();
        
                System.out.println("-----------------------------------------------");
                System.out.println("Indtast det ønskede varenavn: ");
                String input = tastatur.nextLine().toLowerCase();
                //tastatur.nextLine();
        
        ArrayList<vareliste> vareTypeListe;
        vareTypeListe = new ArrayList<vareliste>();
        boolean fundet = false;

        BufferedReader ind = new BufferedReader(new FileReader("store.txt"));
        String linje = ind.readLine();
        int vareNummer = 0;
        String vareNavn = null;
        int weight = 0;
        while (linje != null) {
            String[] bidder = linje.split(",");     // opdel i bidder efter komma
            vareNummer = Integer.parseInt(bidder[0]);
            vareNavn = bidder[1]; // brug første bid
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
                        System.out.println("works");
                        fundet = true;
                        return;
                    }
            }
            }
        }
            if(!fundet){
                System.out.println("it doesnt ");
                }
    }
    
    else {
        System.out.println("Venligst indtast gyldig operatør nummer");
    }
    
}
}
}
