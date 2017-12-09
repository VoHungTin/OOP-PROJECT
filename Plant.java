package pvz.model;


public class Plant {

   
    private int pV;
   
    private int dmg;
   
    private String typePlant;
    
    private int[] coordYX = new int[2];
   
    private Plant.StatePlant state;

    
    protected Plant(String typePlant, int[] coordYX) {
        this.pV = 600;
        this.typePlant = typePlant;
        this.coordYX = coordYX;
        switch (typePlant) {
            case "TirePois":
                this.dmg = 60;
                this.state = Plant.StatePlant.pasApplicable;
                break;
            case "LanceCochon":
                this.dmg = 5;
                this.state = Plant.StatePlant.pasApplicable;
                break;
            case "Tournesol":
                this.dmg = 0;
                this.state = Plant.StatePlant.pasApplicable;
                break;
            case "PlantPiege":
                this.dmg = 0;
                this.pV = 300;
                this.state = Plant.StatePlant.desactive;
                break;
        }
    }

   
    public int getpV() {
        return pV;
    }

    public Plant.StatePlant getState() {
        return state;
    }

    
    public void setState(Plant.StatePlant state) {
        this.state = state;
    }

   
    protected int[] getCoordYX() {
        return coordYX;
    }

   
    protected final String toStringCoordYX() {
        return coordYX[0] + " " + coordYX[1];
    }

   
    protected void reducePV(int dmg) {
        this.pV -= dmg;
    }

    
    protected int getDmg() {
        return dmg;
    }

    
    protected String getType() {
        return typePlant;
    }

    public enum StatePlant {

       
        active,
       
        desactive,
        
        pasApplicable
    }

    
    public boolean isDesactive() {
        boolean isDesactive = false;
        if (this.getState() == Plant.StatePlant.desactive) {
            isDesactive = true;
        }
        return isDesactive;
    }
}
