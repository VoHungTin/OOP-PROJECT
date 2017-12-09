package pvz.model;

import java.util.ArrayList;


public class Model {

    private int noZombie = 0;
   
    private int cash = 425;
    
    private ArrayList<Zombie> listZombie = new ArrayList<>();
    
    private ArrayList<Plant> listPlant = new ArrayList<>();
    
    private int nbZombieWave = 5;
    
    private float multiplierZombie = 1.2F;
    
    private int[] coordYX = new int[2];
    
    private Plant tirePois = new Plant("TirePois", coordYX);
    
    private Plant lanceMelon = new Plant("LanceMelon", coordYX);

    
    public ArrayList<Plant> getListPlant() {
        return listPlant;
    }

    
    public ArrayList<Zombie> getListZombie() {
        return listZombie;
    }

    
    public Zombie getZombie(String ID) {
        Zombie zombieSelection = null;
        for (Zombie zombie : listZombie) {
            if (ID.equalsIgnoreCase(zombie.getID())) {
                zombieSelection = zombie;
            }
        }
        return zombieSelection;
    }

    
    public Plant getPlant(int[] coordYX) {
        Plant plantSelection = null;
        for (Plant plant : listPlant) {
            if (coordYX == plant.getCoordYX()) {
                plantSelection = plant;
            }
        }
        return plantSelection;
    }

    public void modCash(int transaction) {
        if (cash + transaction >= 0) {
            cash += transaction;
        }
    }

    public int getCash() {
        return this.cash;
    }

    
    public void createZombie(String typeZombie, String ID, int noLane) {
        listZombie.add(new Zombie(typeZombie, ID, noLane));
    }

    
    public void createPlant(String typePlant, int[] coordYX) {
        listPlant.add(new Plant(typePlant, coordYX));
    }

   
    public void dammageZombie(String ID, String typePlante) {
        Zombie zombie = getZombie(ID);
        if (typePlante.equalsIgnoreCase(tirePois.getType())) {
            zombie.reducePV(tirePois.getDmg());
        } else if (typePlante.equalsIgnoreCase(lanceMelon.getType())) {
            zombie.reducePV(lanceMelon.getDmg());
        }
    }

    
    public synchronized void damagePlant(int[] coordYX, String typeZombie) {
        for (Plant plant : listPlant) {
            if (coordYX == plant.getCoordYX()) {
                boolean degatsInfliges = false;
                for (int i = 0; !degatsInfliges && i < listZombie.size(); i++) {
                    Zombie zombie = listZombie.get(i);
                    if (typeZombie.equalsIgnoreCase(zombie.getTypeZombie())) {
                        plant.reducePV(zombie.getDmg());
                        boolean damage = true;
                    }
                }
            }
        }
    }

    
    public int nbZombieNewWave(int noWave) {
        if (noWave >= 2) {
            nbZombieWave *= multiplierZombie;
        }
        return nbZombieWave;
    }

    
    public void setMultiplierZombie(float multiplierZombi) {
        this.multiplierZombie = multiplierZombi;
    }

    public int getNameOfZombiesAppear() {
        noZombie++;
        return this.noZombie;
    }
}