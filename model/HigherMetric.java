package model;

// Yüksek değerin daha iyi olduğu metrikler için kullanılır.
public class HigherMetric extends Metric {

    public HigherMetric(String name, int coefficient, double min, double max, String unit) {
        super(name, coefficient, min, max, unit);
    }

    // Metrik yönünü döndürür.
    @Override
    public Direction getDirection() {
        return Direction.HIGHER_BETTER;
    }

    // Higher is better skor formülü.
    @Override
    protected double calculateRawScore(double value) {
        return 1 + ((value - getMin()) / (getMax() - getMin())) * 4;
    }
}