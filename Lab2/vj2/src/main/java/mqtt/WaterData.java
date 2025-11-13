package mqtt;

public class WaterData {

    private String type; //tip podatka (npr. što se mjeri)
    private double value; // stvarna vrijednost mjerenja
    private int raw; // "sirova" vrijednost
    private String unit; // jedinica mjere

    public WaterData(String type, double value, int raw, String unit) { //stvaranje novog objekta WaterData
        this.type = type;
        this.value = value;
        this.raw = raw;
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public int getRaw() {
        return raw;
    }

    public String getUnit() {
        return unit;
    }

    public String toString() {
        return "{"
                + "\"type\":\"" + type + "\", "
                + "\"value\":" + value + ", "
                + "\"raw\":" + raw + ", "
                + "\"unit\":\"" + unit + "\""
                + "}";
    }
}
