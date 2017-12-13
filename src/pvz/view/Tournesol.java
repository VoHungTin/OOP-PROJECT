package pvz.view.plant;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pvz.controller.PvZController;
import pvz.view.game.JZonePlantable;
 
public class Tournesol extends JPlant {

    /*
     * Contains the image it should display when the variable is called.
    */
    private Image imgActuelle;
    /*
     * Contains the image that must be drawn when it is activated.
     */
    private Image imgActif = Toolkit.getDefaultToolkit().getImage("Tournesol1.gif");
    /*
     * Contains the image to be drawn when it is not activated.
     */
    private Image imgInactif = Toolkit.getDefaultToolkit().getImage("Tournesol2.gif");
    /*
     * Integer that represents the time between each pea shot.
     */
    private int tempsRecharge;
    /*
     * Long which represents the time at which the plant was created.
     */
    private long dateNaissance;
    /*
    * Boolean that represents whether the plant is ready to shoot or not.
     */
    private boolean actif;

    /**
    * Builder of a sunflower. It instantiates the timeRecharge, the
     * dateBirth, the current state to false by default, the current image to image
     * inactive and a mouseListener for when a player presses the plant
     * to take the money.
     *
     * @param jzp An object that represents an area where you can plant a plant.
     * @param controller Initializes the controller and thus establishes
     * the communication between the two.
     */
    public Tournesol(final PvZController controleur, JZonePlantable jzp) {
        super(jzp, controleur, "Tournesol");
        this.tempsRecharge = controleur.getFPS() * 30;
        this.actif = false;
        this.imgActuelle = imgInactif;
        this.dateNaissance = controleur.getTemps();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                Tournesol tourneSource = ((Tournesol) me.getSource());
                if (tourneSource.actif) {
                    actif = false;
                    tourneSource.imgActuelle = imgInactif;
                    controleur.playSound("kaching.wav");
                    controleur.modCash(25);
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgActuelle, 0, 0, this);
    }

    @Override
    public void actualiser(boolean zombieDansLane, boolean resteZombies) {
        if (getControleur().getTemps()!=dateNaissance&&((getControleur().getTemps() - dateNaissance) % tempsRecharge == 0)) {
            this.actif = true;
            this.imgActuelle = imgActif;
        }
        this.invalidate();
        this.repaint();
    }
}
