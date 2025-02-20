import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class PickaxeActionStrategy implements ActionStrategy {
    Tool tool = new Tool("Pickaxe", 50.00, 15.0);

    @Override
    public void execute(Player player, Tile tile, Gui.tileInfo tileInfo, JButton button, JLabel stats) {
        if (tile.getWither()) {
            JOptionPane.showMessageDialog(tileInfo, "The tile has withered, we might need to use Shovel", "Withered", JOptionPane.INFORMATION_MESSAGE);
            return;
        } 
        
        if (tile.getSeed() != null) {
            JOptionPane.showMessageDialog(tileInfo, "There is a seed in this tile, we might need to use Shovel", "Seed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (tile.getPlowed()) {
            JOptionPane.showMessageDialog(tileInfo, "The tile is plowed, we might need to use Shovel", "Plowed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (player.getObjectCoins() < tool.getCost()) {
            JOptionPane.showMessageDialog(tileInfo, "Not enough Objectcoins to use Pickaxe", "Not Enough Money", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        player.usePick(tool, tile);
        button.setBackground(Color.WHITE);
        JOptionPane.showMessageDialog(tileInfo, "Successfully removed a rock (Xp gained: "+ tool.getXpGain()+")", "Success", JOptionPane.INFORMATION_MESSAGE);
        stats.setText("Player Stats: \nFarmer type - " + 
            player.getFarmerType().getName() + " | Level - " + player.getLevel() + 
                " | XP - " + player.getXp() + " | Objectcoins - " + player.getObjectCoins() + 
                " | Day " + player.getDay());
    }
}