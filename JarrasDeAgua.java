package jarrasdeagua;
//83 lineas de codigo

import jarrasdeagua.heuristica.Auto;
/**
 *
 * @author Luis Detlefsen
 */
public class JarrasDeAgua {

    public static void main(String[] args) {
        for(String s:args){
            System.out.println(s);
        }
        if(args.length>0){
            Auto.main(args);
            System.exit(0);
        }
        System.out.println("Problema de las jarras de agua");
        jarrasDeAgua();
        System.out.println();
        System.out.println("Problema del lechero");
        lechero();
    }

    /**
     * <b>Problema:</b> ¿Cómo haría para traer de un río 6 litros de agua,<br> 
     * si no tiene a su disposición, para medir el agua, más que dos recipientes<br> 
     * no graduados, uno de 4 litros y otro de 9 litros? El agua que no necesite<br> 
     * la puede echar en el río nuevamente. <br>
     * <br>
     * <b>Solución:</b><br>
     * Llenar el recipiente de 9 litros.<br>
     * Vaciar el recipiente de 9 litros en el de 4 litros.<br>
     * Vaciar el recipiente de 4 litros.<br>
     * Vaciar el recipiente de 9 litros en el de 4 litros.<br>
     * Vaciar el recipiente de 4 litros.<br>
     * Vaciar el recipiente de 9 litros en el de 4 litros.<br>
     * Llenar el recipiente de 9 litros.<br>
     * Vaciar el recipiente de 9 litros en el de 4 litros.<br>
     * Vaciar el recipiente de 4 litros.
     */
    public static void jarrasDeAgua() {
        Recipiente rec4 = new Recipiente("recipiente 4");
        Recipiente rec9 = new Recipiente("recipiente 9");
        rec4.setCapacidad(4);
        rec9.setCapacidad(9);
        rec9.llenar(rec9.getCapacidad());
        rec4.llenar(rec9.vaciar(rec4.getLibre()));
        rec4.vaciar(rec4.getCapacidad());
        rec4.llenar(rec9.vaciar(rec4.getLibre()));
        rec4.vaciar(rec4.getCapacidad());
        rec4.llenar(rec9.vaciar(rec4.getLibre()));
        rec9.llenar(rec9.getCapacidad());
        rec4.llenar(rec9.vaciar(rec4.getLibre()));
        rec4.vaciar(rec4.getCapacidad());
        System.out.println(rec4.getNombre() + ": " + rec4.getCantidad() + " litros");
        System.out.println(rec9.getNombre() + ": " + rec9.getCantidad() + " litros");
    }

 
    /**
     * <b>Problema:</b> Un lechero tiene un cántaro de 8 litros lleno de leche, 
     * y dos más de 5 y de 3 litros. Un cliente le pide exactamente 4 litros. 
     * ¿Cómo puede calcular los cuatro litros y dárselos en el cántaro de 5 litros?<br>
     * <br>
     * <b>Solución:</b><br>
     * Llenar el recipiente de 5 litros<br>
     * Vaciar el recipiente de 5 litros en el de 3 litros<br>
     * Vaciar el recipiente de 5 litros en el de 8 litros<br>
     * Vaciar el recipiente de 3 litros<br>
     * Llenar el recipiente de 5 litros<br>
     * Vaciar el recipiente de 5 litros en el de 3 litros<br>
     * Vaciar el recipiente de 8 litros en el de 5 litros<br>
     * Vaciar el recipiente de 3 litros<br>
     */
    public static void lechero() {
        Recipiente rec8 = new Recipiente("recipiente 8");
        Recipiente rec5 = new Recipiente("recipiente 5");
        Recipiente rec3 = new Recipiente("recipiente 3");
        rec8.setCapacidad(8);
        rec5.setCapacidad(5);
        rec3.setCapacidad(3);
        rec5.llenar(rec5.getCapacidad());
        rec3.llenar(rec5.vaciar(rec3.getLibre()));
        rec8.llenar(rec5.vaciar(rec8.getLibre()));
        rec3.vaciar(rec3.getCapacidad());
        rec5.llenar(rec5.getCapacidad());
        rec3.llenar(rec5.vaciar(rec3.getLibre()));
        rec5.llenar(rec8.vaciar(rec5.getLibre()));
        rec3.vaciar(rec3.getCapacidad());
        System.out.println(rec3.getNombre() + ": " + rec3.getCantidad() + " litros");
        System.out.println(rec5.getNombre() + ": " + rec5.getCantidad() + " litros");
        System.out.println(rec8.getNombre() + ": " + rec8.getCantidad() + " litros");
    }
}