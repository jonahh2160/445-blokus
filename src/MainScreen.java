import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen  implements ActionListener{
    
    public MainScreen() {
        
       
        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WELCOME TO BLOCKUS!");
        //frame.add(panel, BorderLayout.CENTER);

       // JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        
        
       
        //frame.pack();
        

        
        //panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
        //panel.setLayout(new GridLayout(2, 0));

        JButton singlePlayerButton = new JButton("Start Single Player Game!");
        //singlePlayerButton.setBounds(10, 20, 80, 25);
        singlePlayerButton.setPreferredSize(new Dimension(300, 60));
        singlePlayerButton.setBackground(Color.GREEN);
        singlePlayerButton.addActionListener(this);
        panel.add(singlePlayerButton, gbc);
        panel.add(Box.createVerticalStrut(80));
       

        JButton multiPlayerButton = new JButton(" Start Multi-player Game! ");
        //multiPlayerButton.setBounds(10, 40, 80, 25);
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
        //Code to call Single Player Game
        System.out.println("Starting Single Player Game!");
    } else if (e.getActionCommand().equals(" Start Multi-player Game! ")) {
        // Code to call Multiplayer Game
        System.out.println("Starting Multiplayer Game!");
    }
}
}
