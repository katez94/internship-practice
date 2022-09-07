package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.commons.lang.RandomStringUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

public class TestHelper {

    public static char createCyrillicSymbol() {
        return (char) (1040 + new Random().nextInt(63));
    }

    public static String generatePassword(String email) {
        String oneLetterFromEmail = email.substring(0, 1);
        char cyrillycLetter = TestHelper.createCyrillicSymbol();
        return RandomStringUtils.randomAlphabetic(7) + RandomStringUtils.randomNumeric(1) + cyrillycLetter + oneLetterFromEmail;
    }

    public static String getImagePathFromJson(){
        ISettingsFile environment = new JsonSettingsFile("config.json");
        return environment.getValue("/imagePath").toString();
    }

    public static void uploadImage(String imagePath) throws AWTException {
        String imageFullPath = new File(imagePath).getAbsolutePath();
        Robot robot = new Robot();
        StringSelection imagePathToFind = new StringSelection(imageFullPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imagePathToFind, null);
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(90);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
