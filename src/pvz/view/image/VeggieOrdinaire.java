
package pvz.view.image;

import java.awt.Graphics;
import pvz.controller.PvZController;

/**
  * Visual object that represents an ordinary Veggie
  *
  * <p> This class contains all the information necessary for the correct
  * Visual functioning of the ordinary Veggies. It contains values
  * predetermined variables to distinguish the ordinary Veggies from
  * other kinds of JVeggies. </ p>
  */
public class VeggieOrdinaire  extends JVeggie{
    
    
    public VeggieOrdinaire(float posiX, int noZombie, final PvZController controller) {
        super(posiX, "VO", noZombie, controller);
    }
    
}
