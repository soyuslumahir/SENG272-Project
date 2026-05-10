package view;

import controller.DataManager;
import model.Scenario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Kalite tipi, mode ve senaryo seçimi yapılan ekran.
public class DefinePanel extends JPanel {
    private MainFrame frame;

    private JRadioButton productQualityButton;
    private JRadioButton processQualityButton;

    private JComboBox<String> modeComboBox;
    private JComboBox<Scenario> scenarioComboBox;

    public DefinePanel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 2: Define Quality Dimensions", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        updateScenarioComboBox();
    }

    // Seçim alanlarını oluşturur.
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addQualityTypeSelection(formPanel, gbc);
        addModeSelection(formPanel, gbc);
        addScenarioSelection(formPanel, gbc);

        return formPanel;
    }

    // Quality type radio buttonlarını oluşturur.
    private void addQualityTypeSelection(JPanel panel, GridBagConstraints gbc) {
        JLabel label = new JLabel("Quality Type:");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        productQualityButton = new JRadioButton("Product Quality");
        processQualityButton = new JRadioButton("Process Quality");

        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(productQualityButton);
        qualityGroup.add(processQualityButton);

        productQualityButton.setSelected(true);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(productQualityButton);
        radioPanel.add(processQualityButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(radioPanel, gbc);
    }

    // Mode seçimini oluşturur.
    private void addModeSelection(JPanel panel, GridBagConstraints gbc) {
        JLabel label = new JLabel("Mode:");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        modeComboBox = new JComboBox<>(new String[]{"Education", "Health"});
        modeComboBox.addActionListener(e -> updateScenarioComboBox());

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(modeComboBox, gbc);
    }

    // Scenario seçimini oluşturur.
    private void addScenarioSelection(JPanel panel, GridBagConstraints gbc) {
        JLabel label = new JLabel("Scenario:");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        scenarioComboBox = new JComboBox<>();

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(scenarioComboBox, gbc);
    }

    // Seçilen mode'a göre scenario listesini yeniler.
    private void updateScenarioComboBox() {
        if (scenarioComboBox == null || modeComboBox == null) {
            return;
        }

        scenarioComboBox.removeAllItems();

        String selectedMode = (String) modeComboBox.getSelectedItem();
        ArrayList<Scenario> scenarios = DataManager.getScenariosByMode(selectedMode);

        for (Scenario scenario : scenarios) {
            scenarioComboBox.addItem(scenario);
        }
    }

    // Back ve Next butonlarını oluşturur.
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));

        backButton.addActionListener(e -> frame.previousStep());
        nextButton.addActionListener(e -> saveAndContinue());

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        return buttonPanel;
    }

    // Seçimleri kaydeder ve sonraki adıma geçer.
    private void saveAndContinue() {
        String qualityType = productQualityButton.isSelected()
                ? "Product Quality"
                : "Process Quality";

        String mode = (String) modeComboBox.getSelectedItem();
        Scenario selectedScenario = (Scenario) scenarioComboBox.getSelectedItem();

        if (selectedScenario == null) {
            JOptionPane.showMessageDialog(this, "Please select a scenario to continue.");
            return;
        }

        frame.saveDefinitionInformation(qualityType, mode, selectedScenario);
        frame.nextStep();
    }
}