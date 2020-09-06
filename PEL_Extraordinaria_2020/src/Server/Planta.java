/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;


public class Planta {
    /* Variables */
    private Ascensor ascensor;
    private String nombre = "P"; 
    private String nombre_default = "P";
    private final Random rand = new Random();
    private int tiempo_llegada_ascensor = 500;
    private int total_plantas = 21;
    
    public Planta(int numero, Ascensor ascensor){
        this.nombre = nombre + numero;
        this.ascensor = ascensor;
    }
    
    
    /* Comprobar ascensor */
    public Ascensor comprobarAscensor(Ascensor ascensor){
        return ascensor;
    }
    
    /* Llamar ascensor */
    public Planta llamarAscensor(Ascensor ascensor_llamada, String id_persona, Planta planta_destino){
        String nombre_planta_destino = planta_destino.get_nombre();
        // Caso en el que coincida la planta con el destino
        /*
        while(planta_destino.get_nombre().equals(nombre)){
            System.out.println("Planta destino : " + planta_destino.get_nombre() + " Planta actual: "+ nombre);
            nombre = nombre_default + rand.nextInt(total_plantas);
        }
        */
        System.out.println(id_persona + " se encuentra en la planta (" + nombre + "), ha solicitado el ascensor (" + ascensor_llamada.get_nombre() + ")"
         + " para ir a la planta (" + nombre_planta_destino + ")");
        // Si el ascensor no se encuentra en la planta, 
        
        // System.out.println(ascensor);
        if (ascensor == null || (ascensor_llamada.get_nombre() == null ? ascensor.get_nombre() != null : !ascensor_llamada.get_nombre().equals(ascensor.get_nombre()))){
            try{
                System.out.println("Llamando al ascensor " + ascensor_llamada.get_nombre() + java.time.LocalTime.now());
                sleep(tiempo_llegada_ascensor);
            }catch(InterruptedException e){}
        }
        return planta_destino;
    }
    
    
    /* Return planta */
    public String get_nombre(){
        return nombre;
    }
    
    /* Set ascensor */
    public void set_ascensor(Ascensor ascensor_creado){
        this.ascensor = ascensor_creado;
    }
    
    
    /* Return ascensor en planta */
    public String get_nombre_ascensor(){
        return ascensor.get_nombre();
    }
    
}
