package pvz.view.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComponent;
import pvz.controller.PvZController;
import pvz.model.Zombie;

public abstract class JVeggie extends JComponent {

    /**
      * Float that represents the exact position of the JVeggie in the lane (then rounded up when calling JVeggie.setLocation ()).
      */
    private float posiX;
    /**
      * Float representing the movement speed of the JVeggie.
      */
    private float speed;
    /**
      * String containing the type of veggie that this JVeggie represents in order to associate him the good zombie of the model.
      */
    private String typeZombie;
    /**
      * Integer representing the zombie number of the JVeggie in order to associate him the good zombie of the model.
      */
    private int noZombie;
    /**
      * Controller associated with JVeggie.
      */
    private PvZController controller;
    /**
      * ArrayList containing sprites associated with a certain type of JVeggie walking.
      */
    private ArrayList<Image> walkList = new ArrayList<>();
   /**
      * ArrayList containing sprites associated with a certain type of JVeggie eating.
      */
    private ArrayList<Image> eatList = new ArrayList<>();
    /**
      * ArrayList containing the sprites associated with a certain type of JVeggie walking leaning.
      */
    private ArrayList<Image> walkLeaning = new ArrayList<>();
    /**
      * Integer representing the index of the image that the JVeggie should display from a certain arrayList.
      */
    private int CurrentIndexImg;
    /**
      * Image that the JVeggie displays at the next repaint.
      */
    private Image CurrentImg;
    /**
      * String that lets you know which zombie model this JVeggie is associated with.
      */
    private String ID;

    /**
      * Function returning the Zombie type associated with this JVeggie.
      * @return the Veggie type of JVeggie.
     
     * @return  */
    public String getTypeZombie() {
        return typeZombie;
    }
    /**
      * Method initializing the zombie ID.
      * @param ID The ID saved in memory of this JVeggie.
     
     * @param ID */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
      * Function returning the ID of this JVeggie.
      * @return The ID of the JVeggie.
     
     * @return  */
    public String getID() {
        return ID;
    }

    /**
      * Method modifying the speed of this JVeggie.
      * @param speed New speed of JVeggie.
     
     * @param speed */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    /**
      * Constructor JVeggie.
      * @parameter posiX Float which represents the movement speed of the JVeggie.
      * @parameter typeZombie String containing the type of veggie that this JVeggie represents in order to associate him the good zombie of the model.
      * @parameter noZombie Integer representing the zombie number of the JVeggie in order to associate him the good zombie of the model.
      * @parameter Controller Controller.
     
     * @param posiX
     * @param typeZombie
     * @param noZombie
     * @param controller */
    public JVeggie(float posiX, String typeZombie, int noZombie, final PvZController controller) {
        Image img;
        for (int i = 0; i < 4; i++) {
            img = Toolkit.getDefaultToolkit().getImage(typeZombie + (i + 1) + ".gif");
            walkList.add(img);
        }

        for (int i = 0; i < 4; i++) {
            img = Toolkit.getDefaultToolkit().getImage(typeZombie + "Mange"+ (i + 1) + ".gif");
            eatList.add(img);
        }

        for (int i = 0; i < 4; i++) {
            img = Toolkit.getDefaultToolkit().getImage(typeZombie +"Penche"+ (i + 1) + ".gif");
            walkLeaning.add(img);
        }

        this.CurrentImg = walkList.get(0);
        this.posiX = posiX;
        this.setSize(110, 100);
        this.setLocation((int) posiX, 0);
        this.typeZombie = typeZombie;
        this.noZombie = noZombie;
        this.ID = typeZombie + noZombie;
        this.controller = controller;
        controller.creeZombie(typeZombie, ID, noZombie);
    }


    /**
      * Method modifying and moving the JVeggie according to its zombie of the associated model.
      */
    public void actualize() {
        if (this instanceof LimboVeggie && controller.getTemps()%30==0) {
            controller.playSound("untz.wav");
        }
        move();
        if (controller.getTemps() % 12 == 0) {
            if (CurrentIndexImg == 3) {
                CurrentIndexImg = 0;
            } else if (controller.getZombie(ID).getState()!=Zombie.EtatZombie.trap) {
                CurrentIndexImg++;
            }
            switch (controller.getZombie(ID).getState()) {
                case trap:
                    CurrentImg = walkList.get(CurrentIndexImg);
                    break;
                case Standing:
                    CurrentImg = walkList.get(CurrentIndexImg);
                    break;
                case eat:
                    CurrentImg = eatList.get(CurrentIndexImg);
                    break;
                case limboMode:
                    CurrentImg = walkLeaning.get(CurrentIndexImg);
                    break;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(CurrentImg, 0, 0, this);
    }

    /**
      * Method that changes the position of the JVeggie in the Lane according to its speed.
      */
    public void move() {
        posiX += speed;
        this.setLocation((int) posiX, this.getY());
    }
}