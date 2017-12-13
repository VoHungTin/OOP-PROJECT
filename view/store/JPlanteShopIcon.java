package pvz.view.store;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import pvz.controller.PvZController;
import pvz.vue.terrainjeu.PnlTerrainJeu;


public class JPlanteShopIcon extends JComponent {

    
    private int price;
    
    private String name;
    
    private boolean isSelection;
    
    private boolean Mouseover = false;
    
    private PnlTerrainJeu pnlTerrainJeu;
   
    private Image img1;
    
    private Image img2;
    
    private Image imgActual;
    
    private JLabel lblTextMouseTop;

    
    public JPlanteShopIcon(final String name, final int price, final PnlTerrainJeu pnlTerrainJeu, String textMouseTop) {
        lblTextMouseTop = new JLabel(textMouseTop);
        this.pnlTerrainJeu = pnlTerrainJeu;
        this.setSize(90, 110);
        this.name = name;
        this.price = price;
        this.pnlTerrainJeu = pnlTerrainJeu;
        img1 = Toolkit.getDefaultToolkit().getImage(name + "1.gif");
        img2 = Toolkit.getDefaultToolkit().getImage(name + "2.gif");
        imgActual = img1;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                JPlanteShopIcon jpsiSource = ((JPlanteShopIcon) me.getSource());
                jpsiSource.Mouseover = true;
                pnlTerrainJeu.add(jpsiSource.getLblTextMouseTop());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                JPlanteShopIcon jpsiSource = ((JPlanteShopIcon) me.getSource());
                jpsiSource.Mouseover = false;
                pnlTerrainJeu.remove(jpsiSource.getLblTextMouseTop());
            }
        });
    }

    
    public JLabel getLblTextMouseTop() {
        return lblTextMouseTop;
    }

    
    public boolean getIsSelection() {
        return isSelection;
    }

    
    public void setIsSelection(boolean isSelection) {
        this.isSelection = isSelection;
    }

    
    public String getName() {
        return name;
    }

    
    public int getPrice() {
        return price;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgActual, 0, 0, this);
    }

    
    public void actualize() {
        if (isSelection || Mouseover) {
            if (imgActual == img1) {
                imgActual = img2;
            } else {
                imgActual = img1;
            }
        }
    }
}
