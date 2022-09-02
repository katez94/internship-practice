package utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TestHelper {

    public static char createCyrillicSymbol(){
        return (char)(1040 + new Random().nextInt(63));
    }

    public static void uploadImage(String imageFullPath) throws AWTException {
        Robot robot = new Robot();
        StringSelection imagePath = new StringSelection(imageFullPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imagePath, null);
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
