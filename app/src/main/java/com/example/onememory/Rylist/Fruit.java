package com.example.onememory.Rylist;

public class Fruit {

    private String name;
    private int imageId;
    private int white_imageId;
    private String background_color;
    private String text_color;

    public Fruit(String name, int imageId, int white_imageId, String background_color, String text_color) {
        this.name = name;
        this.imageId = imageId;
        this.white_imageId = white_imageId;
        this.background_color = background_color;
        this.text_color = text_color;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getWhite_imageId() {
        return white_imageId;
    }

    public void setWhite_imageId(int white_imageId) {
        this.white_imageId = white_imageId;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }
}