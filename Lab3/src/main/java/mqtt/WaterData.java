package mqtt;

import com.google.gson.Gson;

public class WaterData {

    public String type;
    public double value;
    public int raw;
    public String unit;

    public WaterData() {} //

    public WaterData(String type, double value, int raw, String unit) {
        this.type = type;
        this.value = value;
        this.raw = raw;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
