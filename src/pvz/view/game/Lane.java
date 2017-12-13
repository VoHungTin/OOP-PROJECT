/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pvz.view.game;

import pvz.view.image.JVeggie;
import pvz.view.image.CoachVeggie;
import pvz.view.image.VeggieOrdinaire;
import pvz.view.image.GrosVeggie;
import pvz.view.image.LimboVeggie;
import pvz.view.projectiles.JProjectile;
import pvz.view.projectiles.Peas;
import pvz.view.plant.JPlant;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import pvz.controller.PvZController;
import pvz.view.plant.PlantePiege;


public class Lane extends JComponent {


    private long dateDerniereUpdate = 0;
  
    private Random rand = new Random();
  
    private PvZController controller;
  
    private JZonePlantable[] cases = new JZonePlantable[9];
  
    private ArrayList<JProjectile> ProjectileList = new ArrayList<>();
  
    private ArrayList<JVeggie> Zombieslist = new ArrayList<>();
 
    private int noLane;
   
    private PnlGameField gamefield;

  
    public void addProjectile(JProjectile projectile) {
        this.ProjectileList.add(projectile);
        this.add(projectile);
    }

  
    public int getNoLane() {
        return noLane;
    }

  
    protected Lane(final PvZController controller, final PnlGameField gamefield, int noLane) {
        this.gamefield = gamefield;
        this.noLane = noLane;
        this.controller = controller;
        this.setSize(85 * 9, 130);
        for (int i = 0; i < 9; i++) {
            cases[i] = new JZonePlantable(controller, gamefield, i, this);
            this.add(cases[i]);
            cases[i].setLocation(3 + i * 85, 3);
        }
    }

  
    private void JZPsrender(boolean resteZombies) {
        for (int i = 0; i < cases.length; i++) {
            cases[i].actualiser(!Zombieslist.isEmpty(), resteZombies);
        }
    }


    private void Projectilesremover() {
        for (int i = 0; !(ProjectileList.isEmpty()) && i < ProjectileList.size(); i++) {
            JProjectile projectile = ProjectileList.get(i);
            if (projectile instanceof Peas) {
                ((Peas) projectile).actualiser();
            }
            if (projectile.getX() > 1016) {
                this.remove(projectile);
                ProjectileList.remove(i);
                i--;
            }
        }
    }

  
    private void BonusZombies() {
        boolean zombiesEncourages = false;
        boolean zombiesPenches = false;
        for (int i = 0; !(Zombieslist.isEmpty()) && (!(zombiesEncourages && zombiesPenches)) && i < Zombieslist.size(); i++) {
            if ((controller.getZombie(Zombieslist.get(i).getID()).getTypeZombie()).equalsIgnoreCase("CV")) {
                zombiesEncourages = true;
            } else if ((controller.getZombie(Zombieslist.get(i).getID()).getTypeZombie()).equalsIgnoreCase("LV")) {
                zombiesPenches = true;
            }
        }

        for (int i = 0; i < Zombieslist.size(); i++) {
            JVeggie veggie = Zombieslist.get(i);
            if (zombiesEncourages && controller.getZombie(veggie.getID()).isSonicModePossible()) {
                veggie.setVitesse(-0.56666F);
            } else {
                veggie.setVitesse(-0.28333F);
            }
            if (!controller.getZombie(veggie.getID()).isMange() && !controller.getZombie(veggie.getID()).isPiege()) {
                if (zombiesPenches && controller.getZombie(veggie.getID()).isLimboModePossible()) {
                    controller.setEtat(controller.getZombie(veggie.getID()), "limboMode");
                } else {
                    controller.setEtat(controller.getZombie(veggie.getID()), "debout");
                }
            }
        }
    }

  
    private boolean jeterZombie(JVeggie veggie) {
        boolean isJeter = false;
        if (controller.getZombie(veggie.getID()).getpV() <= 0) {
            if (controller.getZombie(veggie.getID()).isPiege()) {
                controller.setEtat(controller.getZombie(veggie.getID()).getPlantePiege(), "desactive");
            }
            controller.getListeZombies().remove(controller.getZombie(veggie.getID()));
            this.remove(veggie);
            Zombieslist.remove(veggie);
            isJeter = true;
            gamefield.moreDestruct();
        } else if (veggie.getX() == 0) {
            String messageGameOver = "Game Over! Zombie Over Fences";
            JOptionPane.showMessageDialog(null, messageGameOver);
            System.exit(0);
        }
        return isJeter;
    }


