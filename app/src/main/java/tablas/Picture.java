package tablas;

import java.io.Serializable;
import java.util.Arrays;

public class Picture implements Serializable {

    private int id;
    private String name;
    private String description;
    private String pathImage;
    private byte[] image;

    public Picture(int id, String name, String description, String pathImage, byte[] image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pathImage = pathImage;
        this.image = image;
    }

    public Picture(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
