import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class HarvestActionStrategy implements ActionStrategy {
    Tool tool = new Tool("Fertilizer", 10.00, 4.0);

    @Override
    public void execute(Player player, Tile tile, Gui.tileInfo tileInfo, JButton button, JLabel stats) {
        if (tile.getWither()) {
            JOptionPane.showMessageDialog(tileInfo, "The tile has withered, we might need to use Shovel", "Withered", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (tile.getRocks()) {
            JOptionPane.showMessageDialog(tileInfo, "We cannot harvest the tile because there is a rock on it, we night need to use Pickaxe", "Rock", JOptionPane.INFORMATION_MESSAGE);
            return;
        } 
        
        if (tile.getSeed() == null) {
            JOptionPane.showMessageDialog(tileInfo, "There is nothing in this tile, we might need to plant a seed", "Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(tile.getSeed().getFarmTime() > 0) {
            JOptionPane.showMessageDialog(tileInfo, "The seed has not grown enough", "Unable to Harvest", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(tileInfo, "Successfully harvested this tile - No. of "+
            tile.getSeed().getName()+" produced: "+tile.getSeed().getProductsProduced()+
            " Xp gained: "+tile.getSeed().getXpGain(), "Success", JOptionPane.INFORMATION_MESSAGE);
        player.harvest(tile);
        stats.setText("Player Stats: \nFarmer type - " + 
            player.getFarmerType().getName() + " | Level - " + player.getLevel() + 
                " | XP - " + player.getXp() + " | Objectcoins - " + player.getObjectCoins() + 
                " | Day " + player.getDay());
        button.setBackground(Color.ORANGE);
    }
}