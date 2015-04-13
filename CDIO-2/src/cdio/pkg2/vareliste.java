/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdio.pkg2;

/**
 *
 * @author Rami
 */
public class vareliste {
private boolean OperatørNr;
    
    
    int vareNummer;
    String vareNavn;
    int weight;

    @Override
    public String toString() {
        return "varenummer:" + vareNummer + " varenavn:" + vareNavn + " vægt:" + weight;
    }
    
    
    
        void OperatørNr(String adgangskode) {
        if ("12".equals(adgangskode)) {
            OperatørNr = true;
            System.out.println("Operatør aktiveret");

        } else {
            OperatørNr = false;
            System.out.println("Operatør deaktiveret");
        }
    }

    public boolean OperatørNr() {
        return OperatørNr;
    }
}