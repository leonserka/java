package mqtt;

import org.eclipse.paho.client.mqttv3.*;
import java.util.*;

public class WaterFlowMeter {

    public String id;
    public String topicPrefix;
    public MqttClient client;
    public List<Spec> specs;
    public Random rand = new Random();

    public WaterFlowMeter(String broker, String id, String topicPrefix, List<Spec> specs) throws MqttException {
        this.id = id;
        this.topicPrefix = topicPrefix;
        this.specs = specs;

        client = new MqttClient(broker, id);
        MqttConnectOptions opts = new MqttConnectOptions();
        opts.setCleanSession(true);
        client.connect(opts);
    }

    public List<WaterData> generateAll() {
        List<WaterData> result = new ArrayList<>();
        for (Spec s : specs) {
            double value = s.min + rand.nextDouble() * (s.max - s.min);
            value = round(value, s.factor == 0 ? 0 : 1);
            int raw = (int)(value * s.factor);
            result.add(new WaterData(s.type, value, raw, s.unit));
        }
        return result;
    }

    public void publish(WaterData data) throws MqttException {
        String topic = topicPrefix + "/" + data.type;
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
