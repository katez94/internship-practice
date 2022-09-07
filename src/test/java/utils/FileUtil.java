package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static String getTargetFilePath(String fileName) {
        String downloadDirectory = AqualityServices.getBrowser().getDownloadDirectory();

        // below is workaround for case when local FS is different from remote (e.g. local machine runs on Windows but remote runs on Linux)
        if (downloadDirectory.contains("/") && !downloadDirectory.endsWith("/")) {
            downloadDirectory = downloadDirectory.concat("/");
        }
        if (downloadDirectory.contains("\\") && !downloadDirectory.endsWith("\\")) {
            downloadDirectory = downloadDirectory.concat("\\");
        }
        return downloadDirectory.concat(fileName);
    }
}
