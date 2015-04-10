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

    int vareNummer;
    String vareNavn;
    int weight;

    @Override
    public String toString() {
        return "varenummer:" + vareNummer + " varenavn:" + vareNavn + " vægt:" + weight;
    }
}
