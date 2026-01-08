package mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<WaterFlowMeter> meters = Arrays.asList(
                    new WaterFlowMeter("tcp://localhost:1883", "Meter01", "iot/water/meter1"),
                    new WaterFlowMeter("tcp://localhost:1883", "Meter02", "iot/water/meter2")
            );

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

        } catch (MqttException | InterruptedException e) {
            System.err.println("Greška u radu sustava:");
            e.printStackTrace();
        }
    }
}
