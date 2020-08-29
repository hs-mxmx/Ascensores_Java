package Server;
import java.util.*;

public class Persona extends Thread{
    /* Variables */
    private String nombre = "P"; 
    private final Random rand = new Random();
    private int tiempo_llegada_maximo = 2000;
    private int tiempo_llegada_minimo = 500;
    private final Ascensor ascensor;
    private final Planta planta;
    private Planta planta_destino;
    
    /* Persona */
    public Persona(int numero, Ascensor ascensor, Planta planta, Planta planta_destino){
        this.nombre = nombre + numero;
        this.ascensor = ascensor;
        this.planta = planta;
        this.planta_destino = planta_destino;
    }
    
    
    /* Get Nombre */
    public String get_nombre(){
        return nombre;
    }
    
    /* Run */
    public void run(){
        planta_destino = planta.llamarAscensor(ascensor, nombre, planta, planta_destino);
        ascensor.cargarPersonas(planta_destino, nombre);
        ascensor.start();
    }
    
}
