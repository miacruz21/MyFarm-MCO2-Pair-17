class PlantTurnipsStrategy implements PlantStrategy {
    private String name = "Turnips";
    private String type = "Flower";
    private double baseSeedPrice = 10;
    private int farmTime = 2;
    private int waterNeeds = 2;
    private int baseWaterBonusLim = 3;
    private int fertilizerNeeds = 0;
    private int baseFertilizerBonusLim = 1;
    private int produceMin = 1;
    private int produceMax = 1;
    private double sellingPrice = 9;
    private double xpGain = 5;

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