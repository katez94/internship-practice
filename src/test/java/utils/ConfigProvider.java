package utils;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigProvider {
    private String startPageUrl;
    private String imageFullPath;

    private static ConfigProvider config;
    private static final String CONFIG_JSON_FILE = "src/test/resources/config.json";

    private ConfigProvider() {
    }

    public static ConfigProvider getInstance() {
        if (config == null) {
            config = getConfigFromJson(CONFIG_JSON_FILE);
            return config;
        }
        return config;
    }

    private static ConfigProvider getConfigFromJson(String fileName) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader(fileName), ConfigProvider.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Json config not found!");
        }
    }

    public String getStartPageUrl() {
        return startPageUrl;
    }

    public String getImageFullPath(){
        return imageFullPath;
    }
}
