package model;

// Düşük değerin daha iyi olduğu metrikler için kullanılır.
public class LowerMetric extends Metric {

    public LowerMetric(String name, int coefficient, double min, double max, String unit) {
        super(name, coefficient, min, max, unit);
    }

    // Metrik yönünü döndürür.
    @Override
    public Direction getDirection() {
        return Direction.LOWER_BETTER;
    }

    // Lower is better skor formülü.
    @Override
    protected double calculateRawScore(double value) {
        return 5 - ((value - getMin()) / (getMax() - getMin())) * 4;
    }
}