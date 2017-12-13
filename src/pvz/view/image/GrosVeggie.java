
package pvz.view.image;

import java.awt.Graphics;
import pvz.controller.PvZController;
/**
  * Visual object representing a roadway
  *
  * <p> This class contains all the information necessary for the correct
  * visual operation of the roads. It contains values
  * predetermined variables allowing to distinguish the roads of the
  * other kinds of JVeggies. </ p>
  */

public class GrosVeggie extends JVeggie{
    public GrosVeggie(float posiX, int noZombie, final PvZController controller){
        super(posiX, "GV", noZombie, controller);
    }
}
