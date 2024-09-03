package com.Mat.TotalWarChess.Model;

import java.util.ArrayList;

public class ChessMoves {

    ArrayList<FigureImage> imagesArray = new ArrayList<>();

    public void addFigure(FigureImage figureImage) {
        imagesArray.add(figureImage);
    }

//    public void getFigureName(int number) {
//        imagesArray.get(number).getImageName();
//    }
//    public void getImagePath(int number) {
//        imagesArray.get(number).getPath();
//    }















    public void swapTiles(String firstTileName, String secondTileName) {
        String path = "";
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(firstTileName)) {
                path = imagesArray.get(i).path;
                imagesArray.get(i).setPath("images/blank.png");
                break;
            }
        }
            for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(secondTileName)) {
                imagesArray.get(i).setPath(path);
                break;
            }
        }

    }

}