    private void jeterPlante(JPlant plante, int index) {
        if (controller.getPlante(plante.getCoordYX()).getpV() <= 0) {
            controller.getListePlantes().remove(controller.getPlante(plante.getCoordYX()));
            cases[index].setEmptyCase(true);
            cases[index].remove(plante);
        }
    }

   
    private synchronized void Zombies() {
        BonusZombies();
        for (int i = 0; !(Zombieslist.isEmpty()) && i < Zombieslist.size(); i++) {
            JVeggie veggie = Zombieslist.get(i);
            if (!jeterZombie(veggie)) {
                if (controller.getZombie(veggie.getID()).isPiege()) {
                    veggie.setVitesse(0);
                }
                testCollision(veggie);
                veggie.actualiser();
            }
        }
    }

 
    private void testCollision(JVeggie veggie) {
        testCollisionProjectile(veggie);
        testCollisionPlant(veggie);
    }

  
    private void testCollisionProjectile(JVeggie veggie) {
        Rectangle zoneVeggie = new Rectangle(veggie.getX(), veggie.getY(), 40, veggie.getHeight());
        for (int i = 0; !ProjectileList.isEmpty() && i < ProjectileList.size(); i++) {
            JProjectile projectile = ProjectileList.get(i);
            Rectangle zoneProjectile = new Rectangle(projectile.getBounds());
            if (zoneVeggie.intersects(zoneProjectile) && !controller.getZombie(veggie.getID()).isLimboMode()) {
                if (projectile instanceof Peas) {
                    if (!controller.getZombie(veggie.getID()).isLimboMode()) {
                        controller.playSound("poc.wav");
                        controller.endommageZombie(veggie.getID(), projectile.getPlanteAssocie());
                    }
                }
                this.remove(projectile);
                ProjectileList.remove(i);
            }
        }
    }

 
    private void testCollisionPlant(JVeggie veggie) {
        Rectangle zoneVeggie = new Rectangle(veggie.getX(), veggie.getY(), 40, veggie.getHeight());
        for (int i = 0; i < cases.length; i++) {
            Rectangle zoneCase = new Rectangle(cases[i].getBounds());
            if (zoneVeggie.intersects(zoneCase) && cases[i].getX() <= veggie.getX()) {
                if (cases[i].getDangerCase()) {
                    controller.endommageZombie(veggie.getID(), "LanceMelon");
                }
                Rectangle zoneVulnerablePlante = new Rectangle(cases[i].getX() + 30, cases[i].getY() + 10, 20, 5);
                if (!cases[i].getEmptyCase() && zoneVeggie.intersects(zoneVulnerablePlante)) {
                    if (cases[i].getPlantCase() instanceof PlantePiege) {
                        PlantePiege plantePiegeTouchee = (PlantePiege) cases[i].getPlantCase();
                        if (controller.getPlante(plantePiegeTouchee.getCoordYX()).isDesactive()) {
                            controller.playSound("paf.wav");
                            controller.getZombie(veggie.getID()).setPlantePiege(controller.getPlante(plantePiegeTouchee.getCoordYX()));
                            controller.setEtat(controller.getPlante(plantePiegeTouchee.getCoordYX()), "active");
                            controller.setEtat(controller.getZombie(veggie.getID()), "piege");

                        } else if (!controller.getZombie(veggie.getID()).isPiege()) {
                            controller.setEtat(controller.getZombie(veggie.getID()), "mange");
                            veggie.setVitesse(0);
                            controller.endommagePlante(plantePiegeTouchee.getCoordYX(), veggie.getTypeZombi());
                            if (controller.getPlante(plantePiegeTouchee.getCoordYX()).getpV() % 60 == 0) {
                                controller.playSound("croc.wav");
                            }
                            jeterPlante(plantePiegeTouchee, i);
                        }
                    } else {
                        controller.setEtat(controller.getZombie(veggie.getID()), "mange");
                        veggie.setVitesse(0);
                        JPlant planteAttaquee = cases[i].getPlantCase();
                        controller.endommagePlante(planteAttaquee.getCoordYX(), veggie.getTypeZombi());
                        if (controller.getPlante(planteAttaquee.getCoordYX()).getpV() % 60 == 0) {
                            controller.playSound("croc.wav");
                        }
                        jeterPlante(planteAttaquee, i);
                    }
                }
                if (cases[i].getEmptyCase() && (controller.getZombie(veggie.getID()).isPiege()
                        || controller.getZombie(veggie.getID()).isMange())) {
                    controller.setEtat(controller.getZombie(veggie.getID()), "debout");
                    veggie.setVitesse(-0.28333F);
                }
            }
        }
    }

  
    protected void actualiser(boolean resteZombies) {
        if (controller.getTemps() == dateDerniereUpdate + 1) {
            JZPsrender(resteZombies);
            if (!ProjectileList.isEmpty()) {
                Projectilesremover();
            }
            if (!Zombieslist.isEmpty()) {
                Zombies();
            }
            dateDerniereUpdate = controller.getTemps();
        } else {
            System.out.println("PROBLEM");
            System.out.println("Thread Error!");
        }
    }

   
    public boolean ifZombieinThisLane() {
        return !Zombieslist.isEmpty();
    }

  
    protected void generateNewZombie(int noVague) {
        if (noVague <= 1) {
            VeggieOrdinaire veggie = new VeggieOrdinaire(this.getWidth(), controller.getNumeroZombie(), controller);
            ajouterZombie(veggie);
        } 
        else if (noVague <= 3) {
            switch (rand.nextInt(2)) {
                case 0:
                    VeggieOrdinaire veggie = new VeggieOrdinaire(this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(veggie);
                    break;
                case 1:
                    LimboVeggie limboVeggie = new LimboVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(limboVeggie);
                    break;
            }
        } 
        else if (noVague <= 5) {
            switch (rand.nextInt(3)) {
                case 0:
                    VeggieOrdinaire veggie = new VeggieOrdinaire(this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(veggie);
                    break;
                case 1:
                    LimboVeggie limboVeggie = new LimboVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(limboVeggie);
                    break;
                case 2:
                    CoachVeggie coachVeggie = new CoachVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(coachVeggie);
                    break;
            }
        } else if (noVague <= 9) {
            switch (rand.nextInt(4)) {
                case 0:
                    VeggieOrdinaire veggie = new VeggieOrdinaire(this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(veggie);
                    break;
                case 1:
                    LimboVeggie limboVeggie = new LimboVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(limboVeggie);
                    break;
                case 2:
                    CoachVeggie coachVeggie = new CoachVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(coachVeggie);
                    break;
                case 3:
                    GrosVeggie grosVeggie = new GrosVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(grosVeggie);
                    break;
            }
        } else {
            switch (rand.nextInt(4)+1) {
                case 0:
                    GrosVeggie grosVeggie = new GrosVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(grosVeggie);
                    break;
                case 1:
                    LimboVeggie limboVeggie = new LimboVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(limboVeggie);
                    break;
                case 2:
                    CoachVeggie coachVeggie = new CoachVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(coachVeggie);
                    break;
                case 3:
                    GrosVeggie grosVeggie2 = new GrosVeggie((float) this.getWidth(), controller.getNumeroZombie(), controller);
                    ajouterZombie(grosVeggie2);
                    break;
            }
        }
    }

  
    private void ajouterZombie(JVeggie veggie) {
        this.Zombieslist.add(veggie);
        this.add(veggie);
    }

  
    public JZonePlantable getCase(int index) {
        return this.cases[index];
    }
}
