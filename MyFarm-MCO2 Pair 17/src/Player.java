/** 
* Player class does everything a player can do in the game
* @author Exconde and Soemadipradja
*/
public class Player {
    private int level; // initialize 0 in Driver later
    private Type farmerType; // initialize as Farmer(default)
    private double ObjectCoins; // initialize 100 in Driver later
    private int day; // initialize 1 in Driver later
    private double xp; // initialize 0 in Driver later

    /*class Player constructors, getters, and setters*/
    public Player(int level, double objectCoins, int day, double xp) {
        this.level = level;
        this.farmerType = new Type(0, "Farmer", 0, 0, 0, 0, 0);
        this.ObjectCoins = objectCoins;
        this.day = day;
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Type getFarmerType() {
        return farmerType;
    }

    public void setFarmerType(Type farmerType) {
        this.farmerType = farmerType;
    }

    public double getObjectCoins() {
        return ObjectCoins;
    }

    public void setObjectCoins(double objectCoins) {
        ObjectCoins = objectCoins;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    /**
     * Checks xp to level up
     * @return true if player xp is enough to level up
     *         false if player xp is not enough to level up
     */
    public boolean levelUp() {// 
        if (this.xp >= ((1 + this.level) * 100)) {
            this.level++;
            return true;
        } else
            return false;
    }
    
    
    /**
     * Registers the player's farmer type
     * @param choice the player's input of choice in the form of integer
     */
    public void register(int choice) {
        Type type;
        if (choice == 1) {
            type = new Type(5, "Registered Farmer", 1.00, -1.00, 0, 0, 200.00);
        } else if (choice == 2) {
            type = new Type(10, "Distinguished Farmer", 2.00, -2.00, 1, 0, 300.00);
        } else if (choice == 3) {
            type = new Type(15, "Legendary Farmer", 4.00, -3.00, 2, 1, 400.00);
        } else {
            System.out.println("Invalid input!");
            return;
        }
        if (this.level >= type.getLevelNum()) {
            if (ObjectCoins >= type.getRegistrationFee()) {
                System.out.println("You have successfuly registered as a "+type.getName());
                this.farmerType = type;
                this.ObjectCoins = this.ObjectCoins - type.getRegistrationFee();
            } else {
                System.out.println("Not enough money!");
            }
        } else {
            System.out.println("You are not yet to eligble to register as this Farmer Type!");
        }
    }

    
    /**
     * Sets a tile's wither to true if the plant was not harvested in time 
     * or if it was not watered or fertilized enough at the day of harvest
     * @param tile the (singular) plot of land that is chosen by the player
     */
    public void checkWithered(Tile tile) {
        if (tile.getSeed().getFarmTime() == 0) {
            if (tile.getTimesWatered() < tile.getSeed().getWaterNeeds() || tile.getTimesFertilized() < tile.getSeed().getFertilizerNeeds()) {
                tile.setWither(true);
            } else
                return;
        } else if (tile.getSeed().getFarmTime() < 0) {
            tile.setWither(true);
        } else
            return;
    }

    
    /**
     * Plants a seed of choice in a chosen tile
     * @param tile the (singular) plot of land that is chosen by the player
     * @param choice the player's input of choice 
     */
    public void plant(Tile tile, int choice) { 
        if (tile.getRocks() == false && tile.getCurrPlant() == false) {
            if (tile.getPlowed() == true) {
                System.out.println("=====PLANT=====");
                System.out.println("[1] Turnip (Type: Root crop Cost: 5)");
                System.out.println("[2] Carrot (Type: Root crop Cost: 10)");
                System.out.println("[3] Potato (Type: Root crop Cost: 20)");
                System.out.println("[4] Rose (Type: Flower Cost: 5)");
                System.out.println("[5] Turnips (Type: Flower Cost: 10)");
                System.out.println("[6] Sunflower (Type: Flower Cost: 20)");
                System.out.println("[7] Mango (Type: Fruit tree Cost: 100)");
                System.out.println("[8] Apple (Type: Fruit tree Cost: 200)");
                System.out.println("Which would you like to plant in the tile?");

                PlantStrategy plantStrategy = null;
                switch (choice) {
                    case 1:
                        plantStrategy = new PlantTurnipStrategy();
                        break;
                    case 2:
                        plantStrategy = new PlantCarrotStrategy();
                        break;
                    case 3:
                        plantStrategy = new PlantPotatoStrategy();
                        break;
                    case 4:
                        plantStrategy = new PlantRoseStrategy();
                        break;
                    case 5:
                        plantStrategy = new PlantTurnipsStrategy();
                        break;
                    case 6:
                        plantStrategy = new PlantSunflowerStrategy();
                        break;
                    case 7:
                        plantStrategy = new PlantMangoStrategy();
                        break;
                    case 8:
                        plantStrategy = new PlantAppleStrategy();
                        break;

                    default:
                        System.out.println("Invalid input!");
                }

                if (plantStrategy != null) {
                    plantStrategy.plant(this, farmerType, tile);
                }
                
            } else {
                System.out.println("Tile has not been plowed! You have to plow the tile with a plow first.");
            }
        }  else {
            System.out.println("Tile is still occupied and cannot be planted on!");
        }
    }

    
    /**
     * Plows a vacant tile
     * @param plow the object or tool used to plow a tile
     * @param tile the (singular) plot of land that is chosen by the player
     */
    public void plow(Tool plow, Tile tile) {
        if (tile.getCurrPlant() == false && tile.getRocks() == false) {
            if (tile.getPlowed() == false) { // not yet plowed
                tile.setPlowed(true);
                xp += 0.5;
                System.out.println("You have successfully plowed the tile!");
            } else {
                System.out.println("You already plowed this tile.");
            }
        } else {
            System.out.println("The tile is occupied and cannot be plowed.");
        }
        levelUp();
    }

    
    /**
     * Waters a tile whether a seed is planted or not
     * @param wateringcan the object or tool used to water a plant
     * @param tile the (singular) plot of land that is chosen by the player
     */
    public void water(Tool wateringcan, Tile tile) {
        if (tile.getPlowed() == false) { // not yet plowed, cannot water
            System.out.println("You cannot water this tile as it has not been plowed.");
            System.out.println(" Please plow this tile first.");
        } else {// alrdy plowed, can water
            if (tile.getSeed() != null) {
                if (tile.getTimesWatered() < tile.getSeed().getWaterBonusLim()) {
                    tile.setTimesWatered(tile.getTimesWatered() + 1);
                    System.out.println("You have successfully watered this tile.");
                } else
                    System.out.println("You have successfully watered this tile, although you can't water this plant anymore.");
            } else
                System.out.println("You have successfully watered this tile, although no plant was watered.");
            xp += 0.5;
            levelUp();
        }
    }

    
    /**
     * Fertilizes a tile
     * @param fertilizer the object or tool used to fertilize the plant
     * @param tile the (singular) plot of land that is chosen by the player
     */
    public void fertilize(Tool fertilizer, Tile tile) {
        if (tile.getPlowed() == false) { // not yet plowed, cannot fertilize
            System.out.println("You cannot fertilize this tile as it has not been plowed.");
            System.out.println(" Please plow this tile first.");
        } else {// alrdy plowed, can fertilize
            if(this.ObjectCoins >= fertilizer.getCost()){
                if (tile.getSeed() != null) {
                    if (tile.getTimesFertilized() < tile.getSeed().getFertilizerBonusLim()) {
                        tile.setTimesFertilized(tile.getTimesFertilized() + 1);
                        System.out.println("You have successfully fertilize this tile.");
                    } else
                        System.out.println("You have successfully fertilize this tile, although you can't fertilize this plant anymore.");
                } else
                    System.out.println("You have successfully fertilize this tile, although no plant was fertilized.");
                xp += 4.0;
                this.ObjectCoins -= fertilizer.getCost();
                levelUp();
            }else
                System.out.println("You do not have enough Object Coins to fertilize.");
        }
    }

    
    /**
     * Uses a shovel to reset a tile
     * @param shovel the object or tool used to remove a plant from a tile
     * @param tile the (singular) plot of land that is chosen by the player
     */
    public void useShovel(Tool shovel, Tile tile) {
        if (tile.getPlowed() == false) { // not yet plowed, cannot shovel
            System.out.println("You cannot use the shovel on this tile as it has not been plowed.");
            System.out.println(" Please plow this tile first.");
        } else {// alrdy plowed, can shovel
            if(this.ObjectCoins >= shovel.getCost()){
                if (tile.getSeed() != null) {
                    tile.reset();
                    System.out.println("You have successfully used the shovel to remove the plant on this tile.");
                } else
                    tile.reset();
                    System.out.println("You have successfully used the shovel on this tile, although there was no plant remove.");
                xp += 2.0;
                this.ObjectCoins -= shovel.getCost();
                levelUp();
            }else
                System.out.println("You do not have enough Object Coins to use the shovel.");
        }
    }

    
    /**
     * Removes a rock if present on a tile
     * @param pickaxe the object or tool used to remove rocks from the tile
     * @param tile the (singular) plot of land that is chosen by the player
     */
    public void usePick(Tool pickaxe, Tile tile) {
        if (tile.getRocks() == false) { // not yet plowed, cannot pick
            System.out.println("You cannot use the shovel on this tile as it does not have any rocks on it.");
        } else {// rocks present, can pick
            if(this.ObjectCoins >= pickaxe.getCost()){
                tile.setRocks(false);
                System.out.println("You have successfully used the pickaxe to remove the rocks on this tile.");
                xp += 15;
                this.ObjectCoins -= pickaxe.getCost();
                levelUp();
            }else
                System.out.println("You do not have enough Object Coins to use the pickaxe.");
        }
    }

    /**
     * Harvests the tile
     * @param tile the (singular) plot of land that is chosen by the player
     */
    public void harvest(Tile tile) { //
        if (tile.getCurrPlant() == true) {
            if (tile.getWither() == false) {
                if (tile.getSeed().getFarmTime() == 0) {
                    double HarvestTotal = tile.getSeed().getProductsProduced() * (tile.getSeed().getSellingPrice() + this.farmerType.getEarningsBonus());
                    double WaterBonus = HarvestTotal * 0.2 * (tile.getTimesWatered() - 1);
                    double FertilizerBonus = HarvestTotal * 0.5 * tile.getTimesFertilized();
                    double FinalHarvestPrice = HarvestTotal + WaterBonus + FertilizerBonus;
                    if (tile.getSeed().getType().equals("Flower"))
                        FinalHarvestPrice = FinalHarvestPrice * 1.1;
                    this.ObjectCoins += FinalHarvestPrice;
                    this.xp += tile.getSeed().getXpGain();
                    System.out.println(tile.getSeed().getName()+" harvested successfully!\n");
                    System.out.println("No. of "+tile.getSeed().getName()+" pieces produced: "+tile.getSeed().getProductsProduced());
                    System.out.println("Total ObjectCoins earned: "+FinalHarvestPrice);
                    System.out.println("Total Current ObjectCoins: "+getObjectCoins());
                    System.out.println("Xp Gained: "+tile.getSeed().getXpGain());
                    System.out.println("Total Current Xp: "+getXp());
                    tile.setCurrPlant(false);
                    tile.setSeed(null);
                    tile.setTimesFertilized(0);
                    tile.setTimesWatered(0);
                    tile.setWither(false);
                    levelUp();
                } else {
                    System.out.println("It is not time to harvest this yet!");
                }
            } else {
                System.out.println("The plant on the tile has withered and cannot be harvested!");
            }
        } else {
            System.out.println("There is nothing planted on the tile!");
        }
    }

    /**
     *Advances tile to the next day
     * @param tile the (singular) plot of land that is chosen by the player
     */
    public void advanceDay(Tile tile) {  
        if (tile.getCurrPlant() == true)
            tile.getSeed().setFarmTime(tile.getSeed().getFarmTime() - 1);
    }
}