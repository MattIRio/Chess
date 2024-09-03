package com.Mat.TotalWarChess.Model;

public class FigureImage {
    String tileName;
    String imageName;
    String path;

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


    public FigureImage(String tileName, String imageName, String path ) {
        this.imageName = imageName;
        this.path = path;
        this.tileName = tileName;
    }


}
