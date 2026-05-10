package view;

import javax.swing.*;
import java.awt.*;

// Kullanıcı profil bilgilerini alan ilk ekran.
public class ProfilePanel extends JPanel {
    private MainFrame frame;

    private JTextField usernameField;
    private JTextField schoolField;
    private JTextField sessionNameField;

    public ProfilePanel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 1: Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(createFormPanel());
        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Form alanlarını oluşturur.
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("User Information"),
                BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));

        GridBagConstraints gbc = new GridBagConstraints();

        usernameField = new JTextField(22);
        schoolField = new JTextField(22);
        sessionNameField = new JTextField(22);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;

        addFormRow(formPanel, gbc, 0, "Username:", usernameField);
        addFormRow(formPanel, gbc, 1, "School:", schoolField);
        addFormRow(formPanel, gbc, 2, "Session Name:", sessionNameField);

        return formPanel;
    }

    // Tek bir label + textfield satırı ekler.
    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;

        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(260, 30));
        panel.add(textField, gbc);
    }

    // Next butonunu oluşturur.
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setPreferredSize(new Dimension(90, 32));

        nextButton.addActionListener(e -> validateAndContinue());

        buttonPanel.add(nextButton);
        return buttonPanel;
    }

    // Boş alan kontrolü yapar ve bilgileri kaydeder.
    private void validateAndContinue() {
        String username = usernameField.getText().trim();
        String school = schoolField.getText().trim();
        String sessionName = sessionNameField.getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username to continue.");
            return;
        }

        if (school.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your school name to continue.");
            return;
        }

        if (sessionName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a session name to continue.");
            return;
        }

        frame.saveProfileInformation(username, school, sessionName);
        frame.nextStep();
    }
}