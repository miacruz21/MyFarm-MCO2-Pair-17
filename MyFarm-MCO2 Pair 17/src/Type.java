/**
* Type class being the type of farmer the player can be or is
* @author Exconde and Soemadipradja
*/
public class Type {
    private int levelNum;
    private String name;
    private double earningsBonus;
    private double seedCostReduce;
    private int waterLimBonus;
    private int fertillizeLimBonus;
    private double registrationFee;

    //class Type constructors, getters, and setters
    public Type(int levelNum, String name, double earningsBonus, double seedCostReduce, int waterLimBonus,
                int fertillizeLimBonus, double registrationFee) {
        this.levelNum = levelNum;
        this.name = name;
        this.earningsBonus = earningsBonus;
        this.seedCostReduce = seedCostReduce;
        this.waterLimBonus = waterLimBonus;
        this.fertillizeLimBonus = fertillizeLimBonus;
        this.registrationFee = registrationFee;
    }
    public int getLevelNum() {
        return levelNum;
    }
    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getEarningsBonus() {
        return earningsBonus;
    }
    public void setEarningsBonus(double earningsBonus) {
        this.earningsBonus = earningsBonus;
    }
    public double getSeedCostReduce() {
        return seedCostReduce;
    }
    public void setSeedCostReduce(double seedCostReduce) {
        this.seedCostReduce = seedCostReduce;
    }
    public int getWaterLimBonus() {
        return waterLimBonus;
    }
    public void setWaterLimBonus(int waterLimBonus) {
        this.waterLimBonus = waterLimBonus;
    }
    public int getFertillizeLimBonus() {
        return fertillizeLimBonus;
    }
    public void setFertillizeLimBonus(int fertillizeLimBonus) {
        this.fertillizeLimBonus = fertillizeLimBonus;
    }
    public double getRegistrationFee() {
        return registrationFee;
    }
    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

}
