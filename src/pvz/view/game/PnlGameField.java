package pvz.view.game;

import java.awt.Color;
import pvz.view.store.CashMoney;
import pvz.view.store.JPlantShopIcon;
import pvz.view.plant.JPlant;
import pvz.view.plant.Tournesol;
import pvz.view.plant.TirePois;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pvz.controller.PvZController;
import pvz.view.plant.Peanut;
import pvz.view.plant.PlantePiege;
import pvz.view.projectiles.BouletCochon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class PnlGameField extends JPanel {

  
    private int zombiDuRound;
 
    private int zombisApparusCeRound = 0;
   
    private long tempsOuProchainZombieAttaque;
   
    private JLabel imgBackgroundShop = new JLabel(new ImageIcon("ShopBackground.gif"));
   
    private JLabel imgBackground = new JLabel(new ImageIcon("TerrainJeu(1016x784).gif"));
  
    private CashMoney cashMoney;
  
    private PvZController controleur;
 
    private Random rand = new Random();
  
    private JLabel Cash = new JLabel();
  
    private JPlantShopIcon[] Purchasable = new JPlantShopIcon[4];
   
    private Lane[] ComPoLane = new Lane[5];
   
    private int DestroySize = 0;
   
    private BulletTrajectory trajectory;
    
    private JLabel LabelofDestroy = new JLabel();
    
    private int noVague = 1;
  
    private JLabel LabelCurrentVague = new JLabel();
  
    private JZoneExplosions jZoneExplosions = new JZoneExplosions();

  
    public void moreDestruct() {
        DestroySize++;
    }


    public PnlGameField(final PvZController controleur) {
        this.controleur = controleur;
        controleur.playSound("Catgroove.wav");
        this.tempsOuProchainZombieAttaque = controleur.getFPS() * 30;
        this.zombiDuRound = controleur.getNbZombies(noVague);
        this.setLayout(null);
        this.setSize(1016, 784);
        this.setPreferredSize(new Dimension(1016, 784));
        this.cashMoney = new CashMoney(controleur, this);
        this.add(cashMoney);
        Cash.setText(controleur.getCash() + "$");
        Cash.setFont(new Font("Ravie", 100, 15));
        Cash.setSize(200, 200);
        this.add(Cash);
        Cash.setLocation(20, 10);

        this.trajectory = new BulletTrajectory(this.getSize());
        this.add(trajectory);
        this.trajectory.setLocation(0, 0);
        this.add(LabelofDestroy);
        LabelofDestroy.setForeground(new Color(49, 168, 87));
        LabelofDestroy.setFont(new Font("Ravie", 100, 12));
        LabelofDestroy.setSize(290, 70);
        LabelofDestroy.setLocation(600, 10);
        this.add(LabelCurrentVague);
        LabelCurrentVague.setForeground(new Color(49, 168, 87));
        LabelCurrentVague.setFont(new Font("Ravie", 100, 12));
        LabelCurrentVague.setSize(230, 70);
        LabelCurrentVague.setLocation(660, 60);
        for (int i = 0; i < 5; i++) {
            ComPoLane[i] = new Lane(controleur, this, i);
            this.add(ComPoLane[i]);
            ComPoLane[i].setLocation(230, 99 + i * 130);
        }

        this.add(jZoneExplosions);
        jZoneExplosions.setLocation(230, 99);

        Purchasable[0] = new JPlantShopIcon("Tournesol", 50, this, "<html> Cout: 50 <br> Donne 25 $CAN aux 30 secondes. Plutot rentable...");
        Purchasable[1] = new JPlantShopIcon("PlantePiege", 75, this, "");
        Purchasable[2] = new JPlantShopIcon("Peashooter", 100, this, "");
        Purchasable[3] = new JPlantShopIcon("LanceCochon", 150, this, "");
        for (int i = 0; i < Purchasable.length; i++) {
            Purchasable[i].addMouseListener(new PnlGameField.GestionnaireEvenements());
            this.add(Purchasable[i]);
            Purchasable[i].setLocation(150 + i * 120, 10);
        }

        this.add(imgBackgroundShop);
        imgBackgroundShop.setSize(900, 130);
        imgBackgroundShop.setLocation(0, 0);
        this.add(imgBackground);
        imgBackground.setLocation(0, 0);
        imgBackground.setSize(1000, 784);
    }

  
    public void updateGameField() {
        Cash.setText(controleur.getCash() + "$");
        LabelofDestroy.setText("Number of zombies killed : " + DestroySize);

        cashMoney.actualiser();
        trajectory.actualiser();
        Boolean ZombieRemain = false;
        for (int i = 0; !ZombieRemain && i < ComPoLane.length; i++) {
            if (ComPoLane[i].ifZombieinThisLane()) {
                ZombieRemain = true;
            }
        }
        for (int i = 0; i < ComPoLane.length; i++) {
            ComPoLane[i].actualiser(ZombieRemain);
        }
        actualiserJpsiSelection();
        verifierSiZombiesAttaquent();

        if (controleur.getTemps() % 12 == 0) {
            for (int i = 0; i < Purchasable.length; i++) {
                Purchasable[i].actualiser();
            }
        }
        if (controleur.getTemps()%((5*60+10)*60)==0) {
            controleur.playSound("Catgroove.wav");
        }
        this.invalidate();
        this.repaint();
    }

  
    public void ClicktoPlant(JZonePlantable jzp) {
        if (jzp.getEmptyCase()) {
            for (int i = 0; i < Purchasable.length; i++) {
                if (Purchasable[i].getSelection()) {
                    controleur.modCash(-(Purchasable[i].getPrice()));

                    JPlant nouvPlant = null;
                    switch (Purchasable[i].getName()) {
                        case "Tournesol":
                            nouvPlant = (JPlant) new Tournesol(controleur, jzp);
                            jzp.plPlant(nouvPlant);
                            break;
                        case "Peashooter":
                            nouvPlant = (JPlant) new TirePois(controleur, jzp);
                            jzp.plPlant(nouvPlant);
                            break;
                        case "LanceCochon":
                            nouvPlant = (JPlant) new Peanut(controleur, jzp, this);
                            jzp.plPlant(nouvPlant);
                            break;
                        case "PlantePiege":
                            nouvPlant = (JPlant) new PlantePiege(controleur, jzp);
                            jzp.plPlant(nouvPlant);
                            break;
                    }
                }
            }
        }
    }

  
    public void actualiserJpsiSelection() {
        for (int i = 0; i < Purchasable.length; i++) {
            if (Purchasable[i].getSelection() && Purchasable[i].getPrice() > controleur.getCash()) {
                unselectAllPlantShopIcons();
            }
        }
    }

  
    public void unselectAllPlantShopIcons() {
        for (int i = 0; i < Purchasable.length; i++) {
            Purchasable[i].setSelection(false);
        }
    }

   
    public void clicJPlantShopIcon(JPlantShopIcon jpsiSource) {
        unselectAllPlantShopIcons();
        if (jpsiSource.getPrice() <= controleur.getCash()) {
            jpsiSource.setSelection(true);
        }
    }

  
    public void verifierSiZombiesAttaquent() {
        if (controleur.getTemps() >= tempsOuProchainZombieAttaque) {
            if (zombisApparusCeRound < zombiDuRound / 2) {
                int noLane = rand.nextInt(5);
                ComPoLane[noLane].generateNewZombie(noVague);
                zombisApparusCeRound++;
                if (zombisApparusCeRound == 1) {
                    LabelCurrentVague.setText("Vague : " + noVague);
                }
                tempsOuProchainZombieAttaque += controleur.getFPS() * 5;
            } else {
                if (zombisApparusCeRound < zombiDuRound) {
                    int noLane = rand.nextInt(5);
                    ComPoLane[noLane].generateNewZombie(noVague);
                    zombisApparusCeRound++;
                    tempsOuProchainZombieAttaque += controleur.getFPS() * 0.5;
                }
                if (zombisApparusCeRound == zombiDuRound) {
                    zombisApparusCeRound = 0;
                    noVague++;
                    zombiDuRound = controleur.getNbZombies(noVague);
                    tempsOuProchainZombieAttaque = controleur.getTemps() + controleur.getFPS() * 30;
                }
            }
        }
    }

   
    public void lancerBouletCochon(JZonePlantable jzpOrigine) {
        int noLane = rand.nextInt(5);
        int noCase = rand.nextInt(9);
        BouletCochon bouletCochon = new BouletCochon(jzpOrigine, ComPoLane[noLane].getCase(noCase), trajectory);
        trajectory.addCochon(bouletCochon);
    }

  
    public JZoneExplosions getZoneExplosions() {
        return this.jZoneExplosions;
    }


  
    class GestionnaireEvenements extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JPlantShopIcon jpsiSource = (JPlantShopIcon) e.getSource();
            clicJPlantShopIcon(jpsiSource);
        }
    }
}