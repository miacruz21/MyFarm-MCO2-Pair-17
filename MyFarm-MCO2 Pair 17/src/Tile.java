/**
* Tile class that the player farms on
* @author Exconde and Soemadipradja
*/
public class Tile {
    private Boolean rocks;
    private Boolean plowed;
    private Boolean wither;
    private Boolean currPlant;
    private Seed seed; //not initialized in constructor
    private int timesWatered;
    private int timesFertilized;

    //class Tile constructors, getters, and setters
    public Tile(Boolean rocks, Boolean plowed, Boolean wither, Boolean currPlant, int timesWatered, int timesFertilized) {
        this.rocks = rocks;
        this.plowed = plowed;
        this.wither = wither;
        this.currPlant = currPlant;
        this.timesWatered = timesWatered;
        this.timesFertilized = timesFertilized;
        this.seed = null;
    }

    public Boolean getRocks() {
        return rocks;
    }

    public void setRocks(Boolean rocks) {
        this.rocks = rocks;
    }

    public Boolean getPlowed() {
        return plowed;
    }

    public void setPlowed(Boolean plowed) {
        this.plowed = plowed;
    }

    public Boolean getWither() {
        return wither;
    }

    public void setWither(Boolean wither) {
        this.wither = wither;
    }

    public Boolean getCurrPlant() {
        return currPlant;
    }

    public void setCurrPlant(Boolean currPlant) {
        this.currPlant = currPlant;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public int getTimesWatered() {
        return timesWatered;
    }

    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }

    public int getTimesFertilized() {
        return timesFertilized;
    }

    public void setTimesFertilized(int timesFertilized) {
        this.timesFertilized = timesFertilized;
    }

  /**Resets the statistics and data
  */
    public void reset(){
        this.rocks = false;
        this.plowed = false;
        this.wither = false;
        this.currPlant = null;
        this.timesWatered = 0;
        this.timesFertilized = 0;
    }
}
