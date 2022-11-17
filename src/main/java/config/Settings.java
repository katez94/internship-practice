package config;

public class Settings {
    public static final String DB_URL = JsonAttributes.getValueFromJson(JsonAttributes.DB_URL);
    public static final String DB_USER = JsonAttributes.getValueFromJson(JsonAttributes.DB_USER);;
    public static final String DB_PASSWORD = JsonAttributes.getValueFromJson(JsonAttributes.DB_PASSWORD);;
}
