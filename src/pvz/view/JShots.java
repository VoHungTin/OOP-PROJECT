package pvz.view.shots;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Class that represents all projectiles-type visual objects.
 *
 * <p>It contains methods for the proper visualization of
  * projectiles. It's in charge of regulating what a projectile is visually.</p>
 */
public abstract class JShots extends JComponent {

    /*
     * String that represents the plant associated with the projectile.
     */
    private String planteAssocie;

    /**
     * Function that returns the plant associated with the projectile concerned.
     *
     * @return String that represents the plant associated with the projectile.
     */
    public String getPlanteAssocie() {
        return planteAssocie;
    }

    /**
     * Abstract method that will be redefined in the child classes. This one
      * is used to determine what to do when it comes time to
      * repaint the images.
     */
    public abstract void actualiser();

    @Override
    public abstract void paintComponent(Graphics g);

    /**
     * Abstract method that will be redefined in the child classes. She serves
      * to move in the window the shots of a certain number of pixels to
      Every time she is called.
     */
    public abstract void deplacement();

    /**
     * Constructor of a JShots. He instantiates the associated plant.
     *
     * @param plantAssociates String that represents the plant associated with the projectile.
     */
    public JShots(String planteAssocie) {
        this.planteAssocie = planteAssocie;
    }
}
