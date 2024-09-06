package com.Mat.TotalWarChess.Model;

import org.springframework.ui.Model;

import java.util.ArrayList;

public class ChessMoves {

    public ArrayList<FigureImage> imagesArray = new ArrayList<>();

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

        public void hightlightTile(String tile) {
            for (int i = 0; i < imagesArray.size(); i++) {
                if (imagesArray.get(i).tileName.equals(tile)) {
                    imagesArray.get(i).setBorders("1.5px dashed  #1994da");
                    break;

                }

        }
    }
    public void unHightlightTile() {
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).borders.equals("1.5px dashed  #1994da")) {
                imagesArray.get(i).setBorders("1px solid");
                break;

            }

        }
    }

    public void updateModelWithHighlightedTiles(Model model) {
        for (FigureImage figureImage : imagesArray) {
            model.addAttribute(figureImage.getNameForColor(), figureImage.getBorders());
        }
    }
}
