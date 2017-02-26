package net.easysmarthouse.mobile.ui.android.domain;

import java.io.Serializable;

/**
 * Created by rusakovich on 26.02.2017.
 */
public class Webcam implements Serializable{

    private String name;
    private String image;

    public synchronized String getImage() {
        return image;
    }

    public synchronized void setImage(String image) {
        this.image = image;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }
}
