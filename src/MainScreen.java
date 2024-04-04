import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen implements ActionListener {

    public MainScreen() {

        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WELCOME TO BLOCKUS!");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(panel);
        frame.setLocationRelativeTo(null);

        JButton singlePlayerButton = new JButton("Start Single Player Game!");
        singlePlayerButton.setPreferredSize(new Dimension(300, 60));
        singlePlayerButton.setBackground(Color.GREEN);
        singlePlayerButton.addActionListener(this);
        panel.add(singlePlayerButton, gbc);
        panel.add(Box.createVerticalStrut(80));

        JButton multiPlayerButton = new JButton(" Start Multi-player Game! ");
        multiPlayerButton.setPreferredSize(new Dimension(300, 60));
        multiPlayerButton.setBackground(Color.RED);
        multiPlayerButton.addActionListener(this);
        panel.add(multiPlayerButton, gbc);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new MainScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start Single Player Game!")) {
            // Code to call Single Player Game
            System.out.println("Starting Single Player Game!");
        } else if (e.getActionCommand().equals(" Start Multi-player Game! ")) {
            // Code to call Multiplayer Game
            System.out.println("Starting Multiplayer Game!");
        }
    }
}
