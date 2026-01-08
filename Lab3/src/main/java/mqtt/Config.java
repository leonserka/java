package mqtt;

import java.util.List;

public class Config {
    public List<DeviceConfig> devices;

    public Config() {}
}

class DeviceConfig {
    public String id;
    public String broker;
    public String topicPrefix;
    public List<Spec> specs;

    public DeviceConfig() {}
}

class Spec {
    public String type;
    public double min;
    public double max;
    public int factor;
    public String unit;

    public Spec() {}
}
