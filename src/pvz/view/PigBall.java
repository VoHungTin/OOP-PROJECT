/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pvz.view.shots;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import pvz.view.game.TrajectoireBouletCochon;
import pvz.view.game.JZonePlantable;

/*
 * Class that represents all visual objects of type projectiles plus
 */
public class PigBall extends JShots {

    /*
     * Integer which represents the speed at which the projectile moves.
     */
    private int vitesse = -4;
    /*
     * Object that represents the area where the projectile must arrive.
     */
    private JZonePlantable jzpCible;
    /*
     * Integer representing the position along the length of the starting point of the projectile.
     */
    private int posiX;
    /*
     * Integer representing the position over the length of the point of arrival of the projectile.
     */
    private int posiXCible;
    /*
     * Integer representing the position on the height of the starting point of the projectile.
     */
    private int posiY;
    /*
     * Integer representing the position on the height of the point of arrriver of the projectile.
     */
    private int posiYCible;
    /*
     * Boolean that determines whether the projectile is climbing up or not.
     */
    private boolean monte = true;
    /*
     * Panel that serves only the trajectory of the ball-pigs
     */
    private TrajectoireBouletCochon trajectoireBouletCochon;
    /*
     * Contains the image it should display when the variable is called.
     */
    private Image imgActuelle;
    /*
     * Contains the image that must be drawn when the projectile is going up.
     */
    private Image imgMontante = Toolkit.getDefaultToolkit().getImage("Cochon.gif");
    /*
     * Contains the image that must be drawn when the projectile is descending.
     */
    private Image imgDescendante = Toolkit.getDefaultToolkit().getImage("Cochon2.gif");

    /**
     * Builder of the bullet-pig projectile. He instantiates the size of the
      * projectile, its implantable area to arrive, the trajectory panel of
      * ball-pigs, its original position and that of arrival, and then,
      * finally, its current default image is rising.
     *
     * @param jzpOrigine An object that represents the area from which the projectile leaves.
      * @param jzpCible Object that represents the area where the projectile is to
      * arrive.
      * @param trajectoryBucketConchon Panel that is only used for the
      * trajectory of the ball-pigs
     */
    public PigBall(JZonePlantable jzpOrigine, JZonePlantable jzpCible, TrajectoireBouletCochon trajectoireBouletCochon) {
        super("LanceMelon");
        this.setSize(25, 26);
        this.trajectoireBouletCochon = trajectoireBouletCochon;
        this.jzpCible = jzpCible;
        this.posiX = jzpOrigine.getLane().getX() + jzpOrigine.getX() + jzpOrigine.getWidth() / 2;
        this.posiY = jzpOrigine.getLane().getY();
        this.posiXCible = jzpCible.getX() + jzpCible.getLane().getX();
        this.posiYCible = jzpCible.getY() + jzpCible.getLane().getY();
        this.imgActuelle = imgMontante;
    }

    /**
     * Function that returns the position to the original length of the
      * ball-pig.
     *
     * @return Integer representing the position along the length of the point of
      * departure of the projectile.
     */
    public int getPosiX() {
        return posiX;
    }

    /**
     * Function that returns the position to the original height of the
 * ball-pig.
     *
     * @return Integer representing the position on the height of the point of
      * departure of the projectile.
     */
    public int getPosiY() {
        return posiY;
    }

    @Override
    public void actualiser() {
        if (!monte && this.posiX == posiXCible && this.getY() >= posiYCible) {
            
            jzpCible.splat();
            trajectoireBouletCochon.enleverMelon(this);
        } else if (monte && this.posiY <= -this.getHeight()) {
            monte = false;
            imgActuelle = imgDescendante;
            posiX = jzpCible.getX() + jzpCible.getLane().getX();
            this.setLocation(posiX, posiY);
            vitesse = -vitesse;
        }
        deplacement();
        this.invalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(imgActuelle, 0, 0, this);
    }

    @Override
    public void deplacement() {
        posiY += vitesse;
        this.setLocation(posiX, posiY);
    }
}
