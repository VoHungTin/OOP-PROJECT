package pvz.view.game;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JComponent;
import pvz.view.projectiles.BouletCochon;


public class BulletTrajectory extends JComponent {

   
    private ArrayList<BouletCochon> BulletCochonList= new ArrayList<>();

   
    public BulletTrajectory(Dimension pnlSize) {
        this.setSize(pnlSize);
        this.setLayout(null);
    }

  
    public void addCochon(BouletCochon bouletCochon) {
        this.add(bouletCochon);
        bouletCochon.setLocation(bouletCochon.getPosiX(), bouletCochon.getPosiY());
        BulletCochonList.add(bouletCochon);
    }

  
    public void enleverMelon(BouletCochon bouletMelon) {
        this.remove(bouletMelon);
        BulletCochonList.remove(bouletMelon);
    }

   
    public void actualiser() {
        for (int i = 0; i < BulletCochonList.size(); i++) {
            BulletCochonList.get(i).actualiser();
        }
    }
}
