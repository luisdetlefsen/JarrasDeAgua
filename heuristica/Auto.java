
package jarrasdeagua.heuristica;

import jarrasdeagua.Recipiente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * digraph G{
	Inicio -> Llenar_X;
	Inicio -> Llenar_Y;
	Llenar_X -> Llenar_Y;
	Llenar_X -> Vaciar_Y;
	Llenar_X -> Vaciar_X_en_Y;
	Llenar_Y -> Llenar_X;
	Llenar_Y -> Vaciar_X;
	Llenar_Y -> Vaciar_Y_en_X;
	Vaciar_X -> Llenar_Y;
	Vaciar_X -> Vaciar_Y_en_X;
	Vaciar_Y -> Llenar_X;
	Vaciar_Y -> Vaciar_X_en_Y;
	Vaciar_X_en_Y -> Llenar_X;
	Vaciar_X_en_Y -> Llenar_Y;
	Vaciar_X_en_Y -> Vaciar_X;
	Vaciar_X_en_Y -> Vaciar_Y;
	Vaciar_Y_en_X -> Llenar_X;
	Vaciar_Y_en_X -> Llenar_Y;
	Vaciar_Y_en_X -> Vaciar_X;
	Vaciar_Y_en_X -> Vaciar_Y;
}
 * 
 * dot.exe -Tgif C:\Users\x64\Documents\estados_jarras.dot -o C:\Users\x64\Documents\graph.gif
 */
 
 /**
 *
 * @author Luis Detlefsen
 */
public class Auto {

    /**
     * Guarda el recorrido actual del algoritmo.
     */
    private Stack<Paso> recorrido = new Stack<Paso>();
    
    /**
     * Para mostrar el camino de la solución. Es igual al contenido de 
     * <i>recorrido</i> cuando el algoritmo ha encontrado la solución.
     */
    private Stack<ACCION> camino = new Stack<ACCION>();
    
    /**
     * Total de pasos realizados para encontrar la solución. 
     * Únicamente toma en cuenta la búsqueda con la profundidad usada en la 
     * solución.
     */
    int c = 0; 
    
    /**
     * Profundidad de busqueda.
     */
    static int profundidad = 9;
    
    /**
     * Recipiente A.
     */
    static Recipiente a = new Recipiente("Recipiente de 9 litros", 9);
    
    /**
     * Recipiente B.
     */
    static Recipiente b = new Recipiente("Recipiente de 4 litros", 4);
    
    /**
     * Cantidad objetivo del recipiente A.
     */
    static int objetivoA = 6;
    
    /**
     * Cantidad objetivo del recipiente B.
     */
    static int objetivoB = 0;

