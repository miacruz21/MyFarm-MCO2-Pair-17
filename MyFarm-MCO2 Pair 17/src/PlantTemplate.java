public class PlantAction extends ActionTemplate {
    @Override
    protected boolean validate(Tile tile, Seed seed, Player player) {
        if (tile.getRocks()) {
            System.out.println("Cannot plant: Tile has rocks!");
            return false;
        }
        if (tile.getCurrPlant()) {
            System.out.println("Cannot plant: Tile is already occupied!");
            return false;
        }
        if (!tile.getPlowed()) {
            System.out.println("Cannot plant: Tile must be plowed first!");
            return false;
        }
        if (player.getObjectCoins() < seed.getSeedPrice()) {
            System.out.println("Cannot plant: Not enough ObjectCoins!");
            return false;
        }
        return true;
    }

    @Override
    protected void performAction(Tile tile, Seed seed, Player player) {
        tile.setSeed(seed);
        tile.setCurrPlant(true);
        player.setObjectCoins(player.getObjectCoins() - seed.getSeedPrice());
        System.out.println(seed.getName() + " has been planted on the tile!");
    }

    @Override
    protected void finalizeAction(Tile tile, Player player) {
        System.out.println("Planting completed. Player now has " + player.getObjectCoins() + " ObjectCoins.");
    }
}
