import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class WaterActionStrategy implements ActionStrategy {
    Tool tool = new Tool("Watering Can", 0.00, 0.5);

    @Override
    public void execute(Player player, Tile tile, Gui.tileInfo tileInfo, JButton button, JLabel stats) {
        if (tile.getWither()) {
            JOptionPane.showMessageDialog(tileInfo, "The tile has withered, we might need to use Shovel", "Withered", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (tile.getRocks()) {
            JOptionPane.showMessageDialog(tileInfo, "We cannot water the tile because there is a rock on it, we night need to use Pickaxe", "Rock", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (tile.getSeed() == null) {
            JOptionPane.showMessageDialog(tileInfo, "There is nothing in this tile, we might need to plant a seed", "Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (tile.getPlowed() == false) {
            JOptionPane.showMessageDialog(tileInfo, "We cannot water the tile because it is not plowed, we might need to use Plow", "Not Plowed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(tile.getTimesWatered() < tile.getSeed().getWaterBonusLim()) {
            player.water(tool, tile);
            JOptionPane.showMessageDialog(tileInfo, "Successfully watered this tile (Xp gained: "+ tool.getXpGain()+")", "Success", JOptionPane.INFORMATION_MESSAGE);
            stats.setText("Player Stats: \nFarmer type - " + 
                player.getFarmerType().getName() + " | Level - " + player.getLevel() + 
                " | XP - " + player.getXp() + " | Objectcoins - " + player.getObjectCoins() + 
                " | Day " + player.getDay());
        } else {
            player.water(tool, tile);
            JOptionPane.showMessageDialog(tileInfo, "The seed has been watered enough (Xp gained: "+ tool.getXpGain()+")", "Watered", JOptionPane.INFORMATION_MESSAGE);
            stats.setText("Player Stats: \nFarmer type - " + 
                player.getFarmerType().getName() + " | Level - " + player.getLevel() + 
                " | XP - " + player.getXp() + " | Objectcoins - " + player.getObjectCoins() + 
                " | Day " + player.getDay());
        }
        
    }
}