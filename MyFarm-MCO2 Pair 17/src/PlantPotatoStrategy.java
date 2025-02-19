class PlantPotatoStrategy implements PlantStrategy {
    private String name = "Potato";
    private String type = "Root crop";
    private double baseSeedPrice = 20;
    private int farmTime = 5;
    private int waterNeeds = 3;
    private int baseWaterBonusLim = 4;
    private int fertilizerNeeds = 1;
    private int baseFertilizerBonusLim = 2;
    private int produceMin = 1;
    private int produceMax = 10;
    private double sellingPrice = 3;
    private double xpGain = 12.5;

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