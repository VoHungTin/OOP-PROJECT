package pvz.view.game;

import javax.swing.JComponent;


public class JZoneExplosions extends JComponent {

   
    public JZoneExplosions() {
        this.setSize(765, 650);
    }

  
    public void addExplosion(int noLane, int noCase, DangerZone DZone) {
        this.add(DZone);
        DZone.setLocation(85 * noCase, 130 * noLane);
    }

   
    public void endExplosion(DangerZone DZone) {
        this.remove(DZone);
    }
}
