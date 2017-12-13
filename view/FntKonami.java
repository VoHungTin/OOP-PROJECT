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


public class FntKonami extends JFrame {

   
    private PvZController pvZcontroleur;

    
    public FntKonami(final PvZController controleur) {
        this.setTitle("Congratulations!");
        this.pvZcontroleur = controleur;
        JPanel pnl = new JPanel();
        pnl.setSize(431, 305);
        pnl.setPreferredSize(new Dimension(431, 305));
        pnl.setLayout(null);
        ImageIcon img = new ImageIcon("Cheat.gif");
        JLabel spam = new JLabel(img);
        spam.setSize(431, 305);
        spam.setLocation(0, 0);

        JButton boutonClaim = new JButton("Click here to claim prize!!!");
        boutonClaim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot((JButton) e.getSource());
                frame.setVisible(false);
                pvZcontroleur.modCash(999999);
            }
        });
        boutonClaim.setSize(new Dimension(300, 25));
        pnl.add(boutonClaim);
        pnl.add(spam);



        boutonClaim.setLocation(pnl.getWidth() / 2 - boutonClaim.getWidth() / 2, 275);
        this.add(pnl);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        controleur.playSound("error.wav");
    }
}
