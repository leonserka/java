package mqtt;

import org.eclipse.paho.client.mqttv3.*;
import java.util.*;

public class WaterFlowMeter {
    private final String id;
    private final String topicPrefix;
    private final MqttClient client;
    private final Random rand = new Random();

    private static class Spec { //tip senzorskog podatka
        String type;
        double min;
        double max;
        int factor;
        String unit;

        Spec(String type, double min, double max, int factor, String unit) {
            this.type = type;
            this.min = min;
            this.max = max;
            this.factor = factor;
            this.unit = unit;
        }
    }

    private final List<Spec> specs = Arrays.asList(
            // Temp
            new Spec("temperature", -3266.8, 3266.8, 10, "°C"),
            // Pressure
            new Spec("pressure", 0, 65.336, 1000, "Bar"),
            // Consumption L
            new Spec("consumption_1min", 0, 65336, 0, "L"),
            new Spec("consumption_10min", 0, 65336, 0, "L"),
            new Spec("consumption_1h", 0, 65336, 0, "L"),
            new Spec("consumption_1day", 0, 65336, 0, "L"),
            // Consumption m3
            new Spec("consumption_1week", 0, 6533.6, 10, "m³"),
            new Spec("consumption_1month", 0, 6533.6, 10, "m³"),
            new Spec("consumption_1year", 0, 6533.6, 10, "m³")
    );

    public WaterFlowMeter(String broker, String id, String topicPrefix) throws MqttException { //spajanje na mqtt iz main
        this.id = id;
        this.topicPrefix = topicPrefix;

        client = new MqttClient(broker, id);
        MqttConnectOptions opts = new MqttConnectOptions();
        opts.setCleanSession(true);
        client.connect(opts);
    }

    public List<WaterData> generateAll() { //generira mjerenja
        List<WaterData> result = new ArrayList<>();

        for (Spec s : specs) {
            double value = s.min + rand.nextDouble() * (s.max - s.min);
            value = round(value, s.factor == 0 ? 0 : 1);

            int raw = (int)(value * s.factor);

            result.add(new WaterData(s.type, value, raw, s.unit));
        }

        return result;
    }

    public void publish(WaterData data) throws MqttException {  // objavljuje njerenja
        String topic = topicPrefix + "/" + data.getType();
        client.publish(topic, new MqttMessage(data.toString().getBytes()));
        System.out.println("Published → " + data);
    }

    private double round(double val, int dec) {
        double f = Math.pow(10, dec);
        return Math.round(val * f) / f;
    }
    @Override
    public String toString() {
        return id;
    }

}

