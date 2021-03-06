
package ecoparque;

import static clasesAuxiliares.Constantes.*;
import static clasesAuxiliares.InOut.*;
import clasesAuxiliares.Clima;
import clasesAuxiliares.Vegetacion;
import clasesAuxiliares.Continente;
import clasesAuxiliares.Duracion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Agustín Pacheco
 */
public class Administrador extends Empleado {
    
    /**
     *
     * @param usuario
     * @param contra
     * @param nombre
     * @param direccion
     * @param telefono
     * @param fechaIngreso
     */
    public Administrador(String usuario, String contra, String nombre, String direccion, String telefono, Date fechaIngreso) {
        super(usuario, contra, nombre, direccion, telefono, fechaIngreso);
    }
    
    /**
     *
     */
    @Override
    public void mostrarDatos(){
        printLine("Tipo de usuario: ADMINISTRADOR");
        super.mostrarDatos();
    }
    
    /**
     *
     * @param s
     * @return
     */
    @Override
    public boolean ingresar(Sistema s) {
        boolean seguir = true;
        int opc;
        
        while(seguir){
            this.printMenu();
            opc = leerInt(CANT_OPC_MENU_ADMIN);
            seguir = procesarOpcion(opc, s);
        }
        
        return true;
    }
    
    /**
     *
     */
    @Override
    public void printMenu() {
        printLine("\n" + SEPARADOR_MEDIO + "MENU DE ADMINISTRADOR" + SEPARADOR_MEDIO);
        super.printMenu();
        printLine(DAR_ALTA_EMPLEADO + ". Dar de alta un empleado.");
        printLine(DAR_BAJA_EMPLEADO + ". Dar de baja un empleado.");
        printLine(DAR_ALTA_ESPECIE + ". Dar de alta una especie.");
        printLine(DAR_BAJA_ESPECIE + ". Dar de baja una especie.");
        printLine(REGISTRAR_ZONA + ". Registrar zona.");
        printLine(INHABILITAR_ZONA + ". Inhabilitar zona.");
        printLine(REGISTRAR_HABITAT + ". Registrar habitat.");
        printLine(INHABILITAR_HABITAT + ". Inhabilitar habitat.");
        printLine(REGISTRAR_INTINERARIO + ". Registrar intinerario.");
        printLine(INHABILITAR_INTINERARIO + ". Inhabilitar intinerario.");
        printLine(ASIGNAR_ESP_CUIDADOR + ". Asignar una especie a un cuidador.");
        printLine(REMOVER_ESP_CUIDADOR + ". Remover una especie a un cuidador.");
        printLine(ASIGNAR_INT_GUIA + ". Asignar un intinerario a un guia.");
        printLine(REMOVER_INT_GUIA + ". Remover un intinerario a un guia.");
        printLine(LISTAR_EMPLEADOS_X_ANTIGUEDAD + ". Listar empleados por antiguedad.");
    }

    private boolean procesarOpcion(int op, Sistema s){
        boolean seguir = true;
        switch(op){
            //ASD modificar cada cosa???
            case Integer.MIN_VALUE: printError("Ocurrió un error al elegir la opción del menu."); break;
            case SALIR_MENU: seguir = false; break;
            case CONSULTAR_DATOS: consultarDatos(s); break;
            case DAR_ALTA_EMPLEADO: darAltaEmp(s); break;
            case DAR_BAJA_EMPLEADO: darBajaEmp(s); break;
            case DAR_ALTA_ESPECIE: darAltaEsp(s); break;
            case DAR_BAJA_ESPECIE: darBajaEsp(s); break;
            case REGISTRAR_ZONA: registrar(STRING_ZONA, s); break;
            case INHABILITAR_ZONA: inhabilitar(STRING_ZONA, s); break;
            case REGISTRAR_HABITAT: registrar(STRING_HABITAT, s); break;
            case INHABILITAR_HABITAT: inhabilitar(STRING_HABITAT, s); break;
            case REGISTRAR_INTINERARIO: registrar(STRING_INTINERARIO, s); break;
            case INHABILITAR_INTINERARIO: inhabilitar(STRING_INTINERARIO, s); break;
            case ASIGNAR_ESP_CUIDADOR: asignarEspecieCuidador(s); break;
            case REMOVER_ESP_CUIDADOR: removerEspecieCuidador(s); break;
            case ASIGNAR_INT_GUIA: asignarIntinerarioGuia(s); break;
            case REMOVER_INT_GUIA: removerIntinerarioGuia(s); break;
            case LISTAR_EMPLEADOS_X_ANTIGUEDAD: listarXantiguedad(s); break;
            default: printError("esa opción no existe"); break;
        }
        
        if (op >= 3 && op <= CANT_OPC_MENU_ADMIN-1) {
            try { s.serializar(NOMBRE_ARCHIVO); }
            catch (IOException e) { e.printStackTrace(); }
        }
        
        return seguir;
    }
    
