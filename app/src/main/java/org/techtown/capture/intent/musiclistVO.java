package org.techtown.capture.intent;

public class musiclistVO {

    private  String name;

    private  String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public musiclistVO(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
