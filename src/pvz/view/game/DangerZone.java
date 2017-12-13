/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pvz.view.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComponent;

public class DangerZone extends JComponent {

    private Image img1 = Toolkit.getDefaultToolkit().getImage("ZoneDangereuse0.gif");
    private Image img2 = Toolkit.getDefaultToolkit().getImage("ZoneDangereuse1.gif");
    private int indexImgCurrent = 0;
    private Image imgCurrent;
    

    public DangerZone() {
        this.setSize(79, 124);
        imgCurrent = img1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgCurrent, 0, 0, this);
    }

   
    public void actualiser() {
        if (indexImgCurrent == 0) {
            indexImgCurrent = 1;
            imgCurrent=img1;
        } else {
            indexImgCurrent = 0;
            imgCurrent=img2;
        }
    }
}
