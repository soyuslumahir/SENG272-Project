package view;

import model.Scenario;
import model.UserSession;

import javax.swing.*;
import java.awt.*;

// Ana pencere ve CardLayout wizard yapısını yönetir.
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel stepIndicatorPanel;
    private JLabel[] stepLabels;

    private int currentStep;
    private String[] stepNames = {"Profile", "Define", "Plan", "Collect", "Analyse"};

    private UserSession userSession;

    private ProfilePanel profilePanel;
    private DefinePanel definePanel;
    private PlanPanel planPanel;
    private CollectPanel collectPanel;
    private AnalysePanel analysePanel;

    public MainFrame() {
        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userSession = new UserSession();
        currentStep = 0;

        setLayout(new BorderLayout());

        createStepIndicator();
        createCardPanels();

        add(stepIndicatorPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        updateStepIndicator();
    }

    // Üstteki step indicator alanını oluşturur.
    private void createStepIndicator() {
        stepIndicatorPanel = new JPanel(new GridLayout(1, stepNames.length));
        stepLabels = new JLabel[stepNames.length];

        for (int i = 0; i < stepNames.length; i++) {
            stepLabels[i] = new JLabel((i + 1) + ". " + stepNames[i], SwingConstants.CENTER);
            stepLabels[i].setOpaque(true);
            stepLabels[i].setBorder(BorderFactory.createEmptyBorder(12, 5, 12, 5));
            stepIndicatorPanel.add(stepLabels[i]);
        }
    }

    // CardLayout içindeki ekranları oluşturur.
    private void createCardPanels() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        profilePanel = new ProfilePanel(this);
        definePanel = new DefinePanel(this);
        planPanel = new PlanPanel(this);
        collectPanel = new CollectPanel(this);
        analysePanel = new AnalysePanel(this);

        cardPanel.add(profilePanel, "Profile");
        cardPanel.add(definePanel, "Define");
        cardPanel.add(planPanel, "Plan");
        cardPanel.add(collectPanel, "Collect");
        cardPanel.add(analysePanel, "Analyse");
    }

    // Bir sonraki adıma geçer.
    public void nextStep() {
        if (currentStep < stepNames.length - 1) {
            currentStep++;

            if (currentStep == 2) {
                planPanel.refreshTable();
            } else if (currentStep == 3) {
                collectPanel.refreshTable();
            } else if (currentStep == 4) {
                analysePanel.refreshAnalysis();
            }

            cardLayout.show(cardPanel, stepNames[currentStep]);
            updateStepIndicator();
        }
    }

    // Bir önceki adıma döner.
    public void previousStep() {
        if (currentStep > 0) {
            currentStep--;
            cardLayout.show(cardPanel, stepNames[currentStep]);
            updateStepIndicator();
        }
    }

    // Step indicator görünümünü günceller.
    private void updateStepIndicator() {
        for (int i = 0; i < stepLabels.length; i++) {
            if (i < currentStep) {
                stepLabels[i].setText("✓ " + stepNames[i]);
                stepLabels[i].setBackground(new Color(220, 240, 220));
                stepLabels[i].setFont(stepLabels[i].getFont().deriveFont(Font.PLAIN));
            } else if (i == currentStep) {
                stepLabels[i].setText((i + 1) + ". " + stepNames[i]);
                stepLabels[i].setBackground(new Color(180, 210, 255));
                stepLabels[i].setFont(stepLabels[i].getFont().deriveFont(Font.BOLD));
            } else {
                stepLabels[i].setText((i + 1) + ". " + stepNames[i]);
                stepLabels[i].setBackground(new Color(235, 235, 235));
                stepLabels[i].setFont(stepLabels[i].getFont().deriveFont(Font.PLAIN));
            }
        }
    }

    // Profile ekranındaki bilgileri kaydeder.
    public void saveProfileInformation(String username, String school, String sessionName) {
        userSession.setProfileInformation(username, school, sessionName);
    }

    // Define ekranındaki seçimleri kaydeder.
    public void saveDefinitionInformation(String qualityType, String mode, Scenario scenario) {
        userSession.setDefinitionInformation(qualityType, mode, scenario);
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public Scenario getSelectedScenario() {
        return userSession.getSelectedScenario();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}