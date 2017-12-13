package pvz.view.plant;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import pvz.controller.PvZController;
import pvz.view.game.JZonePlantable;
import pvz.view.game.PnlTerrainJeu;

public class Peanut extends JPlant {

    /*
     * Boolean that represents if the lante is shooting or not. False if it is not, and true if it is.
     */
    private final boolean actif;
    /*
     * Contains the image it should display when the variable is called.
     */
    private Image imgActuelle;
    /*
     * Contains the image that must be drawn when it is not active.
     */
    private Image imgInactive = Toolkit.getDefaultToolkit().getImage("LanceCochon1.gif");
    /*
     * Continues the image that should be drawn when it is activated.
     */
    private Image imgActive = Toolkit.getDefaultToolkit().getImage("LanceCochon2.gif");
    /*
     *Integer that represents the time between each pig shot.
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
    * Pig-launcher constructor that initializes the default loaded boolean
     * to true, the communication with the game field board, the date of the last
     * shot, time to reload, creation time, active state true by
     * default, as well as the active image.
     *
     * @param jzp An object that represents an area where you can plant a plant.
     * @param controller Initializes the controller and thus establishes
     * the communication between the two.
     * @param pnlLeadground Allows you to initialize the main window and,
     * thus, establish the communication between the two.
     */
    public Peanut(final PvZController controleur, JZonePlantable jzp, PnlTerrainJeu pnlTerrainJeu) {
        super(jzp, controleur, "LanceCochon");
        this.pnlTerrainJeu = pnlTerrainJeu;
        this.charge = true;
        this.dateDernierTir = super.getControleur().getTemps();
        this.tempsRecharge = super.getControleur().getFPS() * 5;
        this.dateNaissance = super.getControleur().getTemps();
        this.actif = true;
        this.imgActuelle = imgActive;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(imgActuelle, 0, 0, this);
    }

    @Override
    public void actualiser(boolean zombieDansLane, boolean resteZombies) {
        if ((dateDernierTir - super.getControleur().getTemps()) % tempsRecharge == 0) {
            charge = true;
            imgActuelle = imgActive;
        }
        if (charge && resteZombies) {
            tirerBouletCochon();
        }
    }

    /**
     * Method used to make the animation to draw pigs from
     * pig thrower.
     */
    private void tirerBouletCochon() {
        super.getControleur().playSound("pop.wav");
        dateDernierTir = super.getControleur().getTemps();
        pnlTerrainJeu.lancerBouletCochon(super.getJzp());
        charge = false;
        imgActuelle = imgInactive;
    }
}
