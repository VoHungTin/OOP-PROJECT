package pvz.view.image;

import java.awt.Color;
import java.awt.Graphics;
import pvz.controller.PvZController;

/**
 *   * Visual object that represents a GhettoBlaster Veggie   *  
 * <p>
 * This class contains all the information necessary for the correct   * Visual
 * operation of GhettoBlaster Veggies. It contains values   * predetermined
 * variables to distinguish the Limbo Veggies from   * other kinds of JVeggies.
 * </ p>  
 */
public class LimboVeggie extends JVeggie {

    public LimboVeggie(float posiX, int noZombie, final PvZController controller) {
        super(posiX, "LV", noZombie, controller);
    }
}
