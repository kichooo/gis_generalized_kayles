package pl.edu.gis.kayles.view;

import pl.edu.gis.kayles.Game;
import pl.edu.gis.kayles.util.GameProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Date: 16.01.14
 * Time: 16:35
 */
public class GamePropertiesWindow extends JFrame {
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JComboBox comboBox1;
    private JTextField P1AValueField;
    private JTextField P1BValueField;
    private JTextField P1CValueField;
    private JTextField P2AValueField;
    private JTextField P2BValueField;
    private JTextField P2CValueField;
    private JButton goButton;
    private JPanel panel;

    public GamePropertiesWindow() {

        String[] choices = {"10", "30", "50"};
        comboBox1.setModel(new DefaultComboBoxModel(choices));

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameProperties properties = new GameProperties();
                properties.setGraphSize((String) comboBox1.getSelectedItem());
                properties.setWa1(P1AValueField.getText());
                properties.setWb1(P1BValueField.getText());
                properties.setWc1(P1CValueField.getText());

                boolean singlePlayer = a1RadioButton.isSelected();
                properties.setSinglePlayer(singlePlayer);
                if (!singlePlayer) {
                    properties.setWa2(P2AValueField.getText());
                    properties.setWb2(P2BValueField.getText());
                    properties.setWc2(P2CValueField.getText());
                }

                Game.startGame(properties);
            }
        });

        getContentPane().add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setText("AI Players");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label1, gbc);
        a1RadioButton = new JRadioButton();
        a1RadioButton.setText("1");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(a1RadioButton, gbc);
        a2RadioButton = new JRadioButton();
        a2RadioButton.setText("2");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(a2RadioButton, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Graph");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label2, gbc);
        comboBox1 = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboBox1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel.add(spacer3, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Parameters");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setMaximumSize(new Dimension(40, 14));
        label4.setMinimumSize(new Dimension(40, 14));
        label4.setPreferredSize(new Dimension(40, 14));
        label4.setText("wa");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label4, gbc);
        final JLabel label5 = new JLabel();
        label5.setMaximumSize(new Dimension(40, 14));
        label5.setMinimumSize(new Dimension(40, 14));
        label5.setPreferredSize(new Dimension(40, 14));
        label5.setText("wb");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label5, gbc);
        final JLabel label6 = new JLabel();
        label6.setMaximumSize(new Dimension(40, 14));
        label6.setMinimumSize(new Dimension(40, 14));
        label6.setPreferredSize(new Dimension(40, 14));
        label6.setText("wc");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label6, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("AI Player 1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label7, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("AI Player 2");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label8, gbc);
        P1AValueField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(P1AValueField, gbc);
        P1BValueField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(P1BValueField, gbc);
        P1CValueField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(P1CValueField, gbc);
        P2AValueField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(P2AValueField, gbc);
        P2BValueField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(P2BValueField, gbc);
        P2CValueField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(P2CValueField, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(spacer4, gbc);
        goButton = new JButton();
        goButton.setText("Go");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(goButton, gbc);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(a1RadioButton);
        buttonGroup.add(a2RadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
