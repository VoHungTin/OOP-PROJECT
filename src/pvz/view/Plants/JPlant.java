package pvz.view.plant;

import javax.swing.JComponent;
import pvz.controller.PvZController;
import pvz.view.game.JZonePlantable;

public abstract class JPlant extends JComponent {
    
    /*Table of 2 integer that allows, like the ID of the zombies,
     * to know which plant in the model corresponds to which plant in the
     * view.
     */

    private int[] coordYX = new int[2];
    /*
     *Declaring a PvZController variable that allows the thread to communicate with the controller.
     */
    private PvZController controller;
    /*
     *String that lets you know what kind of
     * plant is created since the statistics differ from one type to another.
     */
    private String typePlante;
    /*
     * Object that represents an area where you can plant a plant.
     */
    private JZonePlantable jzp;

    /**
     * Method that returns the area on which the plant concerned was
     * placed.
     *
     * @return An object that represents an area where you can plant a plant.
     */
    protected JZonePlantable getJzp() {
        return jzp;
    }

    /**
     * Function that returns the coordinates of the concerned plant
     *
     * @return Table of 2 integer that allows, like the ID of zombies, to
     * know which plant in the model corresponds to which plant in the
     * view.
     */
    public int[] getCoordYX() {
        return coordYX;
    }

    /**
     * Constructor of a JPlante where one initializes the zone where it is
     * planted, its coordinates, the controller, its size, the type of plant
     * created and ultimately, where is called the controller to create the plant
     * in the model.
     *
     * @param jzp An object that represents an area where you can plant a plant.
     * @param controller Initializes the controller and thus establishes
     * the communication between the two.
     * @param typePlant String that lets you know what kind of plant is
     * created since the statistics differ from one type to another.
     */
    public JPlant(JZonePlantable jzp, final PvZController controller, String typePlante) {
        this.jzp = jzp;
        this.coordYX[0] = jzp.getLane().getNoLane();
        this.coordYX[1] = jzp.getNoCase();
        this.controller = controller;
        this.setSize(106, 155);
        this.typePlante = typePlante;
        controller.creePlante(typePlante, coordYX);
    }

    /**
     * Abstract method that will be redefined in children's classes.
          * This is used to determine what to do when it comes time
          * to repaint the images. This often involves changing the images
          * current according to the new state of the plant (activated or deactivated). Of
          * more, it activates the animation of the plant to shoot or trap or
          * show a bag of money.
          *
          * @param zombieInLaLane Boolean who determines if there are zombies in
          * the line of plants or not.
          * @param remains Boolean zombies that determines if there are zombies remaining in the
          * line or not.
     */
    public abstract void actualiser(boolean zombieDansLaLane, boolean resteZombies);

    /**
          * function that returns the controller when called. This is used by
          * children in the classroom when they need a controller method.
          *
          * @return PvZController that makes the link with the plants and the controller.
     */
    public PvZController getControleur() {
        return this.controller;
    }
}
