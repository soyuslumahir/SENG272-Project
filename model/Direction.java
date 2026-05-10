package model;

// Metrik yönlerini sabit olarak tutar.
public enum Direction {
    HIGHER_BETTER("Higher ↑"),
    LOWER_BETTER("Lower ↓");

    private final String displayText;

    Direction(String displayText) {
        this.displayText = displayText;
    }

    // Ekranda gösterilecek yön yazısını döndürür.
    public String getDisplayText() {
        return displayText;
    }
}