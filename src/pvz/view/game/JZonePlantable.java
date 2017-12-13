package pvz.view.game;

import pvz.view.plant.JPlant;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import pvz.controller.PvZController;


public class JZonePlantable extends JComponent {

   
    private boolean EmptyCase = true;
   
    private boolean DangerCase = false;
   
    private DangerZone DangerZone = new DangerZone();
  
    private long commun = 0;
  
    private PvZController controller;
  
    private JPlant plantCase;
 
    private PnlGameField gamefield;
  
    private int noCase;
   
    private Lane lane;

 
    public JZonePlantable(final PvZController controller, final PnlGameField gamefield, int noCase, Lane lane) {
        this.controller = controller;
        this.gamefield = gamefield;
        this.noCase = noCase;
        this.lane = lane;
        this.setSize(79, 124);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gamefield.ClicktoPlant((JZonePlantable) e.getSource());
            }
        });
    }

 
    public int getNoCase() {
        return noCase;
    }

 
    public Lane getLane() {
        return lane;
    }

 
    public JPlant getPlantCase() {
        return plantCase;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

 
    public boolean getEmptyCase() {
        return this.EmptyCase;
    }

 
    public void setEmptyCase(boolean EmptyCase) {
        this.EmptyCase = EmptyCase;
    }

   
    public void plPlant(JPlant plPlant) {
        this.plantCase = plPlant;
        this.add(plPlant);
        plPlant.setLocation(0, 0);
        this.EmptyCase = false;
    }

  
    public void actualiser(boolean zombieDansLaLane, boolean resteZombies) {
        if (!(EmptyCase)) {
            plantCase.actualiser(zombieDansLaLane, resteZombies);
        }
        if (DangerCase && controller.getTemps() >= commun) {
            DangerCase = false;
            gamefield.getZoneExplosions().endExplosion(DangerZone);
        }
        if (controller.getTemps() % 6 == 0) {
            DangerZone.actualiser();
        }
    }

 
    public void splat() {
        controller.playSound("boom.wav");
        if (!DangerCase) {
            DangerCase = true;
            gamefield.getZoneExplosions().addExplosion(this.getLane().getNoLane(), this.getNoCase(), DangerZone);
            commun = controller.getTemps() + 10 * controller.getFPS();
        }
        else {
            commun = controller.getTemps() + 10 * controller.getFPS();
        }
    }

 
    public boolean getDangerCase() {
        return DangerCase;
    }
}