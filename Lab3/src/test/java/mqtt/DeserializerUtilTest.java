package mqtt;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

public class DeserializerUtilTest {

    @Test
    public void testLoadConfig() throws IOException {
        Config config = DeserializerUtil.load("src/main/resources/config.json");
        assertNotNull(config);
        assertFalse(config.devices.isEmpty());
        assertFalse(config.devices.get(0).specs.isEmpty());
    }
}
