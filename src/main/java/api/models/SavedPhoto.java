package api.models;

import lombok.Data;
import utils.RegexHelper;

@Data
public class SavedPhoto {
    private String server;
    private String photo;
    private String hash;

    public SavedPhoto(String server, String photo, String hash) {
        this.server = server;
        this.photo = RegexHelper.removeSlashes(photo);
        this.hash = hash;
    }
}
