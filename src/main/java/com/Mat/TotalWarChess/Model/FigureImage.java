package com.Mat.TotalWarChess.Model;

public class FigureImage {
    String tileName;
    String imageName;
    String path;
    String backgroundColor;
    String nameForColor;
    String borders;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getNameForColor() {
        return nameForColor;
    }

    public void setNameForColor(String nameForColor) {
        this.nameForColor = nameForColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getTileName() {
        return tileName;
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public FigureImage(String tileName, String imageName, String path, String borders, String backgroundColor, String nameForColor) {
        this.imageName = imageName;
        this.path = path;
        this.tileName = tileName;
        this.borders = borders;
        this.backgroundColor = backgroundColor;
        this.nameForColor = nameForColor;
    }


}
