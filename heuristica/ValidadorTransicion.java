
package jarrasdeagua.heuristica;

/**
 *
 * @author Luis Detlefsen
 */
public class ValidadorTransicion {

    /**
     * <b>Transiciones:</b><table border="1">
     * <tr><td><b>Desde</b></td><td><b>Hacia</b></td><td><b>Valido</b></td><td><b>Validar cantidades</b></td></tr>
     * <tr><td>Llenar x</td><td>Llenar x</td><td>false	</td><td>&nbsp</td></tr>
     * <tr><td>Llenar x</td><td>Llenar y</td><td>true</td><td>	y</td></tr>
     * <tr><td>Llenar x</td><td>Vaciar x</td><td>false</td><td>	</td></tr>
     * <tr><td>Llenar x</td><td>Vaciar y</td><td>true</td><td>y</td></tr>
     * <tr><td>Llenar x</td><td>Vaciar x en y</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Llenar x</td><td>Vaciar y en x</td><td>	false</td><td>	</td></tr>
     * <tr><td>Llenar y</td><td>Llenar x</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Llenar y</td><td>	Llenar y</td><td>	false</td><td>	</td></tr>
     * <tr><td>Llenar y</td><td>	Vaciar x</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Llenar y</td><td>	Vaciar y</td><td>	false</td><td>	</td></tr>
     * <tr><td>Llenar y</td><td>	Vaciar x en y</td><td>	false</td><td>	</td></tr>
     * <tr><td>Llenar y</td><td>	Vaciar y en x</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar x</td><td>	Llenar x</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar x</td><td>	Llenar y</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar x</td><td>	Vaciar x</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar x</td><td>	Vaciar y</td><td>	false</td><td></td></tr>
     * <tr><td>Vaciar x</td><td>	Vaciar x en y</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar x</td><td>	Vaciar y en x</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar y</td><td>	Llenar x</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar y</td><td>	Llenar y</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar y</td><td>	Vaciar x</td><td>	false</td><td></td></tr>
     * <tr><td>Vaciar y</td><td>	Vaciar y</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar y</td><td>	Vaciar x en y</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar y</td><td>	Vaciar y en x</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar x en y</td><td>	Llenar x</td><td>	true</td><td>	</td></tr>
     * <tr><td>Vaciar x en y</td><td>	Llenar y</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar x en y</td><td>	Vaciar x</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar x en y</td><td>	Vaciar y</td><td>	true</td><td>	</td></tr>
     * <tr><td>Vaciar x en y</td><td>	Vaciar x en y</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar x en y</td><td>	Vaciar y en x</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar y en x</td><td>	Llenar x</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar y en x</td><td>	Llenar y</td><td>	true</td><td>	</td></tr>
     * <tr><td>Vaciar y en x</td><td>	Vaciar x</td><td>	true</td><td>	</td></tr>
     * <tr><td>Vaciar y en x</td><td>	Vaciar y</td><td>	true</td><td>	y</td></tr>
     * <tr><td>Vaciar y en x</td><td>	Vaciar x en y</td><td>	false</td><td>	</td></tr>
     * <tr><td>Vaciar y en x</td><td>	Vaciar y en x</td><td>	false</td><td>	</td></tr>
     * </table>
    
     * @param x Estado actual, la última acción que se realizó.
     * @param y Acción que se quiere realizar.
     * @param cantX 
     * @param cantY
     * @return Indica si es válida la transicion de X hacia Y.
     */
    public static boolean validarDesdeXHaciaY(ACCION x, ACCION y, int cantX, int cantY, int maxX, int maxY) {
        if (x == ACCION.LLENAR_X && y == ACCION.LLENAR_Y && cantY < maxY) {
            return true;
        }
        if (x == ACCION.LLENAR_X && y == ACCION.VACIAR_Y && cantY > 0) {
            return true;
        }
        
        
        if (x == ACCION.LLENAR_X && y == ACCION.VACIAR_X_EN_Y && cantY < maxY) {
            return true;
        }

        if (x == ACCION.LLENAR_Y && y == ACCION.LLENAR_X && cantX < maxX) {
            return true;
        }
        if (x == ACCION.LLENAR_Y && y == ACCION.VACIAR_X && cantX > 0) {
            return true;
        }
        if (x == ACCION.LLENAR_Y && y == ACCION.VACIAR_Y_EN_X && cantX < maxX) {
            return true;
        }

        if (x == ACCION.VACIAR_X && y == ACCION.LLENAR_Y && cantY < maxY) {
            return true;
        }
        if (x == ACCION.VACIAR_X && y == ACCION.VACIAR_Y_EN_X && cantY > 0) {
            return true;
        }

        if (x == ACCION.VACIAR_Y && y == ACCION.LLENAR_X && cantX < maxX) {
            return true;
        }
        if (x == ACCION.VACIAR_Y && y == ACCION.VACIAR_X_EN_Y && cantX > 0) {
            return true;
        }

        if (x == ACCION.VACIAR_X_EN_Y && y == ACCION.LLENAR_X) {
            return true;
        }
        if (x == ACCION.VACIAR_X_EN_Y && y == ACCION.LLENAR_Y && cantY < maxY) {
            return true;
        }
        if (x == ACCION.VACIAR_X_EN_Y && y == ACCION.VACIAR_X && cantX > 0) {
            return true;
        }
        if (x == ACCION.VACIAR_X_EN_Y && y == ACCION.VACIAR_Y) {
            return true;
        }

        if (x == ACCION.VACIAR_Y_EN_X && y == ACCION.LLENAR_X && cantX < maxX) {
            return true;
        }
        if (x == ACCION.VACIAR_Y_EN_X && y == ACCION.LLENAR_Y) {
            return true;
        }
        if (x == ACCION.VACIAR_Y_EN_X && y == ACCION.VACIAR_X) {
            return true;
        }
        if (x == ACCION.VACIAR_Y_EN_X && y == ACCION.VACIAR_Y && cantY > 0) {
            return true;
        }
        return false;
    }
}
