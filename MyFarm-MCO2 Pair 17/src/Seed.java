import java.util.Random;
/**
* Seed class to be used by the player on the tile
* @author Exconde and Soemadipradja
*/ 
public class Seed {
    private String name;
    private String type;
    private double seedPrice;
    private int farmTime;
    private int waterNeeds;
    private int waterBonusLim;
    private int fertilizerNeeds;
    private int fertilizerBonusLim;
    private int produceMin;
    private int produceMax;
    private int productsProduced;
    private double sellingPrice;
    private double xpGain;

    //class Seed constructors, getters, and setters
    public Seed(String name, String type, double seedPrice, int farmTime, int waterNeeds, int waterBonusLim,
                int fertilizerNeeds, int fertilizerBonusLim, int produceMin, int produceMax,
                double sellingPrice, double xpGain) {
        Random random = new Random(); //randomizes the products produced

        this.name = name;
        this.type = type;
        this.seedPrice = seedPrice;
        this.farmTime = farmTime;
        this.waterNeeds = waterNeeds;
        this.waterBonusLim = waterBonusLim;
        this.fertilizerNeeds = fertilizerNeeds;
        this.fertilizerBonusLim = fertilizerBonusLim;
        this.produceMin = produceMin;
        this.produceMax = produceMax;
        this.productsProduced = produceMin + random.nextInt(produceMax - produceMin + 1);
        this.sellingPrice = sellingPrice;
        this.xpGain = xpGain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSeedPrice() {
        return seedPrice;
    }

    public void setSeedPrice(double seedPrice) {
        this.seedPrice = seedPrice;
    }

    public int getFarmTime() {
        return farmTime;
    }

    public void setFarmTime(int farmTime) {
        this.farmTime = farmTime;
    }

    public int getWaterNeeds() {
        return waterNeeds;
    }

    public void setWaterNeeds(int waterNeeds) {
        this.waterNeeds = waterNeeds;
    }

    public int getWaterBonusLim() {
        return waterBonusLim;
    }

    public void setWaterBonusLim(int waterBonusLim) {
        this.waterBonusLim = waterBonusLim;
    }

    public int getFertilizerNeeds() {
        return fertilizerNeeds;
    }

    public void setFertilizerNeeds(int fertilizerNeeds) {
        this.fertilizerNeeds = fertilizerNeeds;
    }

    public int getFertilizerBonusLim() {
        return fertilizerBonusLim;
    }

    public void setFertilizerBonusLim(int fertilizerBonusLim) {
        this.fertilizerBonusLim = fertilizerBonusLim;
    }

    public int getProduceMin() {
        return produceMin;
    }

    public void setProduceMin(int produceMin) {
        this.produceMin = produceMin;
    }

    public int getProduceMax() {
        return produceMax;
    }

    public void setProduceMax(int produceMax) {
        this.produceMax = produceMax;
    }

    public int getProductsProduced() {
        return productsProduced;
    }

    public void setProductsProduced(int productsProduced) {
        this.productsProduced = productsProduced;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getXpGain() {
        return xpGain;
    }

    public void setXpGain(double xpGain) {
        this.xpGain = xpGain;
    }


}
