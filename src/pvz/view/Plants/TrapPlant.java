/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// +work
package pvz.view.plant;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import pvz.controller.PvZController;
import pvz.view.game.JZonePlantable;
import pvz.view.image.JVeggie;

public class TrapPlant extends JPlant {

    /*
     * Contains the image that the program should display for a trap-plant at the time of the call of the variable.
     */
    private Image imgActuelle;
    /*
     * Contains the image representing a deactivated plant-trap.
     */
    private Image imgInactive = Toolkit.getDefaultToolkit().getImage("TrapPlant2.gif");
    /*
     * Contains the image representing an activated plant-trap.
     */
    private Image imgActive = Toolkit.getDefaultToolkit().getImage("TrapPlant1.gif");

    /**
     * Builder of a visual plant-trap. It instantiates the current image
     * default of a plant-trap that is in the disabled state.
     *
     * @param jzp An object that represents an area where you can plant a plant.
     * @param controller Initializes the controller and thus establishes
     * the communication between the two.
     */
    public TrapPlant(final PvZController controller, JZonePlantable jzp) {
        super(jzp, controller, "TrapPlant");
        imgActuelle = imgInactive;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgActuelle, 0, 0, this);
    }

    @Override
    public synchronized void actualiser(boolean zombieDansLaLane, boolean resteZombies) {
        if (!getControleur().getPlante(this.getCoordYX()).isDesactive()) {
            imgActuelle = imgActive;
        } else {
            imgActuelle = imgInactive;
        }
    }
}