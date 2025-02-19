class PlantRoseStrategy implements PlantStrategy {
    private String name = "Rose";
    private String type = "Flower";
    private double baseSeedPrice = 5;
    private int farmTime = 1;
    private int waterNeeds = 1;
    private int baseWaterBonusLim = 2;
    private int fertilizerNeeds = 0;
    private int baseFertilizerBonusLim = 1;
    private int produceMin = 1;
    private int produceMax = 1;
    private double sellingPrice = 5;
    private double xpGain = 2.5;

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