package sokoban_code;

import javax.swing.*;
import java.awt.*;

public class OptionsWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private JComboBox<String> skinComboBox;
    private JCheckBox musicCheckBox;
    private String[] skins = {"Default", "Skin 1", "Skin 2", "Skin 3"};

    public OptionsWindow() {
        frame = new JFrame("Options");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(250, 150);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));

        // Add a label for the skin selection
        JLabel skinLabel = new JLabel("Select a skin:");
        mainPanel.add(skinLabel);

        // Add a combo box for the skin selection
        skinComboBox = new JComboBox<>(skins);
        mainPanel.add(skinComboBox);

        // Add a label for the music option
        JLabel musicLabel = new JLabel("Activate music:");
        mainPanel.add(musicLabel);

        // Add a check box for the music option
        musicCheckBox = new JCheckBox();
        mainPanel.add(musicCheckBox);

        frame.add(mainPanel);
    }

    public void show() {
        frame.setVisible(true);
    }
}