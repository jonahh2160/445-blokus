import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Michael Toon 3/28/24

public class MainScreen implements ActionListener {

    private GUI blokusGUI;
    public MainScreen(GUI blokusGUI) {

        this.blokusGUI = blokusGUI;
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

        blokusGUI.setVisible(false);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        GUI blokusGUI = new GUI();
        new MainScreen(blokusGUI);
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
        blokusGUI.setVisible(true);
    }
}
