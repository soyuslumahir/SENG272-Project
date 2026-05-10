package model;

import java.util.ArrayList;

// Kalite boyutunu ve ona bağlı metrikleri tutar.
public class Dimension {
    private String name;
    private int coefficient;
    private ArrayList<Metric> metrics;

    public Dimension(String name, int coefficient) {
        this.name = name;
        this.coefficient = coefficient;
        this.metrics = new ArrayList<>();
    }

    // Boyuta yeni metrik ekler.
    public void addMetric(Metric metric) {
        metrics.add(metric);
    }

    // Metrik skorlarına göre ağırlıklı boyut skorunu hesaplar.
    public double calculateWeightedScore() {
        double totalWeightedScore = 0.0;
        int totalCoefficient = 0;

        for (Metric metric : metrics) {
            totalWeightedScore += metric.getScore() * metric.getCoefficient();
            totalCoefficient += metric.getCoefficient();
        }

        if (totalCoefficient == 0) {
            return 0.0;
        }

        return totalWeightedScore / totalCoefficient;
    }

    public String getName() {
        return name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public ArrayList<Metric> getMetrics() {
        return metrics;
    }
}