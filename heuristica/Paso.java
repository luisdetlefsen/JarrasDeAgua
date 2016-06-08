package jarrasdeagua.heuristica;

import jarrasdeagua.Recipiente;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
 *
 * @author Luis Detlefsen
 */
public class Paso {

    /**
     * Acción realizada en el paso.
     */
    private ACCION accion;
    /**
     * Colección de los pasos siguientes posibles.
     */
    private HashSet<ACCION> siguiente = new HashSet<ACCION>();
    /**
     * Colección de los pasos ya visitados, para evitar repetirlos.
     */
    private HashSet<ACCION> visitados = new HashSet<ACCION>();
    /**
     * Copia del estado de los recipientes.
     */
    private List<Recipiente> recipientes = new ArrayList<Recipiente>();

    
    /**
     * Agrega una acción que ha sido realizada para que no se vuelva a repetir.
     * @param a Acción realizada.
     * @return false si la acción ya ha sido realizada antes.
     */
    public boolean addAccionRealizada(ACCION a) {
        return visitados.add(a);

    }

    /**
     * Indica si la acción <i>accion</i> ya ha sido realizada.
     * @param accion Acción que se está buscando.
     * @return true si la acción ya ha sido realizada antes.
     */
    public boolean getAccionRealizada(ACCION accion) {
        return visitados.contains(accion);
    }

    
    /**
     * Agrega una copia del recipiente <i>r</i>.
     * @param r Recipiente que se va a copiar.
     */
    public void addRecipiente(Recipiente r) {
        try {
            recipientes.add(r.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Agrega una accion que es posible ejecutar.
     * @param a Acción posible a ejecutar.
     */
    public void addSiguienteAccion(ACCION a) {
        siguiente.add(a);
    }

    /**
     * Devuelve la acción que fue ejecutada en este paso.
     * @return the accion
     */
    public ACCION getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(ACCION accion) {
        this.accion = accion;
    }

    /**
     * Devuelve una acción que es posible realizar.
     * @return Siguiente accion a realizar. null si no hay mas acciones posibles.
     */
    public ACCION getSiguiente() {
        if (!siguiente.iterator().hasNext()) {
            return null;
        }
        ACCION a = siguiente.iterator().next();
        siguiente.remove(a);
        return a;
    }

    /**
     * @return the recipientes
     */
    public List<Recipiente> getRecipientes() {
        return recipientes;
    }
}
