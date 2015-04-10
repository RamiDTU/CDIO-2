/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdio.pkg2;

import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 *
 * @author Rami
 */
public class BenytTestvareliste {
    
    public static void main(String[] arg) {
    Testvareliste vareliste = new Testvareliste ();
     Scanner tastatur = new Scanner(System.in);
     
     System.out.println("Velkommen til Testvareliste Build 1.01");
        System.out.println();
        
        while (true) {
                System.out.println("-----------------------------------------------");
                System.out.println("Indtast det ønskede varenavn: ");
                String input = tastatur.nextLine();
                //tastatur.nextLine();
                
                System.out.println(Testvareliste.getvareNavn());
                System.out.println("Balancen er på " + automat.getBalance() + " kroner");
                System.out.println();
                System.out.println("Tast 1 for at indbetale penge");
                System.out.println("Tast 2 for at udskrive din billet");
                System.out.println("Tast 3 for at få returpengene");
                System.out.println();
                System.out.println("Tast 10 for at logge ind som montør");
            
     
            
     
}
