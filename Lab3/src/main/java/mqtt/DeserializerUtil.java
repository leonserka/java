package mqtt;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class DeserializerUtil {

    public static Config load(String path) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, Config.class);
        }
    }
}
