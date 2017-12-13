package pvz.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import pvz.controller.PvZController;

/*
 * Class that is used to capture when a player makes a combination of
 * keyboard to enter a cheat code.
 
 * Thanks to its KeyListener, this class will record keys that the
 * player enters and compare them to the predefined cheat codes. If the
 * key combination corresponds to one of the codes, then any effect follows

  */
public class CheatCodeListener implements KeyListener {

    /*
     * ArrayList containing the last 11 characters entered on the keyboard. 
     * This is useful when you want to know if the player has entered the Konami Code.
     */
    private ArrayList<Integer> last11KeyPressed;
    /*
     * ArrayList of characters representing the Konami Code.
     */
    private ArrayList<Integer> konamiCode;
    /*
     * ArrayList of characters that is used to compare the keys entered by the player with the two other cheat codes: Galarneau and Monsante.
     */
    private ArrayList<Character>enterPassword = new ArrayList<>();
    /*
     * String representing the galarneau cheat code.
     */
    private String silverCheat = "galarneau";
    /*
     * String representing the Montsante cheat code.
     */
    private String hardMode = "Monsante";
    /*
     * Declaring a PvZController variable that allows the thread to communicate with the controller.
     */
    private PvZController controller;

    /**
     * Constructor of the class that tests the entered characters. It initializes the controller and 
     * establishes communication between the two. It also initializes what the Konami Code 
     * is. Finally, it instantiates the list of the last 11 characters entered.
     
     * @param controller Initializes the controller and thus 
     * establishes communication between the two.
     */
    public CheatCodeListener(final PvZController controller) {
        this.controller = controller;
        konamiCode = new ArrayList<Integer>();
        konamiCode.add(KeyEvent.VK_UP);
        konamiCode.add(KeyEvent.VK_UP);
        konamiCode.add(KeyEvent.VK_DOWN);
        konamiCode.add(KeyEvent.VK_DOWN);
        konamiCode.add(KeyEvent.VK_LEFT);
        konamiCode.add(KeyEvent.VK_RIGHT);
        konamiCode.add(KeyEvent.VK_LEFT);
        konamiCode.add(KeyEvent.VK_RIGHT);
        konamiCode.add(KeyEvent.VK_B);
        konamiCode.add(KeyEvent.VK_A);
        konamiCode.add(KeyEvent.VK_ENTER);
        this.last11KeyPressed = new ArrayList<Integer>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!(silverCheat.indexOf(e.getKeyChar()) == -1) || !(hardMode.indexOf(e.getKeyChar()) == -1)) {
           enterPassword.add(e.getKeyChar());
        }
        if (enterPassword.size() > 9) {
           enterPassword.remove(0);
        }
        String passString = "";
        for (int i = 0; i <enterPassword.size(); i++) {
            passString +=enterPassword.get(i);
        }
        if (passString.equals(silverCheat)) {
            controller.modCash(100);
        } else if (passString.contains(hardMode)) {
            controller.activateHardMode();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (last11KeyPressed.size() == 11) {
            last11KeyPressed.remove(0);
        }
        last11KeyPressed.add(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            boolean matches = true;
            for (int i = 0; i < konamiCode.size() && matches; i++) {
                if (last11KeyPressed.get(i) != konamiCode.get(i)) {
                    matches = false;
                }
            }
            if (matches) {
                FntKonami fntKonami = new FntKonami(controller);
            }
        }
    }
}
