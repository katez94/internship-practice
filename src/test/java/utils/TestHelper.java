package utils;

public class TestHelper {
    public static String removeAllButNumbers(String s) {
        return s.replaceAll("\\D", "");
    }

    public static String removeSlashes(String s) {
        return s.replace("\\", "");
    }
}