    public static void main(String[] args) {
        Auto auto = new Auto();
        if (args.length > 0 && args[0].equals("custom")) {
            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                
                System.out.print("Ingrese el nombre del recipiente X: ");
                String s = bufferRead.readLine();
                a.setNombre(s);
                
                System.out.print("\nIngrese el nombre del recipiente Y: ");
                s = bufferRead.readLine();
                b.setNombre(s);
                
                System.out.print("\nIngrese la capacidad del recipiente X: ");
                s = bufferRead.readLine();
                a.setCapacidad(Integer.parseInt(s));
                
                System.out.print("\nIngrese la capacidad del recipiente Y: ");
                s = bufferRead.readLine();
                b.setCapacidad(Integer.parseInt(s));
                
                System.out.print("\nIngrese el objetivo del recipiente X: ");
                s = bufferRead.readLine();
                objetivoA = Integer.parseInt(s);
                
                System.out.print("\nIngrese el objetivo del recipiente Y: ");
                s = bufferRead.readLine();
                objetivoB = Integer.parseInt(s);
                
                System.out.print("\nIngrese la profundidad de busqueda: ");
                s = bufferRead.readLine();
                profundidad = Integer.parseInt(s);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if( !(args.length>0 && args[0].equals("default"))){
            System.out.println("Parametro invalido. Use <default> o <custom>.");
            System.exit(0);
        }
            

        while (true) {
            try {
                auto.resolver();
                System.exit(0);
            } catch (Exception e) {
                profundidad++;
                System.out.println("No hay solucion, aumentando profundidad de busqueda a " + profundidad);
                System.out.println();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    
    /**
     * Resuelve el problema de las jarras de agua usando un algoritmo
     * de búsqueda de profundidad limitada.
     */
    public void resolver() {
        Paso pasoInicial = new Paso();
        pasoInicial.setAccion(null);
        pasoInicial.addRecipiente(a);
        pasoInicial.addRecipiente(b);
        pasoInicial.addSiguienteAccion(ACCION.LLENAR_X);
        pasoInicial.addSiguienteAccion(ACCION.LLENAR_Y);
        recorrido.add(pasoInicial);
        while (!(a.getCantidad() == objetivoA && b.getCantidad() == objetivoB)) {
            avanzar();
            if (recorrido.size() - 1 > profundidad) {
                recorrido.pop();
            }
            a = recorrido.peek().getRecipientes().get(0);
            b = recorrido.peek().getRecipientes().get(1);
        }

        System.out.println("Cantidad de pasos realizados: " + c);
        while (!recorrido.empty()) {
            ACCION ac = recorrido.pop().getAccion();
            if (ac == null) {
                continue;
            }
            camino.push(ac);
        }
        System.out.println("Cantidad de pasos de la solucion: " + camino.size());
        System.out.println("Profundidad de busqueda: " + profundidad);
        System.out.println(a.getNombre() + ": " + a.getCantidad()+"/"+a.getCapacidad());
        System.out.println(b.getNombre() + ": " + b.getCantidad()+"/"+b.getCapacidad());
        System.out.println();
        System.out.println("Solucion:");
        System.out.println("---------------");
        a = new Recipiente(a.getNombre(), a.getCapacidad());
        b = new Recipiente(b.getNombre(), b.getCapacidad());
        while (!camino.empty()) {
            ACCION n = camino.pop();
            Paso p = realizarAccion(n, a, b);
            a.setCantidad(p.getRecipientes().get(0).getCantidad());
            b.setCantidad(p.getRecipientes().get(1).getCantidad());
            String s = n + "";
            s = s.replaceAll("X", "%s");
            s = s.replaceAll("Y", "%s");
            s = s.replace('_', ' ');
            System.out.printf(s, a.getNombre(), b.getNombre());mostrarEstadoRecipiente(a);mostrarEstadoRecipiente(b);
            System.out.println();
        }

    }

    /**
     * Estando en el paso actual, avanza hacia el siguiente paso posible. 
     * Si no hay más pasos posibles, regresa al paso anterior.
     */
    private void avanzar() {
        c++;
        ACCION accion = recorrido.peek().getSiguiente();
        if (accion == null) {
            recorrido.pop();
            return;
        }
        System.out.println(accion);
        Paso siguiente = realizarAccion(accion, recorrido.peek().getRecipientes().get(0), recorrido.peek().getRecipientes().get(1));
        recorrido.push(siguiente);
    }

    /**
     * Realiza la acción indicada y crea un nuevo paso con la acción realizada
     * y con sus posibles acciones siguientes.
     * @param a Acción a realizar.
     * @param xx Recipiente x, se hará una copia.
     * @param yy Recipiente y, se hará una copia.
     * @return Devuelve un nuevo Paso.
     */
    private Paso realizarAccion(ACCION a, Recipiente xx, Recipiente yy) {
        try {
            Recipiente x = xx.clone();
            Recipiente y = yy.clone();
            Paso p = new Paso();
            if (a == ACCION.LLENAR_X) {
                x.llenar(x.getCapacidad());
            } else if (a == ACCION.LLENAR_Y) {
                y.llenar(y.getCapacidad());
            } else if (a == ACCION.VACIAR_X) {
                x.vaciar(x.getCapacidad());
            } else if (a == ACCION.VACIAR_Y) {
                y.vaciar(y.getCapacidad());
            } else if (a == ACCION.VACIAR_X_EN_Y) {
                y.llenar(x.vaciar(y.getLibre()));
            } else if (a == ACCION.VACIAR_Y_EN_X) {
                x.llenar(y.vaciar(x.getLibre()));
            }
            p.setAccion(a);
            p.addRecipiente(x);
            p.addRecipiente(y);
            for (ACCION accion : ACCION.values()) {
                if (ValidadorTransicion.validarDesdeXHaciaY(a, accion, x.getCantidad(), y.getCantidad(), x.getCapacidad(), y.getCapacidad()) && !p.getAccionRealizada(accion)) {
                    p.addSiguienteAccion(accion);
                    p.addAccionRealizada(accion);
                }
            }
            return p;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void mostrarEstadoRecipiente(Recipiente r){
        System.out.print(" -- "+r.getNombre()+"="+r.getCantidad()+"/"+r.getCapacidad());
    }
}
