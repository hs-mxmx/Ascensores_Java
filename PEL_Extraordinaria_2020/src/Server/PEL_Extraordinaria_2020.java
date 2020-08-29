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
    int total_personas = 0;
    
    
    public PEL_Extraordinaria_2020(){
        final ArrayList<Ascensor> ascensores = crearAscensores(total_ascensores);
        ArrayList plantas_asignadas = asignarAscensor(total_plantas, total_ascensores);
        final ArrayList<Planta> plantas = crearPlantas(ascensores, plantas_asignadas);
        final ArrayList<Ascensor> ascensores_operativos = inicializarAscensores(ascensores);
        final int total_operativos = ascensores_operativos.size();
        Persona persona = new Persona(total_personas, ascensores_operativos.get(rand.nextInt(total_operativos)), 
                plantas.get(rand.nextInt(total_plantas)),  plantas.get(rand.nextInt(total_plantas)));
            persona.start();
        /*
        do{
            total_personas++;
        }while(true);
        */
    }

    public static void main(String[] args) {
        PEL_Extraordinaria_2020 Prueba = new PEL_Extraordinaria_2020();
    }
    
    
    /* Crear ascensores */
    public ArrayList<Ascensor> crearAscensores(int total){
        ArrayList<Ascensor> ascensores = new ArrayList<>();
        for(int i = 1; i <= total_ascensores; i++){
            ascensores.add(new Ascensor(i));
        }
        return ascensores;
    }
    
    /* Inicializar ascensores */
    public ArrayList<Ascensor> inicializarAscensores(ArrayList<Ascensor> ascensores){
        ArrayList<Ascensor> ascensores_operativos = new ArrayList<>();
        // Inicializamos 2 ascensores operativos
        for(int i = 0; i < total_operativos; i++){
            ascensores_operativos.add(ascensores.remove(rand.nextInt(ascensores.size())));
        }
        return ascensores_operativos;
    }
       
    /* Inicializar plantas */
    public ArrayList<Planta> crearPlantas(ArrayList<Ascensor> asc, ArrayList plantas_asignadas){
        boolean planta_creada = false;
        ArrayList<Planta> plantas = new ArrayList<>();
        System.out.println(plantas_asignadas);
        for(int i = 0; i < total_plantas; i++){
           if (!plantas_asignadas.isEmpty()){
               while( (Integer) plantas_asignadas.get(0) == i){
                   asc.add(asc.get(0));
                   asc.add(asc.get(0));
                   System.out.println("Planta " + i + " tiene el ascensor " + asc.remove(0).get_nombre());
                   plantas_asignadas.remove(0);
                   plantas.add(new Planta(i, asc.remove(asc.size()-1)));
                   planta_creada = true;
                   if(plantas_asignadas.isEmpty()){
                       break;
                   }
               }
           }
           if (planta_creada == false){
                plantas.add(new Planta(i, null));
           }
           planta_creada = false;
        }
        return plantas;
    }
    
    /* Generar asignacion aleatoria de ascensores */
    public ArrayList asignarAscensor(int total_plantas, int total_ascensores){
        ArrayList  plantas_asignadas = new ArrayList();
        for(int i = 0; i < total_ascensores; i++){
            plantas_asignadas.add(rand.nextInt(total_plantas));
        }
        Collections.sort(plantas_asignadas);
        return plantas_asignadas;
    }
    
}
