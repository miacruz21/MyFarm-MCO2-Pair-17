import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class InfoActionStrategy implements ActionStrategy {
    @Override
    public void execute(Player player, Tile tile, Gui.tileInfo tileInfo, JButton button, JLabel stats) {
        if(tile.getWither()) {
            JOptionPane.showMessageDialog(tileInfo, "The tile has withered, we might need to use Shovel", "Withered", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(tile.getRocks()){
            JOptionPane.showMessageDialog(tileInfo, "This is just a rock, we might need to use Pickaxe", "Rock", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(tile.getSeed() != null) {
            new seedInfo(tile.getSeed());
            return;
        }

        JOptionPane.showMessageDialog(tileInfo, "There is nothing in this tile, we might need to plant a seed", "Empty", JOptionPane.INFORMATION_MESSAGE);
    }
}
