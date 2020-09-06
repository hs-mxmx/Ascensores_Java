package Server;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.Random;

public class Ascensor extends Thread{
    /* Variables */
    private final Random rand = new Random();
    private final int ascensor_maximo = 8;
    private int total_personas_montadas = 0;
    private int total_personas_bajadas = 0;
    private double tiempo_persona;
    private final int tiempo_maximo_divisible = 1000000000;
    private final ArrayList <String> ascensor;
    private String nombre = "Asc";
    private Lock lock = new ReentrantLock();
    private final Condition libreAscensor = lock.newCondition();
    private final Condition comprobarAscensor = lock.newCondition();
    private String id_persona;
    private int ascensor_salida;
    private int tiempo_llegada_maximo = 2000;
    private int tiempo_llegada_minimo = 500;
    private Planta planta_destino = null;
    private Planta planta_actual;
    
    /* Ascensor */
    public Ascensor(int numero, Planta planta){
        this.nombre = nombre + numero;
        ascensor = new ArrayList<>();
        this.planta_actual = planta;
    }
    
    /* Montar Ascensor */
    public String montarAscensor(String persona){
        try{
            lock.lock();
            while(ascensor.size() >=  ascensor_maximo){
                try{
                    comprobarAscensor.await();
                }catch(Exception ie){
            }
        }
            total_personas_montadas += 1;
            id_persona = persona;
            ascensor.add(id_persona);

            if(ascensor.size() == ascensor_maximo){
                // Interfaz
            }else{
                // Interfaz
            }

            // SetText
            System.out.println("[+]" + id_persona + " ha montado en el ascensor " + nombre + "|| TOTAL: " + total_personas_montadas);

            // Times
            tiempo_persona = (System.nanoTime());
            // tiempo_persona = (tiempo_persona - belt_time);
            tiempo_persona = (tiempo_persona/ascensor_maximo);
            libreAscensor.signalAll();
            
            return nombre;
            
        }finally{
            lock.unlock();
        }
    }
    
    
    /* Salir del Ascensor */
    public String salirAscensor(String persona){
        try{
            lock.lock();
            while((ascensor.isEmpty() && (total_personas_montadas == total_personas_bajadas)) || total_personas_montadas == 0){
                try{
                    libreAscensor.await();
                }catch(Exception ie){}
            }
            salirAleatorio(ascensor);
            if(ascensor.size() == ascensor_maximo){
                // Interfaz
            }else{
                // Interfaz
            }

            // Variables 
            total_personas_montadas -= 1;
            total_personas_bajadas += 1;

            // SetText
            System.out.println("[!]" + id_persona + " ha bajado en el ascensor " + nombre + "|| TOTAL: " + total_personas_montadas);

            // Tiempos

            comprobarAscensor.signalAll();
            return id_persona;
        
        }finally{
           lock.unlock();
        }
    }
    
    /**
     * Metodo para generar un abandono aleatorio de alguna persona
     * @return 
     */
    private String salirAleatorio(ArrayList <String> ascensor){
        int ascensor_total = ascensor.size()-1;
        if(!ascensor.isEmpty()){
            ascensor_salida = rand.nextInt((ascensor_total-0)+1)+0;
            id_persona = ascensor.get(ascensor_total-ascensor_salida);
            ascensor.remove(ascensor_total-ascensor_salida);    
        }
        return "";
    }
    
    /* Mover ascensor */
    public void moverAscensor(Planta planta_destino, String nombre){
        try{
            System.out.println("(" + nombre + ") Monta en el ascensor " + this.nombre + " hacia la planta " +  planta_destino.get_nombre());
            sleep((rand.nextInt(tiempo_llegada_maximo - tiempo_llegada_minimo)+ 1) + tiempo_llegada_minimo);
        }catch(InterruptedException e){}
        montarAscensor(nombre);
        // Se baja del ascensor
        try{
            System.out.println("(" + nombre + ") Baja del ascensor " + this.nombre + " hacia la planta " +  planta_destino.get_nombre());
            sleep((rand.nextInt(tiempo_llegada_maximo - tiempo_llegada_minimo)+ 1) + tiempo_llegada_minimo);
        }catch(InterruptedException e){}
        salirAscensor(nombre);
    }
        
    /* Get Nombre */
    public String get_nombre(){
        return nombre;
    }
    
    /* Get Planta Actual */
    public Planta get_planta(){
        return planta_actual;
    }
    
    /* Cargar personas */
    public void cargarPersonas(Planta planta_destino, String nombre){
        // Monta en el ascensor
        this.planta_destino = planta_destino;
        this.id_persona = nombre;    
    }
    
    /* Descargar personas */
    public void descargarPersonas(){
        // Monta en el ascensor
        this.planta_destino = null;
        this.id_persona = null;    
    }
    
        /* Run */
    public void run(){
            if(planta_destino != null && id_persona != null){
                // System.out.println("(" + nombre + ") Comprobación de personal con gente... PLANTA: (" + planta_destino.get_nombre() + ") PERSONA: (" + id_persona + ")"); 
                moverAscensor(planta_destino, id_persona);
                descargarPersonas();
        }
    }
}
