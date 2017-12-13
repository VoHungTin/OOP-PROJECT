package pvz.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pvz.controller.PvZController;

/**
 * Class that contains the window that opens when Konami Code is enabled
 
 * This class will bring up a window containing an image saying that the player has won a 
 * lot of money. If he presses the "Claim the prize" button he will receive $ 999,999 in 
 * cash from the game.

 */
public class FntKonami extends JFrame {

    /*
     * Declaring a PvZController variable that allows the thread to communicate with the controller.
     * In this case the controller allows the window to tell the model to increase the player's money.
     */
    private PvZController pvZcontroller;

    /**
     * Consutructeur de la fenêtre expliquant que le joueur a gagné un prix. On
     * ne peut la faire afficher qu'en faisant le Konami code lorsque le jeu est
     * ouvert.
     *
     * @param controleur Permet d'initialiser le contrôleur et, ainsi, établir
     * la communication entre les deux.
     */
    public FntKonami(final PvZController controleur) {
        this.setTitle("Congratulations!");
        this.pvZcontroller = controleur;
        JPanel pnl = new JPanel();
        pnl.setSize(431, 305);
        pnl.setPreferredSize(new Dimension(431, 305));
        pnl.setLayout(null);
        ImageIcon img = new ImageIcon("Cheat.gif");
        JLabel spam = new JLabel(img);
        spam.setSize(431, 305);
        spam.setLocation(0, 0);

        JButton claimButton = new JButton("Click here to claim prize!!!");
        claimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot((JButton) e.getSource());
                frame.setVisible(false);
                pvZcontroller.modCash(999999);
            }
        });
        claimButton.setSize(new Dimension(300, 25));
        pnl.add(claimButton);
        pnl.add(spam);



        claimButton.setLocation(pnl.getWidth() / 2 - claimButton.getWidth() / 2, 275);
        this.add(pnl);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        controleur.playSound("error.wav");
    }
}
