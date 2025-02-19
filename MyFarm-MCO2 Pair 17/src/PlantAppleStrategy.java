class PlantAppleStrategy implements PlantStrategy {
    private String name = "Apple";
    private String type = "Fruit tree";
    private double baseSeedPrice = 200;
    private int farmTime = 10;
    private int waterNeeds = 7;
    private int baseWaterBonusLim = 7;
    private int fertilizerNeeds = 5;
    private int baseFertilizerBonusLim = 5;
    private int produceMin = 10;
    private int produceMax = 15;
    private double sellingPrice = 5;
    private double xpGain = 25;

    @Override
    public void plant(Player player, Type farmerType, Tile tile) {
        if (player.getObjectCoins() < baseSeedPrice) {
            System.out.println("Insufficient ObjectCoins to purchase " + name + " seed!");
            return;
        }

        Seed seed = new Seed(
            name, type, baseSeedPrice + farmerType.getSeedCostReduce(), farmTime,
            waterNeeds, baseWaterBonusLim + farmerType.getWaterLimBonus(), fertilizerNeeds,
            baseFertilizerBonusLim + farmerType.getFertillizeLimBonus(), produceMin, produceMax,
            sellingPrice, xpGain
            );

        tile.setSeed(seed);
        tile.setCurrPlant(true);
        player.setObjectCoins(player.getObjectCoins() - baseSeedPrice);
        System.out.println(name + " has been planted on the tile!");
    }
}