    /**
     *
     * @param s
     */
    @Override
    public void consultarDatos(Sistema s) {
        printLine("\n");
        
        //MUESTRO EMPLEADOS
        s.mostrarEmpleados();
        
        //MUESTRO ESPECIES
        s.mostrarEspecies();
        
        //MUESTRO ZONAS
        s.mostrarZonas();
        
        //MUESTRO HABITATS
        s.mostrarHabitats();
        
        //MUESTRO INTINERARIOS
        s.mostrarIntinerarios();
        
    }

    private void darAltaEmp(Sistema s) {
        int opc;
        Empleado e = null;
        String _nomUsuario;
        String _contra;
        String _nombre;
        String _direccion;
        String _telefono;
        Date _fechaIngreso;
        boolean existeEmpleado = false;
        
        printLine("\n¿Qué tipo de empleado desea ingresar al sistema?\n");
        printLine("1. Cuidador\n2. Guía\n3. Salir");
        opc = leerInt(3);
        
        if (opc == 3) return;
        
        do {
            if (existeEmpleado) {
                printError("Ese empleado ya esta registrado en el sistema, no se puede repetir");
            }
            _nomUsuario = pedirUsuario();
            existeEmpleado = true;
        } while (s.existeEmpleado(_nomUsuario) != null);
        _contra = pedirContra();
        _nombre = pedirNombreEmpleado();
        _direccion = pedirDireccion();
        _telefono = pedirTelefono();
        _fechaIngreso = new Date();
        
        switch(opc){
            case 1:
                e = new Cuidador(_nomUsuario,_contra,_nombre,_direccion,_telefono, _fechaIngreso);
                break;
            case 2:
                e = new Guia(_nomUsuario,_contra,_nombre,_direccion,_telefono, _fechaIngreso);
                break;
            case 3: return; //NO DEBERIA SER NECESARIO
            default: printError("Error al dar de alta empleado"); break;
        }
        if (e != null) s.agregarEmpleado(e);
    }

    private void darBajaEmp(Sistema s) {
        String empBaja;
        Empleado e = null;
        
        while(true){
            printLine("\n¿Qué empleado desea dar de baja?");
            s.mostrarEmpleados();
            print("Ingrese el nombre de usuario del empleado que desea elminiar (0 para salir): ");
            empBaja = leerString();
            if (empBaja.equals("0")) break;
            e = s.existeEmpleado(empBaja);
            if (e != null) {
                if (confirmarDecision()) {
                    break;
                }
            }
        }
        
        s.eliminarEmpleado(e);
        
    }

    private void darAltaEsp(Sistema s) {
        String nom;
        String nomCient;
        String desc;
        ArrayList<Cuidador> cuidadores;
        boolean hay = false;
        
        nom = pedirNombreColoquialEspecie();
        
        do {
            if (hay) {
                printError("Ese nombre cientifico ya existe en el sistema, no se puede repetir");
            }
            nomCient = pedirNombreCientificoEspecie();
            hay = true;
        } while (s.existeEspecie(nomCient) != null);
        
        desc = pedirDescripcion();
        
        cuidadores = pedirCuidadores(s);
        
        if (cuidadores.isEmpty()) {
            printError("No se puede dar de alta una especie sin un empleado que la cuide");
            return;
        }
        
        Especie nuevaEspecie = new Especie(nom,nomCient,desc,cuidadores);
        
        //GUARDO QUE ESPECIE CUIDA CADA EMPLEADO EN LOS CUIDADORES.
        for (Cuidador cuidador : cuidadores) {
            cuidador.tomarEspecie(nuevaEspecie);
        }
        
        //PARA ZONAS
        print("¿Existe alguna zona en la que se encuentre esta especie? (s/n): ");
        hay = leerBoolean();
        if (hay) {
            Zona zona = pedirZona(s);
            if (zona != null) {
                nuevaEspecie.asignarZona(zona);
                zona.agregarEspecie(nuevaEspecie);
            }
        }
        
        //PARA HABITATS
        print("¿Existe algun habitat en el que se encuentre esta especie? (s/n): ");
        hay = leerBoolean();
        if (hay) {
            ArrayList<Habitat> habitats = pedirHabitats(s);
            if (habitats != null && !habitats.isEmpty()) nuevaEspecie.asignarHabitats(habitats);
        }
        
        s.agregarEspecie(nuevaEspecie);
    }

