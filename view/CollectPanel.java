package view;

import model.Dimension;
import model.Metric;
import model.Scenario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Ham verileri ve hesaplanan skorları gösterir.
public class CollectPanel extends JPanel {
    private MainFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public CollectPanel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 4: Collect Data", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        createTable();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Collect tablosunu oluşturur.
    private void createTable() {
        String[] columns = {
                "Metric",
                "Direction",
                "Range",
                "Value",
                "Score (1-5)",
                "Coeff / Unit"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            // Tabloyu read-only yapar.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setFont(new Font("Arial", Font.PLAIN, 13));
    }

    // Seçilen senaryoya göre tabloyu yeniler.
    public void refreshTable() {
        tableModel.setRowCount(0);

        Scenario scenario = frame.getSelectedScenario();

        if (scenario == null) {
            return;
        }

        for (Dimension dimension : scenario.getDimensions()) {
            for (Metric metric : dimension.getMetrics()) {
                Object[] row = {
                        metric.getName(),
                        metric.getDirectionText(),
                        metric.getRange(),
                        formatNumber(metric.getValue()),
                        formatNumber(metric.getScore()),
                        metric.getCoefficient() + " / " + metric.getUnit()
                };

                tableModel.addRow(row);
            }
        }
    }

    // Sayıları tabloda düzgün gösterir.
    private String formatNumber(double number) {
        if (number == (int) number) {
            return String.valueOf((int) number);
        }
        return String.valueOf(number);
    }

    // Back ve Next butonlarını oluşturur.
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));

        backButton.addActionListener(e -> frame.previousStep());
        nextButton.addActionListener(e -> frame.nextStep());

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        return buttonPanel;
    }
}