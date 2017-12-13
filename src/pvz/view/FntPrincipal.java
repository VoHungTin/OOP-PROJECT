package pvz.view;

import pvz.view.game.PnlGroundGame;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import pvz.controller.PvZController;

/**
 * Class that initializes all the visual aspect of the game.
 *
 * It creates the JPanel that contains the methods to make the game view work.
 * It makes the link in the controller and the rest of the view
 
 */
public class FntPrincipal extends JFrame {

    /*
     * Declaration of the field panel and then instantiate it in the constructor.
     */
    private PnlGroundGame pnlGroundGame;
    /*
     * Declaration of the controller that we will then instantiate in the constructor.
     */
    private PvZController controller;

    /**
     * Builder of the class where controller access is initialized for the rest of the view,
     * the panel that contains the visual appearance of the game
     * and the keyListener for the cheat codes.
     *
     * @param controller Variable type PvZController that allows access
     * to the controller in the rest of the view.
     */
    public FntPrincipal(final PvZController controller) {
        this.controller = controller;
        pnlGroundGame = new PnlGroundGame(controller);
        this.add(pnlGroundGame);
        pnlGroundGame.setLocation(0, 0);
        this.setTitle("Plants vs Zombies");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(new CheatCodeListener(controller));
        this.pack();
    }

    /**
     * Method that calls updating the main panel.
     */
    protected void windowUpdate() {
        pnlGroundGame.updatePnlGroundGame();
    }
}
