/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pvz.view.store;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import pvz.controller.PvZController;
import pvz.vue.terrainjeu.PnlTerrainJeu;


public class CashMoney extends JComponent {

    
    private boolean isActivated;
    
    private PvZController controller;
   
    private int limitInvisible = -100;
    
    private int limitVisible = 0;
   
    private ImageIcon imgActif = new ImageIcon("CashMoney(100x100).gif");
    
    private ImageIcon imgInactif = new ImageIcon("CashMoney2(100x100).gif");
   
    private ImageIcon currentImg;
    
    private Random rnd = new Random();
   
    private int rndInt = rnd.nextInt(554) + 130;
    
    private PnlTerrainJeu pnlTerrainJeu;

    
    public CashMoney(final PvZController controleur, final PnlTerrainJeu pnlTerrainJeu) {
        this.pnlTerrainJeu = pnlTerrainJeu;
        this.controller = controller;
        this.isActivated = true;
        currentImg = imgActif;
        this.setSize(100, 100);
        this.setLocation(-100, rndInt);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isActivated) {
                    controleur.playSound("kaching.wav");
                    controleur.modCash(25);
                    ((CashMoney) e.getSource()).isActivated = false;
                    currentImg = imgInactif;
                }
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentImg.getImage(), 0, 0, this);
    }

    
    public void actualiser() {
        
        int intervalleRespawn = controller.getFPS() * 30;
       
        int tempsExistence = controller.getFPS() * 10;
        if (isActivated && controller.getTemps() % tempsExistence == 0) {
            isActivated = false;
        }
        if (controller.getTemps() % intervalleRespawn == 0) {
            rndInt = rnd.nextInt(554) + 130;
            this.isActivated = true;
            currentImg = imgActif;
        }

        int posiX = this.getX();
        int posiY = rndInt;
        if (isActivated) {
            posiX += 3;
            if (posiX > limitVisible) {
                posiX = limitVisible;
            }
        } else if (!isActivated) {
            posiX -= 3;
            if (posiX < limitInvisible) {
                posiX = limitInvisible;
            }
        }
        this.setLocation(posiX, posiY);
    }
}
