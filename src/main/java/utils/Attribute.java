package utils;

public enum Attribute {
    BASE_URL("/base_url"),
    TOKEN("/token"),
    OWNER_ID("/owner_id"),
    VERSION("/version");

    private final String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
