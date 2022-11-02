package utils;

public class RegexHelper {
    private static final String removeNumbersRegex = "\\D";
    private static final String removeSlashesRegex = "\\";

    public static String removeAllButNumbers(String s) {
        return s.replaceAll(removeNumbersRegex, "");
    }

    public static String removeSlashes(String s) {
        return s.replace(removeSlashesRegex, "");
    }
}
