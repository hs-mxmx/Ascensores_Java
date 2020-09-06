package Server;
import java.util.*;

/**
 *
 * @author dpere
 */
public class PEL_Extraordinaria_2020 {   
    final Random rand = new Random();
    final int total_ascensores = 3;
    final int total_operativos = 2;
    final int planta_inicial = 0;
    final int total_plantas = 21;
    int total_personas = 2;
    ArrayList<Integer> ascensores_operativos_actuales = new ArrayList<>();
    
    
    public PEL_Extraordinaria_2020(){
        final ArrayList<Planta> plantas_creadas = crearPlantas(total_plantas);
        final ArrayList<Ascensor> ascensores = asignarAscensor(total_ascensores, plantas_creadas);
        ArrayList<Ascensor> ascensores_operativos = inicializarAscensores(ascensores, false);
        final int total_operativos = ascensores_operativos.size();
        for(int i = 0; i<total_personas; i++){
            int plantas_totales_origen = rand.nextInt(total_plantas);
            int plantas_totales_destino = rand.nextInt(total_plantas);
            if(i == 1){
                System.out.println("CAMBIANDO");
                ascensores_operativos = inicializarAscensores(ascensores, true);
            }
            Persona persona = new Persona(i, ascensores_operativos.get(rand.nextInt(total_operativos)), 
            plantas_creadas.get(plantas_totales_origen),  plantas_creadas.get(plantas_totales_destino));
            persona.start();
        }
    }

    public static void main(String[] args) {
        PEL_Extraordinaria_2020 Prueba = new PEL_Extraordinaria_2020();
    }
    
    
    /* Crear plantas */
    // Asignamos plantas vacias sin ascensores para su creacion
    public final ArrayList<Planta> crearPlantas(int total){
        ArrayList<Planta> plantas = new ArrayList<>();
        for(int i = 0; i < total; i++){
        plantas.add(new Planta(i,null));
        }
        return plantas;
    }
    
    /* Inicializar ascensores */
    public final ArrayList<Ascensor> inicializarAscensores(ArrayList<Ascensor> ascensores, boolean reemplazo){
        ArrayList<Ascensor> ascensores_operativos = new ArrayList<>();
        int ascensor_seleccionado;
        // Sustituir ascensores operativos
        if(reemplazo){
            while(true){
                ascensor_seleccionado = rand.nextInt(ascensores.size());
                if(!ascensores_operativos_actuales.contains(ascensor_seleccionado)){
                    ascensores_operativos_actuales.add(ascensor_seleccionado);
                    ascensores_operativos.add(ascensores.get(ascensor_seleccionado));
                    ascensores_operativos_actuales.remove(0);
                    ascensores.remove(0);
                    break;
                }
            }
        // Primera asignacion de ascensores
        }else{
            for(int i = 0; i < total_operativos; i++){
                ascensor_seleccionado = rand.nextInt(ascensores.size());
                // Primer ascensor
                if(ascensores_operativos.isEmpty()){
                    ascensores_operativos_actuales.add(ascensor_seleccionado);
                    ascensores_operativos.add(ascensores.get(ascensor_seleccionado));
                }else{
                    // El ascensor asignado ya lo tenemos cubierto
                    while(ascensores_operativos_actuales.contains(ascensor_seleccionado)){
                        ascensor_seleccionado = rand.nextInt(ascensores.size());
                    }
                    ascensores_operativos_actuales.add(ascensor_seleccionado);
                    ascensores_operativos.add(ascensores.get(ascensor_seleccionado));
                }
            }
        }
        System.out.println(ascensores_operativos_actuales);
        return ascensores_operativos;
    }
       
    
    /* Crear ascensores */
    public final ArrayList<Ascensor> asignarAscensor(int total_ascensores, ArrayList<Planta> plantas_creadas){
        ArrayList ascensores = new ArrayList();
        for(int i = 1; i <= total_ascensores; i++){
            int plantas_totales = rand.nextInt(plantas_creadas.size());
            Planta planta = plantas_creadas.get(plantas_totales);
            Ascensor ascensor = new Ascensor(i, planta);
            ascensores.add(ascensor);
            planta.set_ascensor(ascensor);
            System.out.println(planta.get_nombre() + " tiene el ascensor " + planta.get_nombre_ascensor());
            // System.out.println(ascensor.get_nombre() + " se encuentra en la planta " + ascensor.get_planta().get_nombre());
        }
        return ascensores;
    }

}
