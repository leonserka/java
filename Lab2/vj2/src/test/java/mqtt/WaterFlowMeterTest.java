package mqtt;


import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttException;

public class WaterFlowMeterTest {

    @Test
    public void testTemperatureRange() throws MqttException {
        WaterFlowMeter m = new WaterFlowMeter("tcp://localhost:1883", "Meter01", "iot/water/meter1");
        List<WaterData> data = m.generateAll();

        double temperature = data.get(0).getValue();
        assertTrue("Temperature out of expected range: " + temperature,
                temperature >= -3266.8 && temperature <= 3266.8);
    }
}
