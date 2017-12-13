package pvz.view.image;

import java.awt.Color;
import java.awt.Graphics;
import pvz.controller.PvZController;

/**
 * Visual object that represents a Pom-Pom Veggie
 *
 * <p>
 * This class contains all the information necessary for the correct Visual
 * operation of Pom-Pom Veggies. It contains values predetermined variables to
 * distinguish Pom-Pom Veggies from other kinds of JVeggies. </ p>
 */
public class Veggies extends JVeggie {

    /**
     * Constructor CoachVeggie
     *
     * @param posiX
     * @param noZombie
     * @param controller
     */
    public Veggies(float posiX, int noZombie, final PvZController controller) {
        super(posiX, "CV", noZombie, controller);
    }
}
