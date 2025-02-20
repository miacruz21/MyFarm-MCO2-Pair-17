import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class ShovelActionStrategy implements ActionStrategy {
    Tool tool = new Tool("Shovel", 7.00, 2.0);

    @Override
    public void execute(Player player, Tile tile, Gui.tileInfo tileInfo, JButton button, JLabel stats) {
        if (tile.getRocks()) {
            JOptionPane.showMessageDialog(tileInfo, "There is a rock in this tile, we might need to use Shovel", "Rock", JOptionPane.INFORMATION_MESSAGE);
            return;
        } 
        
        if (!tile.getPlowed()) {
           JOptionPane.showMessageDialog(tileInfo, "There is nothing in this tile, we might need to plant a seed", "Empty", JOptionPane.INFORMATION_MESSAGE);
           return;
        }

        if (player.getObjectCoins() < tool.getCost()) {
            JOptionPane.showMessageDialog(tileInfo, "Not enough Objectcoins to use Shovel", "Not Enough Money", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        player.useShovel(tool, tile);
        button.setBackground(Color.WHITE);
        JOptionPane.showMessageDialog(tileInfo, "Successfully un-plowed this tile (Xp gained: "+ tool.getXpGain()+")", "Success", JOptionPane.INFORMATION_MESSAGE);
        stats.setText("Player Stats: \nFarmer type - " + 
            player.getFarmerType().getName() + " | Level - " + player.getLevel() + 
                " | XP - " + player.getXp() + " | Objectcoins - " + player.getObjectCoins() + 
                " | Day " + player.getDay());
    }
}