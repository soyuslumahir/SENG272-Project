package view;

import model.Dimension;
import model.Scenario;

import javax.swing.*;
import java.awt.*;

// Boyut skorlarını, radar chartı ve gap analysis bilgisini gösterir.
public class AnalysePanel extends JPanel {
    private MainFrame frame;

    private JPanel scorePanel;
    private JPanel gapPanel;
    private RadarChart radarChart;

    public AnalysePanel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 5: Analyse", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        gapPanel = new JPanel();
        gapPanel.setLayout(new BoxLayout(gapPanel, BoxLayout.Y_AXIS));
        gapPanel.setBorder(BorderFactory.createTitledBorder("Gap Analysis"));

        radarChart = new RadarChart();

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(scorePanel));
        centerPanel.add(radarChart);

        add(centerPanel, BorderLayout.CENTER);
        add(gapPanel, BorderLayout.EAST);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Analyse ekranındaki bilgileri yeniler.
    public void refreshAnalysis() {
        scorePanel.removeAll();
        gapPanel.removeAll();

        Scenario scenario = frame.getSelectedScenario();

        if (scenario == null) {
            return;
        }

        Dimension lowestDimension = null;
        double lowestScore = 6.0;

        radarChart.clearData();

        for (Dimension dimension : scenario.getDimensions()) {
            double dimensionScore = dimension.calculateWeightedScore();

            addScoreBar(dimension.getName(), dimensionScore);
            radarChart.addDimensionScore(dimension.getName(), dimensionScore);

            if (dimensionScore < lowestScore) {
                lowestScore = dimensionScore;
                lowestDimension = dimension;
            }
        }

        if (lowestDimension != null) {
            addGapAnalysis(lowestDimension, lowestScore);
        }

        scorePanel.revalidate();
        scorePanel.repaint();

        gapPanel.revalidate();
        gapPanel.repaint();

        radarChart.repaint();
    }

    // Her boyut için progress bar ekler.
    private void addScoreBar(String dimensionName, double score) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(dimensionName + " : " + formatNumber(score));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JProgressBar progressBar = new JProgressBar(0, 50);
        progressBar.setValue((int) Math.round(score * 10));
        progressBar.setStringPainted(true);
        progressBar.setString(formatNumber(score) + " / 5.0");

        rowPanel.add(nameLabel, BorderLayout.NORTH);
        rowPanel.add(progressBar, BorderLayout.CENTER);

        scorePanel.add(rowPanel);
    }

    // En düşük skora göre gap analysis bilgilerini ekler.
    private void addGapAnalysis(Dimension dimension, double score) {
        double gapValue = 5.0 - score;
        String qualityLevel = getQualityLevel(score);

        gapPanel.add(createGapLabel("Lowest Dimension: " + dimension.getName()));
        gapPanel.add(createGapLabel("Score: " + formatNumber(score)));
        gapPanel.add(createGapLabel("Gap Value: " + formatNumber(gapValue)));
        gapPanel.add(createGapLabel("Quality Level: " + qualityLevel));
        gapPanel.add(Box.createVerticalStrut(15));
        gapPanel.add(createGapLabel("This dimension has the lowest score and requires the most improvement."));
    }

    // Skora göre kalite seviyesini döndürür.
    private String getQualityLevel(double score) {
        if (score >= 4.5) {
            return "Excellent";
        } else if (score >= 3.5) {
            return "Good";
        } else if (score >= 2.5) {
            return "Needs Improvement";
        } else {
            return "Poor";
        }
    }

    // Gap analysis için label oluşturur.
    private JLabel createGapLabel(String text) {
        JLabel label = new JLabel("<html>" + text + "</html>");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        return label;
    }

    // Sayıları ekranda düzgün gösterir.
    private String formatNumber(double number) {
        return String.format("%.1f", number);
    }

    // Back butonunu oluşturur.
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> frame.previousStep());

        buttonPanel.add(backButton);

        return buttonPanel;
    }
}