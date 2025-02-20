import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class FertilizeActionStrategy implements ActionStrategy {
    Tool tool = new Tool("Fertilizer", 10.00, 4.0);

    @Override
    public void execute(Player player, Tile tile, Gui.tileInfo tileInfo, JButton button, JLabel stats) {
        if (tile.getWither()) {
            JOptionPane.showMessageDialog(tileInfo, "The tile has withered, we might need to use Shovel", "Withered", JOptionPane.INFORMATION_MESSAGE);
            return;
        } 
        
        if (tile.getRocks()) {
            JOptionPane.showMessageDialog(tileInfo, "We cannot fertilize the tile because there is a rock on it, we night need to use Pickaxe", "Rock", JOptionPane.INFORMATION_MESSAGE);
            return;
        } 
        
        if (player.getObjectCoins() < tool.getCost()) {
            JOptionPane.showMessageDialog(tileInfo, "Not enough Objectcoins to use Fertilizer", "Not Enough Money", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (tile.getSeed() == null) {
            JOptionPane.showMessageDialog(tileInfo, "There is nothing in this tile, we might need to plant a seed", "Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(tile.getSeed().getFertilizerBonusLim() + tile.getSeed().getFertilizerNeeds() > tile.getTimesFertilized()) {
            player.fertilize(tool, tile);
            tileInfo.getTimesFertilized().setText("Times Fertilized: " + tile.getTimesFertilized());
            JOptionPane.showMessageDialog(tileInfo, "Successfully fertilized this tile (Xp gained: "+ tool.getXpGain()+")", "Success", JOptionPane.INFORMATION_MESSAGE);
            stats.setText("Player Stats: \nFarmer type - " + 
                player.getFarmerType().getName() + " | Level - " + player.getLevel() + 
                " | XP - " + player.getXp() + " | Objectcoins - " + player.getObjectCoins() + 
                " | Day " + player.getDay());
        } else {
            player.fertilize(tool, tile);
            JOptionPane.showMessageDialog(tileInfo, "The seed has been fertilized enough (Xp gained: "+ tool.getXpGain()+")", "Fertilized", JOptionPane.INFORMATION_MESSAGE);
            stats.setText("Player Stats: \nFarmer type - " + 
                player.getFarmerType().getName() + " | Level - " + player.getLevel() + 
                " | XP - " + player.getXp() + " | Objectcoins - " + player.getObjectCoins() + 
                " | Day " + player.getDay());
        }
    }
}