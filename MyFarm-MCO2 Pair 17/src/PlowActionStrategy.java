import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class PlowActionStrategy implements ActionStrategy {
    Tool tool = new Tool("Plow", 0.00, 0.5);

    @Override
    public void execute(Player player, Tile tile, Gui.tileInfo tileInfo, JButton button, JLabel stats) {
        if (tile.getWither()) {
            JOptionPane.showMessageDialog(tileInfo, "The tile has withered, we might need to use Shovel", "Withered", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (tile.getRocks()) {
            JOptionPane.showMessageDialog(tileInfo, "We cannot plow the tile because there is a rock on it, we night need use Pickaxe", "Rock", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (tile.getSeed() != null) {
            JOptionPane.showMessageDialog(tileInfo, "There is a seed in this tile, we might need to use Shovel", "Seed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (tile.getPlowed()) {
            JOptionPane.showMessageDialog(tileInfo, "This tile is plowed", "Plowed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        player.plow(tool, tile);
        button.setBackground(Color.ORANGE);
        JOptionPane.showMessageDialog(tileInfo, "Successfully plowed this tile (Xp gained: "+ tool.getXpGain()+")", "Success", JOptionPane.INFORMATION_MESSAGE);
        stats.setText("Player Stats: \nFarmer type - " + 
            player.getFarmerType().getName() + " | Level - " + player.getLevel() + 
            " | XP - " + player.getXp() + " | Objectcoins - " + player.getObjectCoins() + 
            " | Day " + player.getDay());
    }
}