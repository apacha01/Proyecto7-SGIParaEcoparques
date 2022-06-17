
package ecoparque;

import static clasesAuxiliares.Constantes.*;
import clasesAuxiliares.Vegetacion;
import clasesAuxiliares.Continente;
import clasesAuxiliares.Clima;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Agustín Pacheco
 * Clase para los habitats del ecoparque.
 */
public class Habitat implements Serializable{
    
    private String nom;
    private Clima clima;
    private Vegetacion vegPredominante;
    private ArrayList<Continente> conts;

    
    public Habitat(String nom, Clima clima, Vegetacion vegPredominante, ArrayList<Continente> cont) {
        this.nom = nom;
        this.clima = clima;
        this.vegPredominante = vegPredominante;
        this.conts = cont;
    }
    
    public Habitat(String nom, Clima clima, Vegetacion vegPredominante, Continente cont) {
        this.nom = nom;
        this.clima = clima;
        this.vegPredominante = vegPredominante;
        conts = new ArrayList<>();
        conts.add(cont);
    }

    public void agregarContinente(Continente c){
        conts.add(c);
    }
    
    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getClima() {
        return clima.getClimaActual();
    }

    public int getVegPredominante() {
        return vegPredominante.getVeg();
    }

    public ArrayList<Continente> getConts() {
        return conts;
    }

    public int getIthCont(int i){
        if (conts.isEmpty()) return 0;
        return conts.get(i).getContinente();
    }
    
    public void mostrarDatos(){
        System.out.println(SEPARADOR);
        System.out.println("Nombre del habitat: " + nom);
        System.out.println("Clima actual del habitat: " + clima.toStringClima(clima.getClimaActual()));
        System.out.println("Tipo de vegetacóión del habitat" + vegPredominante.toStringVegetacion(vegPredominante.getVeg()));
        System.out.println("Esta especie se encuentra en los continentes:");
        for (int i = 0; i < conts.size(); i++) {
            System.out.println("\t" + conts.get(i).toStringContinente(i));
        }
    }
    
}
