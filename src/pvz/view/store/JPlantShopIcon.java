package pvz.view.store;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import pvz.controller.PvZController;
import pvz.view.game.PnlGameField;

/**
 * Classe qui représente les icônes des plantes dans le magasin.
 *
 * <p>Elle dessine les images qui servent à représenter les plantes dans le
 * magasin et à les animer lorsqu'on met la souris dessus.</p>
 *
 * @author Xavier Reid et Philippe Marcotte
 */
public class JPlantShopIcon extends JComponent {

    /*
     * Integer représentant le prix à payer pour placer une plante.
     */
    private int price;
    /*
     * String représentant le nom de plante que le joueur achète.
     */
    private String name;
    /*
     * Boolean représentant si la plante est sélectionné ou non.
     */
    private boolean Selection;
    /*
     * Boolean représentant le fait que la souris soit dessus l'icône d'une plante ou non.
     */
    private boolean Mouseover = false;
    /*
     * Déclaration du panneau principal pour établir la communication entre les deux.
     */
    private PnlGameField pnlGameField;
    /*
     * Contient l'image d'une plante lorsqu'elle n'est plas sélectionnée.
     */
    private Image img1;
    /*
     * Contient l'image d'une plante que la classe va alterner avec l'img1 lorsque le joueur sélectionne une plante.
     */
    private Image img2;
    /*
     * Contient l'image qui doit être dessinée au moment de l'appel de la variable.
     */
    private Image imgCurrent;
    /*
     * Contient le texte qui décrit la plante sélectionnée.
     */
    private JLabel MouseOverLabel;

    /**
     * Constructeur des icônes qui vont être placées dans le magasin. Il
     * initialise toutes les variable nécessaires pour gérer les achats des
     * plantes et les différents effets visuels lorsqu'on sélectionne une
     * plante.
     *
     * @param nom String représentant le nom de plante que le joueur achète.
     * @param prix Integer représentant le prix à payer pour placer une plante.
     * @param pnlGameField Permet d'initialiser la fenêtre principale et,
     * ainsi, établir la communication entre les deux.
     * @param texteSourisDessus String qui représente le texte que le JLabel
     * MouseOverLabel doit afficher.
     */
    public JPlantShopIcon(final String nom, final int prix, final PnlGameField pnlGameField, String label) {
        MouseOverLabel = new JLabel(label);
        this.pnlGameField = pnlGameField;
        this.setSize(90, 110);
        this.name = name;
        this.price = price;
        this.pnlGameField = pnlGameField;
        img1 = Toolkit.getDefaultToolkit().getImage(nom + "1.gif");
        img2 = Toolkit.getDefaultToolkit().getImage(nom + "2.gif");
        imgCurrent = img1;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                JPlantShopIcon jpsiSource = ((JPlantShopIcon) me.getSource());
                jpsiSource.Mouseover = true;
                pnlGameField.add(jpsiSource.getMouseOverLabel());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                JPlantShopIcon jpsiSource = ((JPlantShopIcon) me.getSource());
                jpsiSource.Mouseover = false;
                pnlGameField.remove(jpsiSource.getMouseOverLabel());
            }
        });
    }

    /**
     * Fonction qui renvoie le texte qui est contenu dans le JLabel.
     *
     * @return String représentant le texte qui doit être afficher lorsqu'une
     * plante est sélectionnée,
     */
    public JLabel getMouseOverLabel() {
        return MouseOverLabel;
    }

    /**
     * Fonction qui renvoie si oui ou non la plante est sélectionnée.
     *
     * @return Boolean qui true si la plante est sélectionnée et false si elle
     * ne l'est pas.
     */
    public boolean getSelection() {
        return Selection;
    }

    /**
     * Méthode qui permet de changer le fait qu'une plante est sélectionnée ou
     * non.
     *
     * @param Selection Boolean représentant si la plante est sélectionné
     * ou non.
     */
    public void setSelection(boolean Selection) {
        this.Selection = Selection;
    }

    /**
     * Fonction qui renvoie le nom de l'icône de la plante sélectionnée.
     *
     * @return String qui représente le nom de la plante sélectionnée.
     */
    public String getName() {
        return name;
    }

    /**
     * Fonction qui renvoie le prix de la plante sélectionnée.
     *
     * @return Integer qui représente le prix de la plante achetée.
     */
    public int getPrice() {
        return price;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgCurrent, 0, 0, this);
    }

    /**
     * Permet l'alternation entre les deux images de chaque plante et, ainsi,
     * crée une animation lorsqu'un plante est sélectionnée.
     */
    public void actualiser() {
        if (Selection || Mouseover) {
            if (imgCurrent == img1) {
                imgCurrent = img2;
            } else {
                imgCurrent = img1;
            }
        }
    }
}
