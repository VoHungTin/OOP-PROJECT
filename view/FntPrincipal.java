package pvz.view;

import pvz.view.terrainjeu.PnlTerrainJeu;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import pvz.controller.PvZController;


public class FntPrincipal extends JFrame {

    
    private PnlTerrainJeu pnlTerrainJeu;
    
    private PvZController controller;

    
    public FntPrincipal(final PvZController controller) {
        this.controller = controller;
        pnlTerrainJeu = new PnlTerrainJeu(controller);
        this.add(pnlTerrainJeu);
        pnlTerrainJeu.setLocation(0, 0);
        this.setTitle("Plantes vs Zombies");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(new CheatCodeListener(controller));
        this.pack();
    }

   
    protected void miseAJourFenetre() {
        pnlTerrainJeu.miseAJourPnlTerrainJeu();
    }
}
