
package clasesAuxiliares;

import static clasesAuxiliares.Constantes.*;

/**
 *
 * @author Agustín Pacheco
 */
public class Vegetacion {
    
    private int veg;

    public Vegetacion(int veg){
        if(isVegetacion(veg)) this.veg = veg;
        else { System.err.println("Error: esa vegetación no existe."); veg = 0;}
    }
    
    /**
     * Get the value of veg
     *
     * @return the value of veg
     */
    public int getVeg() {
        return veg;
    }

    /**
     * Set the value of veg
     *
     * @param veg new value of veg
     */
    public void setVeg(int veg) {
        this.veg = veg;
    }
    
    public boolean isVegetacion(int i){
        return (i == PASTIZAL || i == BOSQUE || i == SABANA || i == DESIERTO);
    }
    
    public static String toStringVegetacion(int veg){
        switch(veg){
            case PASTIZAL: return "Pastizal";
            case BOSQUE: return "Bosque";
            case SABANA: return "Sabana";
            case DESIERTO: return "Desierto";
            default: return "";
        }
    }
}
