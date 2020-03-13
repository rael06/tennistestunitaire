import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private JTextField fullName = new JTextField(20);
    private JTextField points = new JTextField(20);
    private JTextField games = new JTextField(20);
    private JTextField sets = new JTextField(20);

    public PlayerPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty=1;
        gbc.weightx=1;
        gbc.gridy=0;
        add(fullName,gbc);

        gbc.gridy++;
        add(points,gbc);

        gbc.gridy++;
        add(games,gbc);

        gbc.gridy++;
        add(sets,gbc);
    }

    public void update(Player player) {
        fullName.setText(player.getFullName());
        points.setText(player.retrievePoint());
        games.setText(player.getNbOfGamesWon() + "");
        sets.setText(player.getNbOfGameSetsWon() + "");
    }
}
