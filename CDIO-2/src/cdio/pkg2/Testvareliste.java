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


/**
 *
 * @author Rami
 */
public class Testvareliste  {

    public static void main(String[] arg) throws IOException {
        
    ArrayList<vareliste> vareTypeListe;
        vareTypeListe = new ArrayList<vareliste>();

        BufferedReader ind = new BufferedReader(new FileReader("store.txt"));
        String linje = ind.readLine();
        int vareNummer = 0;
        String vareNavn = null;
        int weight = 0;
        while (linje
                != null) {
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

        }
    } 
}
