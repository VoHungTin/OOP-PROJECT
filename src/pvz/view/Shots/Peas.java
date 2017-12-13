package pvz.view.shots;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that represents all visual objects of type projectiles plus
  * precisely the peas.
 *
 * <p>It contains methods for the proper visualization of peas.
  * It determines how these move and how they look.</p>
 */
public class Peas extends JShots {

    /*
     * Float which represents the speed of displacement of a pea.

     */
    private float vitesse = 2.16f;
    /*
     * Float which represents its position on the current length when calling the variable.
     */
    private float posX;

    /**
     * Pea builder. He instantiates his position on the initial length,
      * its size and initial position.
     *
     * @param posX Float which represents its position on the length.
     * @param posY Integer which represents its position on the height.
     */
    public Peas(float posX, int posY) {
        super("TirePois");
        this.posX = posX;
        this.setSize(20, 20);
        this.setLocation((int) posX, posY);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(0, 0, 20, 20);
    }

    @Override
    public void deplacement() {
        posX += vitesse;
        this.setLocation((int) posX, this.getY());
    }

    @Override
    public void actualiser() {
        deplacement();
        this.invalidate();
        this.repaint();
    }
}