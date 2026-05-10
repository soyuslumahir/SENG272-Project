package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Boyut skorlarını radar chart olarak çizer.
public class RadarChart extends JPanel {
    private ArrayList<String> dimensionNames;
    private ArrayList<Double> scores;

    public RadarChart() {
        dimensionNames = new ArrayList<>();
        scores = new ArrayList<>();
        setPreferredSize(new Dimension(400, 400));
        setBorder(BorderFactory.createTitledBorder("Radar Chart"));
    }

    // Yeni boyut skoru ekler.
    public void addDimensionScore(String dimensionName, double score) {
        dimensionNames.add(dimensionName);
        scores.add(score);
    }

    // Eski chart verilerini temizler.
    public void clearData() {
        dimensionNames.clear();
        scores.clear();
    }

    // Chart çizimini yapar.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (scores.isEmpty()) {
            drawEmptyMessage(g);
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 3;

        drawGrid(g2, centerX, centerY, radius);
        drawAxesAndLabels(g2, centerX, centerY, radius);
        drawScorePolygon(g2, centerX, centerY, radius);
    }

    // Veri yoksa mesaj gösterir.
    private void drawEmptyMessage(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("No data available", 130, 180);
    }

    // Radar chart arka plan çizgilerini çizer.
    private void drawGrid(Graphics2D g2, int centerX, int centerY, int radius) {
        int count = scores.size();

        g2.setColor(Color.LIGHT_GRAY);

        for (int level = 1; level <= 5; level++) {
            int levelRadius = radius * level / 5;

            int[] xPoints = new int[count];
            int[] yPoints = new int[count];

            for (int i = 0; i < count; i++) {
                double angle = 2 * Math.PI * i / count - Math.PI / 2;

                xPoints[i] = centerX + (int) (Math.cos(angle) * levelRadius);
                yPoints[i] = centerY + (int) (Math.sin(angle) * levelRadius);
            }

            g2.drawPolygon(xPoints, yPoints, count);
        }
    }

    // Eksenleri ve boyut isimlerini çizer.
    private void drawAxesAndLabels(Graphics2D g2, int centerX, int centerY, int radius) {
        int count = scores.size();

        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        g2.setColor(Color.DARK_GRAY);

        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count - Math.PI / 2;

            int axisX = centerX + (int) (Math.cos(angle) * radius);
            int axisY = centerY + (int) (Math.sin(angle) * radius);

            g2.drawLine(centerX, centerY, axisX, axisY);

            int labelX = centerX + (int) (Math.cos(angle) * (radius + 35));
            int labelY = centerY + (int) (Math.sin(angle) * (radius + 35));

            String label = dimensionNames.get(i);
            g2.drawString(label, labelX - 30, labelY);
        }
    }

    // Skorlara göre iç poligonu çizer.
    private void drawScorePolygon(Graphics2D g2, int centerX, int centerY, int radius) {
        int count = scores.size();

        int[] xPoints = new int[count];
        int[] yPoints = new int[count];

        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count - Math.PI / 2;
            double scoreRatio = scores.get(i) / 5.0;
            int scoreRadius = (int) (radius * scoreRatio);

            xPoints[i] = centerX + (int) (Math.cos(angle) * scoreRadius);
            yPoints[i] = centerY + (int) (Math.sin(angle) * scoreRadius);
        }

        g2.setColor(new Color(100, 150, 255, 90));
        g2.fillPolygon(xPoints, yPoints, count);

        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2));
        g2.drawPolygon(xPoints, yPoints, count);
    }
}