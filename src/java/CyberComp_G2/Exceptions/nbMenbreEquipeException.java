/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Exceptions;

/**
 *
 * @author vivi
 */
public class nbMenbreEquipeException extends Exception {

    /**
     * Creates a new instance of <code>nbMenbreEquipeException</code> without
     * detail message.
     */
    public nbMenbreEquipeException() {
    }

    /**
     * Constructs an instance of <code>nbMenbreEquipeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public nbMenbreEquipeException(int nbMenbre) {
        super("Nombre de membre de l'equipe  non correcte (<2) : nbMembre ="+nbMenbre);
    }
}
