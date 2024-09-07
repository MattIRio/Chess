package com.Mat.TotalWarChess.Model;

public class FigureImage {
    String tileName;
    String imageName;
    String path;
    int tilenumber;
    String nameForColor;
    String borders;

    public int getTilenumber() {
        return tilenumber;
    }

    public String getNameForColor() {
        return nameForColor;
    }

    public void setNameForColor(String nameForColor) {
        this.nameForColor = nameForColor;
    }

    public void setTilenumber(int tilenumber) {
        this.tilenumber = tilenumber;
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


    public FigureImage(String tileName, String imageName, String path, String borders, int tilenumber, String nameForColor) {
        this.imageName = imageName;
        this.path = path;
        this.tileName = tileName;
        this.borders = borders;
        this.tilenumber = tilenumber;
        this.nameForColor = nameForColor;
    }


}
