package Server;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.Random;

public class Ascensor{
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
    
    /* Ascensor */
    public Ascensor(int numero){
        this.nombre = nombre + numero;
        ascensor = new ArrayList<>();
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
        
    /* Get Nombre */
    public String get_nombre(){
        return nombre;
    }
}
