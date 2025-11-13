package mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Config config = DeserializerUtil.load("src/main/resources/config.json");

            List<WaterFlowMeter> meters = new ArrayList<>();
            for (DeviceConfig dc : config.devices) {
                meters.add(new WaterFlowMeter(dc.broker, dc.id, dc.topicPrefix, dc.specs));
            }
            while (true) {
                for (WaterFlowMeter meter : meters) {
                    List<WaterData> measurements = meter.generateAll();
                    for (WaterData data : measurements) {
                        meter.publish(data);
                        Thread.sleep(500);
                    }
                    System.out.println("Završio ciklus za: " + meter);
                }
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
