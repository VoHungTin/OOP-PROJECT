/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pvz.view;

import pvz.controller.PvZController;


public class MasterThread extends Thread {

    /*
     *Integer which represents the time that the thread must wait
     *before calling the other methods in the run () function.
     */
    private int sleepTime;
    /*
     * Long that represents the image to which the thread is rendered.
     */
    private long currentFrameNumber;
    /*
     * Declaring a PvZController variable that allows the thread to communicate with the controller.
     */
    private PvZController controller;
    /*
     * Declaration of the main window to communicate with it.
     */
    private FntPrincipal fntPrincipal;

    /**
     * Constructor of the thread where the initializes the main window, the controller,
     * the sleeptime and the counter of the current image.
     * The start () method is also called for the thread to start.
     *
     * @param controller Initializes the controller and thus establishes
     * communication between the two.
     * @param fntPrincipal Initializes the main window and thus establishes 
     * communication between the two.
     */
    public MasterThread(final PvZController controller, final FntPrincipal fntPrincipal) {
        this.fntPrincipal = fntPrincipal;
        this.controller = controller;
        this.sleepTime = 1000 / controller.getFPS();
        this.currentFrameNumber = 0;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.sleep(sleepTime);
                this.currentFrameNumber++;
                fntPrincipal.windowUpdate();

            } catch (InterruptedException ex) {
                System.out.println("InterruptedException error: ");
                ex.printStackTrace();
            }

        }
    }

    /**
     * Function that returns the counter of the current image when it is called.
     
     * @return Long that represents the counter of the current image.
     */
    public long getCurrentFrameNumber() {
        return this.currentFrameNumber;
    }
}
