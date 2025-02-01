
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.*;

public class CostCalculation extends JFrame {

    private Color backgroundColor;
    private Color defaultTextColor;
    private Color whiteTextColor;

    public CostCalculation() {

        setTitle("Cost Calculate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        // Price per kWH
        double electric_cost = 7;

        // Product strength(watt)
        double product = 150;

        // Background Color
        backgroundColor = new Color(43, 45, 48, 255);
        getContentPane().setBackground(backgroundColor);

        // Default and hover colors for buttons
        Color buttonColor = new Color(43, 45, 48, 255);
        Color hoverTextColor = Color.WHITE;
        defaultTextColor = new Color(128, 128, 128);
        whiteTextColor = Color.decode("#e3e3e3");

        // Text Color for TextField
        Color textColor = Color.decode("#d9d9d9");

        // Create labels and add panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null);
        labelPanel.setBounds(50, 50, 450, 600);
        labelPanel.setBackground(backgroundColor);

        JLabel electric_lbl = new JLabel("How many hours of electricity did you use?(minute)");
        JLabel filamentKg_lbl = new JLabel("How much does filament cost?(1 kg)");
        JLabel filamentUse_lbl = new JLabel("How much filament did you use? (gr)");

        electric_lbl.setForeground(defaultTextColor);
        electric_lbl.setFont(new Font("SansSerif", Font.BOLD, 16));

        filamentKg_lbl.setForeground(defaultTextColor);
        filamentKg_lbl.setFont(new Font("SansSerif", Font.BOLD, 16));

        filamentUse_lbl.setForeground(defaultTextColor);
        filamentUse_lbl.setFont(new Font("SansSerif", Font.BOLD, 16));

        labelPanel.add(electric_lbl);
        labelPanel.add(filamentKg_lbl);
        labelPanel.add(filamentUse_lbl);

        electric_lbl.setBounds(10, 75, 400, 30);
        filamentKg_lbl.setBounds(10, 225, 350, 30);
        filamentUse_lbl.setBounds(10, 375, 350, 30);

        add(labelPanel);

        // Create textfield and add panel
        JPanel txtfldPanel = new JPanel();
        txtfldPanel.setLayout(null);
        txtfldPanel.setBounds(500, 50, 200, 600);
        txtfldPanel.setBackground(backgroundColor);

        JTextField electric_txtfld = new JTextField();
        electric_txtfld.setBackground(backgroundColor);
        electric_txtfld.setForeground(textColor);
        electric_txtfld.setFont(new Font("Arial", Font.ITALIC, 18));
        electric_txtfld.setCaretColor(Color.WHITE);
        electric_txtfld.setHorizontalAlignment(SwingConstants.RIGHT);

        JTextField filamentKg_txtfld = new JTextField();
        filamentKg_txtfld.setBackground(backgroundColor);
        filamentKg_txtfld.setForeground(textColor);
        filamentKg_txtfld.setFont(new Font("Arial", Font.ITALIC, 18));
        filamentKg_txtfld.setCaretColor(Color.WHITE);
        filamentKg_txtfld.setHorizontalAlignment(SwingConstants.RIGHT);

        JTextField filamentUse_txtfld = new JTextField();
        filamentUse_txtfld.setBackground(backgroundColor);
        filamentUse_txtfld.setForeground(textColor);
        filamentUse_txtfld.setFont(new Font("Arial", Font.ITALIC, 18));
        filamentUse_txtfld.setCaretColor(Color.WHITE);
        filamentUse_txtfld.setHorizontalAlignment(SwingConstants.RIGHT);

        txtfldPanel.add(electric_txtfld);
        txtfldPanel.add(filamentKg_txtfld);
        txtfldPanel.add(filamentUse_txtfld);

        electric_txtfld.setBounds(20, 75, 150, 30);
        filamentKg_txtfld.setBounds(20, 225, 150, 30);
        filamentUse_txtfld.setBounds(20, 375, 150, 30);

        add(txtfldPanel);

        // Create button and add panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        buttonsPanel.setBounds(900, 540, 120, 50);
        buttonsPanel.setBackground(backgroundColor);

        JButton calculateBtn = createButton(
                "<html><div style='text-align: center;'><span style='font-size: 16px;'> Calculate </span></div></html>",
                buttonColor, defaultTextColor, hoverTextColor);

        calculateBtn.setBounds(10, 10, 100, 30);

        buttonsPanel.add(calculateBtn);

        add(buttonsPanel);

        // Conclusions
        JPanel conclusionPanel = new JPanel();
        conclusionPanel.setLayout(null);
        conclusionPanel.setBounds(800, 10, 300, 400);
        conclusionPanel.setBackground(backgroundColor);

        JLabel totalEnergyCost_lbl = new JLabel("Energy cost: ");
        JLabel totalFilamentCost_lbl = new JLabel("Filament cost: ");
        JLabel totalCost_lbl = new JLabel("Total cost: ");

        totalEnergyCost_lbl.setForeground(defaultTextColor);
        totalEnergyCost_lbl.setFont(new Font("SansSerif", Font.BOLD, 20));

        totalFilamentCost_lbl.setForeground(defaultTextColor);
        totalFilamentCost_lbl.setFont(new Font("SansSerif", Font.BOLD, 20));

        totalCost_lbl.setForeground(defaultTextColor);
        totalCost_lbl.setFont(new Font("SansSerif", Font.BOLD, 20));

        conclusionPanel.add(totalEnergyCost_lbl);
        conclusionPanel.add(totalFilamentCost_lbl);
        conclusionPanel.add(totalCost_lbl);

        totalEnergyCost_lbl.setBounds(800, 100, 250, 40);
        totalFilamentCost_lbl.setBounds(800, 190, 250, 40);
        totalCost_lbl.setBounds(800, 280, 250, 40);

        add(conclusionPanel);

        // Actioner List for calculate button
        calculateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String electricText = electric_txtfld.getText();
                String filamentKgText = filamentKg_txtfld.getText();
                String filamentUseText = filamentUse_txtfld.getText();

                try {
                    double electricHours = Double.parseDouble(electricText);
                    double filamentCost = Double.parseDouble(filamentKgText);
                    double filamentUsed = Double.parseDouble(filamentUseText);

                    // textfield full/empty control
                    if (electricText.isEmpty() || filamentKgText.isEmpty() || filamentUseText.isEmpty()) {
                        showCustomDialog("Please enter a valid value.", backgroundColor, whiteTextColor);
                    }
                    // Check if textfield is negative
                    else if (electricHours < 0 || filamentCost < 0 || filamentUsed < 0) {
                        showCustomDialog("Please enter a non-negative value.", backgroundColor, whiteTextColor);
                    } else {
                        // Calculate Conclusions
                        double totalEnergy = (electricHours / 60) * (product / 1000);
                        double totalEnergyCost = totalEnergy * electric_cost;

                        double totalFilamentCost = (filamentUsed * filamentCost) / 1000;
                        double totalCost = totalEnergyCost + totalFilamentCost;

                        // Number rounding
                        DecimalFormat df = new DecimalFormat("#.#####");
                        // Writing Conclusion
                        totalEnergyCost_lbl.setText("Energy cost: " + df.format(totalEnergyCost));
                        totalFilamentCost_lbl.setText("Filament cost: " + df.format(totalFilamentCost));
                        totalCost_lbl.setText("Total cost: " + df.format(totalCost));
                    }
                    // Type Control
                } catch (NumberFormatException ex) {
                    showCustomDialog("Please enter a numeric value.", backgroundColor, whiteTextColor);
                }
            }
        });

        setVisible(true);
    }

    public void showCustomDialog(String message, Color background, Color whiteTextColor) {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(whiteTextColor);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(background);
        panel.add(messageLabel);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private static JButton createButton(String text, Color buttonColor, Color defaultTextColor, Color hoverTextColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(buttonColor);
        button.setForeground(defaultTextColor);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setText(button.getText().replace("rgba(255, 255, 255, 0.5)", "rgba(255, 255, 255, 1.0)")
                        .replace("rgba(255, 255, 255, 0.3)", "rgba(255, 255, 255, 1.0)"));
                button.setForeground(hoverTextColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setText(button.getText().replace("rgba(255, 255, 255, 1.0)", "rgba(255, 255, 255, 0.5)")
                        .replace("rgba(255, 255, 255, 1.0)", "rgba(255, 255, 255, 0.3)"));
                button.setForeground(defaultTextColor);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        new CostCalculation();
    }
}