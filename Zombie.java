package pvz.model;


public class Zombie {

    
    private int pV;
   
    private int dmg = 1;
    
    private String ID;
    
    private int noLane;
    
    private Zombie.StateZombie status;
    
    private boolean peutSonicMode = true;
    
    private boolean peutLimboMode = true;
    
    private String typeZombie;
    
    private Plant plantTrap;

    
    protected Zombie(String typeZombie, String ID, int noLane) {
        this.noLane = noLane;
        this.ID = ID;
        this.typeZombie = typeZombie;
        switch (typeZombie) {
            case "GV":
                this.pV = 1200;
                break;
            case "VO":
                this.pV = 600;
                break;
            case "LV":
                this.pV = 500;
                this.peutLimboMode = false;
                break;
            case "CV":
                this.pV = 900;
                this.peutSonicMode = false;
                break;
        }
    }
    
    public Plant getPlantePiege() {
        return plantTrap;
    }

    
    public void setPlantePiege(Plant plantePiege) {
        this.plantTrap = plantTrap;
    }

    
    public String getTypeZombie() {
        return typeZombie;
    }

    
    public boolean isSonicModePossible() {
        return this.peutSonicMode;
    }

    
    public boolean isLimboModePossible() {
        return this.peutLimboMode;
    }

    
    public Zombie.StateZombie getState() {
        return State;
    }

    
    public void setState(Zombie.StateZombie state) {
        this.State = State;
    }

    
    public enum StateZombie {

        
        trap,
        
        many,
        
        stand,
        
        limboMode
    }

   
    public boolean isTrap() {
        boolean isTrap = false;
        if (this.getState() == Zombie.StateZombie.trap) {
            isTrap = true;
        }
        return isTrap;
    }

    
    public boolean isMange() {
        boolean isMange = false;
        if (this.getState() == Zombie.StateZombie.mange) {
            isMange = true;
        }
        return isMange;
    }

    public boolean isLimboMode() {
        boolean isPenche = false;
        if (this.getState() == Zombie.StateZombie.limboMode) {
            isPenche = true;
        }
        return isPenche;
    }

  
    protected void reducePV(int dmg) {
        this.pV -= dmg;
    }

   
    public int getpV() {
        return pV;
    }

    
    protected int getDmg() {
        return dmg;
    }

   
    protected String getID() {
        return ID;
    }
}