    private void darBajaEsp(Sistema s) {
        String espBaja;
        Especie e = null;
        
        while(true){
            printLine("¿Qué especie desea dar de baja?");
            s.mostrarEspecies();
            print("Ingrese el nombre cientifico de la especie que desea elminiar (0 para salir): ");
            espBaja = leerString();
            
            if (espBaja.equals("0")) break;
            
            e = s.existeEspecie(espBaja);
            if (e != null) {
                if (confirmarDecision()) {
                    break;
                }
            }
        }
        
        s.eliminarEspecie(e);
    }

    private void registrar(String s, Sistema sis) {
        boolean tieneEspecie = false;
        switch (s) {
            case STRING_ZONA:
                String nomZona;
                Double extZona;
                
                print("Ingrese el nombre de la zona: ");
                nomZona = leerString();
                
                print("Extensión de la zona en m2: ");
                //USO LA VARIABLE tieneEspecie PARA NO CREAR OTRA, EL NOMBRE NO COINCIDE CON LA FUNCION QUE CUMPLE
                do {
                    if (tieneEspecie) {
                        printError("La extension no puede ser menor a 0");
                    }
                    extZona = leerDouble();
                    tieneEspecie = true;
                } while (extZona < 0);
                
                
                print("¿Esta zona tiene especies? (s/n): ");
                tieneEspecie = leerBoolean();
                
                if (tieneEspecie){
                    ArrayList<Especie> e = pedirEspecies(sis);
                    Zona z = new Zona(nomZona,extZona,e);
                    sis.agregarZona(z);
                }
                else { sis.agregarZona(new Zona(nomZona,extZona)); }
                break;
                
            case STRING_HABITAT:
                String nom;
                Habitat h;
                Clima c;
                Vegetacion v;
                ArrayList<Continente> conts;
                ArrayList<Especie> esps;
                
                print("Ingrese el nombre del habitat: ");
                nom = leerString();
                
                c = pedirClima();
                v = pedirVegetacion();
                conts = pedirContinentes();
                h = new Habitat(nom,c,v,conts);
                
                print("¿Hay especies en este habitat? (s/n): ");
                tieneEspecie = leerBoolean();
                if (tieneEspecie) {
                    esps = pedirEspecies(sis);
                
                    for (Especie esp : esps) {
                        esp.asignarHabitat(h);
                    }
                }
                
                sis.agregarHabitat(h);
                break;
                
            case STRING_INTINERARIO:
                String codigo;
                Duracion duracion;
                double longitud;
                int maxVisitas, numEspeciesVisita;
                
                codigo = pedirCodigoIntinerario();
                duracion = pedirDuracionIntinerario();
                longitud = pedirLongitudIntinerario();
                maxVisitas = pedirMaximoVisitas();
                numEspeciesVisita = pedirNumEspeciesVisita();
                
                Intinerario i = new Intinerario(codigo,duracion,longitud,maxVisitas,numEspeciesVisita);
                
                sis.agregarIntinerario(i);
                
                break;
            default: printError("Error al registrar, no es ni una zona ni un habitat ni un intinerario."); break;
        }
    }

