/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdio.pkg2;

/**
 *
 * @author Emre kayhan
 */



public class OperatørNr {
    
    private boolean OperatørNr;
    
    void OperatørNr(String adgangskode) {
        if ("1234".equals(adgangskode)) {
            OperatørNr = true;
            System.out.println("Operatør aktiveret");
        } else {
            OperatørNr = false;
            System.out.println("Operatør deaktiveret");
        }
    }
}
