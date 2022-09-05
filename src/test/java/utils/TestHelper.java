package utils;

import org.apache.commons.lang.RandomStringUtils;

public class TestHelper {
    public static String generateRandomText(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }
}
