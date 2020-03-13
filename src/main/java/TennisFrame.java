import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TennisFrame extends JFrame implements Observer {

    private JPanel mainPanel = new JPanel();
    private PlayerPanel player1Panel = new PlayerPanel();
    private PlayerPanel player2Panel = new PlayerPanel();

    public TennisFrame() {
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        mainPanel.setLayout(new GridBagLayout());
        setLayout(new BorderLayout());

        buildMainPanel();

        add(mainPanel, BorderLayout.CENTER);
    }

    private void buildMainPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.gridx = 0;
        mainPanel.add(player1Panel, gbc);

        gbc.gridx = 1;
        mainPanel.add(player2Panel, gbc);
    }

    @Override
    public void update(Observable o, Object arg) {
        player1Panel.update(((Game) o).getPlayer1());
        player2Panel.update(((Game) o).getPlayer2());
    }
}
