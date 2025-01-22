/** 
* Tool class to be used by the player
* @author Exconde and Soemadipradja
*/ 
public class Tool{
    private String name;
    private double cost;
    private double xpGain;

    //class Tool constructors, getters, and setters
    public Tool(String name, double cost, double xpGain) {
        this.name = name;
        this.cost = cost;
        this.xpGain = xpGain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getXpGain() {
        return xpGain;
    }

    public void setXpGain(double xpGain) {
        this.xpGain = xpGain;
    }

}