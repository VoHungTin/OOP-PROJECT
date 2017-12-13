
package pvz.view;

import pvz.controller.FntPrincipal;
import pvz.controller.PvZController;


public class MasterThread extends Thread {

   
    private int sleepTime;
    
    private long currentFrameNumber;
    
    private PvZController controller;
    
    private FntPrincipal fntPrincipal;

    
    public MasterThread(final PvZController controller, final FntPrincipal fntPrincipal) {
        this.fntPrincipal = fntPrincipal;
        this.controller = controller;
        this.sleepTime = 1000 / controller.getFPS();
        this.currentFrameNumber = 0;
        this.start();
    }

    public MasterThread(PvZController aThis, FntPrincipal fntPrincipal) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.sleep(sleepTime);
                this.currentFrameNumber++;
                fntPrincipal.miseAJourFenetre();

            } catch (InterruptedException ex) {
                System.out.println("InterruptedException error: ");
                ex.printStackTrace();
            }

        }
    }

    
    public long getCurrentFrameNumber() {
        return this.currentFrameNumber;
    }
}
