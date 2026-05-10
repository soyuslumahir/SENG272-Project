package model;

// Tüm metrikler için ortak temel sınıf.
public abstract class Metric {
    private String name;
    private int coefficient;
    private double min;
    private double max;
    private String unit;
    private double value;
    private double score;

    public Metric(String name, int coefficient, double min, double max, String unit) {
        this.name = name;
        this.coefficient = coefficient;
        this.min = min;
        this.max = max;
        this.unit = unit;
        this.value = 0.0;
        this.score = 0.0;
    }

    // Metrik yönü alt sınıflarda belirlenir.
    public abstract Direction getDirection();

    // Ham skor hesabı alt sınıflarda yapılır.
    protected abstract double calculateRawScore(double value);

    // Skoru hesaplar, 1-5 arasına sınırlar ve 0.5'e yuvarlar.
    public void calculateScore(double value) {
        this.value = value;

        double calculatedScore = calculateRawScore(value);

        if (calculatedScore < 1.0) {
            calculatedScore = 1.0;
        } else if (calculatedScore > 5.0) {
            calculatedScore = 5.0;
        }

        this.score = Math.round(calculatedScore * 2.0) / 2.0;
    }

    public String getName() {
        return name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    // Tablo için aralık bilgisini hazırlar.
    public String getRange() {
        return formatNumber(min) + "-" + formatNumber(max);
    }

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    public double getScore() {
        return score;
    }

    // Tablo için yön yazısını döndürür.
    public String getDirectionText() {
        return getDirection().getDisplayText();
    }

    // Tam sayıları .0 olmadan gösterir.
    private String formatNumber(double number) {
        if (number == (int) number) {
            return String.valueOf((int) number);
        }
        return String.valueOf(number);
    }
}