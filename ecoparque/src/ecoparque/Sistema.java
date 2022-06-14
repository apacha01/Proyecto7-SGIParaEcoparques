
package ecoparque;

import static clasesAuxiliares.Constantes.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Agustín Pacheco
 */
public class Sistema implements Serializable {
    private ArrayList<Empleado> empleados;
    private ArrayList<Especie> especies;
    private ArrayList<Habitat> habitats;
    //private ArrayList<Zona> zonas;
    //private ArrayList<Intinerario> ints;
    
    public Sistema() {
        empleados = new ArrayList<>();
        especies = new ArrayList<>();
        habitats = new ArrayList<>();
        //zonas = new ArrayList<>();
        //ints = new ArrayList<>();
    }
    
    /**
     *
     * @return empeleados
     * Devuelve el arraylist de empleados en el sistema
     */
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     *
     * @param empleados arraylist de empleados que se quiere cargar
     */
    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    /**
     *
     * @return array list de especies en el sistema
     */
    public ArrayList<Especie> getEspecies() {
        return especies;
    }

    /**
     *
     * @param especies arrarlist para setear las especies del sistema
     */
    public void setEspecies(ArrayList<Especie> especies) {
        this.especies = especies;
    }

    public ArrayList<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(ArrayList<Habitat> habitats) {
        this.habitats = habitats;
    }
    
    /**
     *
     * @param nomUsuario nombre de usuario del que intenta ingresar
     * @param contra contraseña del usuario que intenta ingresar
     * @return el empleado si emcontro, null si no esta.
     */
    public Empleado existeEmpleado(String nomUsuario, String contra){
        for (Empleado emp: empleados) {
            if (emp.getUsuario().equals(nomUsuario) && emp.getContra().equals(contra)){
                return emp;
            }
        }
        return null;
    }
    
    public Empleado existeEmpleado(String nomUsuario){
        for (Empleado emp: empleados) {
            if (emp.getUsuario().equals(nomUsuario)){
                return emp;
            }
        }
        return null;
    }
    
    public void eliminarEmpleado(Empleado e){
        if (e == null) return;
        empleados.remove(e);
    }
    
    public Cuidador existeCuidador(String nomUsuario){
        ArrayList<Cuidador> cuidadores = obtenerCuidadores();
        
        for (Cuidador cuidador : cuidadores) {
            if (cuidador.getUsuario().equals(nomUsuario)) {
                return cuidador;
            }
        }
        
        return null;
    }
    
    public ArrayList<Cuidador> obtenerCuidadores(){
        ArrayList<Cuidador> cuidadores = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (empleado instanceof Cuidador) {
                cuidadores.add((Cuidador)empleado);
            }
        }
        return cuidadores;
    }
    
    /**
     * Muestra los empleados en el sistema por consola
     */
    public void mostrarEmpleados(){
        System.out.println(SEPARADOR_MEDIO + "Empleados" + SEPARADOR_MEDIO);
        for (int i = 0; i < empleados.size(); i++) {
            empleados.get(i).mostrarDatos();
            System.out.println(SEPARADOR);
        }
    }
    
    /**
     * Muestra las especies en el sistema por consola
     */
    public void mostrarEspecies() {
        System.out.println(SEPARADOR_MEDIO + "Especies" + SEPARADOR_MEDIO);
        if (especies.isEmpty()) {
            System.out.println("No hay especies registradas en el sistema.");
        } else {
            for (int i = 0; i < especies.size(); i++) {
                especies.get(i).mostrarDatos();
                System.out.println(SEPARADOR);
            }
        }
    }
    
    /**
     * Muestra los habitats en el sistema por consola
     */
    public void mostrarHabitats (){
        System.out.println(SEPARADOR_MEDIO + "Habitats" + SEPARADOR_MEDIO);
        if (habitats.isEmpty()) {
            System.out.println("No hay habitats registradas en el sistema.");
        } else {
            for (int i = 0; i < habitats.size(); i++) {
                habitats.get(i).mostrarDatos();
                System.out.println(SEPARADOR);
            }
        }
    }
    
    /**
     * Muestra las zonas en el sistema por consola
     */
    public void mostrarZonas (){
//        System.out.println(SEPARADOR_MEDIO + "Zonas" + SEPARADOR_MEDIO);
//        if (zonas.isEmpty()) {
//            System.out.println("No hay zonas registradas en el sistema.");
//        } else {
//            for (int i = 0; i < zona.size(); i++) {
//              zonas.get(i).mostrarDatos();
//              System.out.println(SEPARADOR);
//            }
//        }
    }
    
    /**
     * Muestra los intinerarios en el sistema por consola
     */
    public void mostrarIntinerarios (){
//        System.out.println(SEPARADOR_MEDIO + "Intinerarios" + SEPARADOR_MEDIO);
//        if (ints.isEmpty()) {
//            System.out.println("No hay intinerarios registradas en el sistema.");
//        } else {
//            for (int i = 0; i < ints.size(); i++) {
//              ints.get(i).mostrarDatos();
//              System.out.println(SEPARADOR);
//            }
//        }
    }
    
    /**
    *  @param a es el nombre del archivo del que se deserializa 
     * @return sistema que deserializo del archivo
    */
    public Sistema deSerializar(String a) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(a);
        ObjectInputStream o = new ObjectInputStream(f);
        Sistema s = (Sistema) o.readObject();
        o.close();
        f.close();
        return s;
    }

    /**
    *  @param a es el nombre del archivo en el que se serializa 
    */
    public void serializar(String a) throws IOException {
        FileOutputStream f = new FileOutputStream(a);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }
}