    private void inhabilitar(String s, Sistema sis) {
        switch (s) {
            case STRING_ZONA:
                String inhabilitarZona;
                Zona z = null;

                while(true){
                    printLine("¿Qué zona desea inhabilitar?");
                    sis.mostrarZonas();
                    print("Ingrese el nombre de la zona que desea elminiar (0 para salir): ");
                    inhabilitarZona = leerString();

                    if (inhabilitarZona.equals("0")) break;

                    z = sis.existeZona(inhabilitarZona);
                    
                    if (z != null) {
                        if (confirmarDecision()) {
                            break;
                        }
                    }
                    else { printError("Esa zona no existe"); }
                }

                if (z != null) sis.eliminarZona(z);
                
                break;

            case STRING_HABITAT:
                String inhabilitarHabitat;
                Habitat h = null;

                while(true){
                    printLine("¿Qué habitats desea inhabilitar?");
                    sis.mostrarHabitats();
                    print("Ingrese el nombre del habitat que desea elminiar (0 para salir): ");
                    inhabilitarHabitat = leerString();

                    if (inhabilitarHabitat.equals("0")) break;

                    h = sis.existeHabitat(inhabilitarHabitat);
                    
                    if (h != null) {
                        if (confirmarDecision()) {
                            break;
                        }
                    }
                    else{
                        printError("Ese habitat no existe");
                    }
                }

                if (h != null) sis.eliminarHabitat(h);
                
                break;
                
            case STRING_INTINERARIO:
                String inhabilitarCod;
                Intinerario i = null;
                
                while(true){
                    printLine("¿Qué intinerario desea inhabilitar?");
                    sis.mostrarIntinerarios();
                    print("Ingrese el codigo del intinerario que desea elminiar (0 para salir): ");
                    inhabilitarCod = leerString();

                    if (inhabilitarCod.equals("0")) break;

                    i = sis.existeIntinerario(inhabilitarCod);
                    
                    if (i != null) {
                        if (confirmarDecision()) {
                            break;
                        }
                    }
                }

                if (i != null) sis.eliminarIntinerario(i);
                
                break;
                
            default: printError("Error al inhabilitar, no es ni una zona ni un habitat ni un intinerario."); break;
        }
    }

    private void asignarEspecieCuidador(Sistema s) {
        ArrayList<Especie> e;
        Cuidador c;
        
        e = pedirEspecies(s);
        if (e == null || e.isEmpty()) return;
        
        c = pedirCuidador(s);
        if (c == null) return;
        
        c.tomarEspecies(e);
    }

    private void removerEspecieCuidador(Sistema s) {
        Cuidador c;
        Especie e;
        String esp;
        boolean quitarOtra;
        
        do {
            c = pedirCuidador(s);

            if (c != null) {
                do {
                    c.mostrarEspeciesCuidadas();
                    print("Ingrese el nombre cientifico de la especie que quiera remover (0 para salir): ");
                    esp = leerString();

                    e = s.existeEspecie(esp);

                    if (esp.equals("0")) break;

                    if (e != null) {
                        c.quitarEspecie(e);
                        e.quitarCuidador(c);
                    }
                    else{
                        printError("Esa especie no existe");
                    }
                    
                    print("¿Quiere remover otra especie de este cuidador? (s/n): ");
                    quitarOtra = leerBoolean();
                } while (quitarOtra);
                
                print("¿Quiere remover una especie de otro cuidador? (s/n): ");
                quitarOtra = leerBoolean();
            }
            else {
                break;
            }
        } while (quitarOtra);
        
    }

    private void asignarIntinerarioGuia(Sistema s) {
        Intinerario i;
        Guia g;
        
        i = pedirIntinerario(s);
        if (i == null) return;
        
        g = pedirGuia(s);
        if (g == null) return;

        g.tomarIntinerario(i);
    }

    private void removerIntinerarioGuia(Sistema s) {
        Guia g;
        Intinerario i;
        
        g = pedirGuia(s);
        if (g == null) return;
        if (g.tieneIntinerarios()){
            printError("Este guia no tiene intinerarios");
            return;
        }
        
        i = pedirIntinerario(s);
        if (i == null) return;
        
        g.quitarIntinerario(i);
        
    }
    
    private void listarXantiguedad(Sistema s) {
        //NUEVA LISTA PARA NO MODIFICAR LA EXISTENTE, SOLO PIDE MOSTRAR POR ANTIGUEDAD NO ORDENAR
        ArrayList<Empleado> e = s.getEmpleados();
        
        Collections.sort(e, new Comparator<Empleado>() {
            @Override
            public int compare(Empleado e1, Empleado e2) {
                    return (e1.getFechaIngreso()).compareTo((e2.getFechaIngreso()));
            }
        });
        
        printLine("\n" + SEPARADOR_MEDIO + "Empleados" + SEPARADOR_MEDIO);
        for (Empleado e1 : e) {
            e1.mostrarDatos();
            printLine(SEPARADOR);
        }
    }

    @Override
    public void eliminarme() {
        printError("No se puede dar de baja al administrador");
    }
    
}
