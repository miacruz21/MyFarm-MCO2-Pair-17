import javax.swing.JButton;
import javax.swing.JLabel;

interface ActionStrategy {
    void execute(Player player, Tile tile, Gui.tileInfo tileInfo, JButton button, JLabel stats);
}
