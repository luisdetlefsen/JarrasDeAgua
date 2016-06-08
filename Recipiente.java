package jarrasdeagua;
/**
 *
 * @author Luis Detlefsen
 */
public class Recipiente implements Cloneable {
    
    /**
     * Capacidad de almacenamiento del recipiente.
     */
    private int capacidad;
    
    /**
     * Cantidad almacenada en el recipiente.
     */
    private int cantidad;
    
    /**
     * Identificador del recipiente.
     */
    private String nombre = "";

    public Recipiente(){}
    
    public Recipiente(String n){nombre = n;}
    
    public Recipiente(String n, int c){
        nombre = n;
        capacidad = c;
    }
    
    /**
     * Obtiene la capacidad de almacenamiento del recipiente.
     * @return La capacidad del recipiente.
     */
    public int getCapacidad() { return capacidad;}

    /**
     * Establece la capacidad de almacenamiento del recipiente.
     * @param capacidad Capacidad de almacenamiento.
     */
    public void setCapacidad(int capacidad) { this.capacidad = capacidad;}
    
    /**
     * Obtiene el identificador del recipiente.
     * @return Identificador del recipiente.
     */
    public String getNombre(){ return nombre;}
    
    /**
     * Obtiene la cantidad almacenada actualmente en el recipiente.
     * @return Cantidad almacenada en el recipiente.
     */
    public int getCantidad(){ return cantidad;}
    
    /**
     * Obtiene la cantidad libre que hay para almacenar. Esto es la cantidad
     * de almacenaje menos la cantidad almacenada.
     * @return Cantidad libre para almacenar.
     */
    public int getLibre(){ return capacidad - cantidad;}
    
    /**
     * Llena el recipiente con la cantidad especificada. 
     * @param cant Cantidad a llenar.
     */
    public void llenar(int cant){ cantidad = cant+cantidad>=capacidad?capacidad:cant+cantidad;}
    
    /**
     * Remueve del recipiente la cantidad especificada. Si la cantidad a remover
     * es mayor a la almacenada entonces remueve el total almacenado.
     * @param cant Cantidad a remover.
     * @return Cantidad removida.
     */
    public int vaciar(int cant) {
        if(cant>cantidad){
            int tmp = cantidad;
            cantidad = 0;
            return tmp;
        }
        cantidad -= cant;
        return cant;         
    }

    @Override
    public Recipiente clone() throws CloneNotSupportedException {
        Recipiente r = new Recipiente();
        r.cantidad = this.cantidad;
        r.capacidad = this.capacidad;
        r.setNombre(this.getNombre());
        return r;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public void setCantidad(int c){
        this.cantidad = c;
    }
        
    
}