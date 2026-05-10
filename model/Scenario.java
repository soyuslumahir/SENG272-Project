package model;

import java.util.ArrayList;

// Seçilen senaryonun bilgilerini ve boyut listesini tutar.
public class Scenario {
    private String name;
    private String mode;
    private String qualityType;
    private ArrayList<Dimension> dimensions;

    public Scenario(String name, String mode, String qualityType, ArrayList<Dimension> dimensions) {
        this.name = name;
        this.mode = mode;
        this.qualityType = qualityType;
        this.dimensions = dimensions;
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public String getQualityType() {
        return qualityType;
    }

    public ArrayList<Dimension> getDimensions() {
        return dimensions;
    }

    // HashMap içinde kullanılacak benzersiz anahtar.
    public String getKey() {
        return mode + " - " + name;
    }

    // JComboBox içinde senaryo adının görünmesini sağlar.
    @Override
    public String toString() {
        return name;
    }
}