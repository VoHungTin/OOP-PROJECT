/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pvz.view.plant;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import pvz.controller.PvZController;
import pvz.view.game.JZonePlantable;
import pvz.view.game.PnlTerrainJeu;
import pvz.view.projectiles.Peas;

public class TirePois extends JPlant {

    /*
     * Contains the image it should display when the variable is called.     
     */
    private Image imgActuelle;
    /*
     * Contains the image that must be drawn when it is activated.
     */
    private Image imgInactive = Toolkit.getDefaultToolkit().getImage("PeaShooter2.gif");
    /*
     * Contains the image to be drawn when it is not activated.
     */
    private Image imgActive = Toolkit.getDefaultToolkit().getImage("PeaShooter1.gif");
    /*
     * Integer that represents the time between each pea shot.
     */
    private int tempsRecharge;
    /*
    * Long which represents the time at which the plant was created.
     */
    private long dateNaissance;
    /*
     * Declaration of the main panel to establish communication between the two.
     */
    private PnlTerrainJeu pnlTerrainJeu;
    /*
     * Long which represents the time at which the plant pulled a pig for the last time.
     */
    private long dateDernierTir;
    /*
     * Boolean that represents whether the plant is ready to shoot or not.
     */
    private boolean charge;

    /**
     * Constructor of visual polka-dot. It instantiates the timeRecharge, the
     * dateLastTime, the state loaded to true by default and the image Current to
     * active image.
     *
     * @param jzp An object that represents an area where you can plant a plant.
     * @param controller Initializes the controller and thus establishes
     * the communication between the two.
     */
    public TirePois(final PvZController controleur, JZonePlantable jzp) {
        super(jzp, controleur, "TirePois");
        this.tempsRecharge = controleur.getFPS();
        this.dateDernierTir = controleur.getTemps();
        this.charge = true;
        this.imgActuelle = imgActive;
    }

    @Override
    public void actualiser(boolean ZombieDansLaLane, boolean resteZombies) {
        if ((dateDernierTir - super.getControleur().getTemps()) % (tempsRecharge / 2) == 0) {
            imgActuelle = imgActive;
        }
        if ((dateDernierTir - super.getControleur().getTemps()) % tempsRecharge == 0 && ZombieDansLaLane) {
            charge = true;
        }
        if (charge && ZombieDansLaLane) {
            tirerPois();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgActuelle, 0, 0, this);
    }

    /**
     * Manage the animation of a pea-shoot plant that shoots.
     */
    private void tirerPois() {
        super.getControleur().playSound("pew.wav");
        dateDernierTir = super.getControleur().getTemps();
        this.getJzp().getLane().addProjectile(new Peas((float) (this.getJzp().getX() + 59), this.getJzp().getY() + 23));
        charge = false;
        imgActuelle = imgInactive;
    }
}
