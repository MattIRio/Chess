package com.Mat.TotalWarChess.Model;

import org.springframework.ui.Model;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ChessMoves {
    ArrayList<String> queenPosibleTurnsReikland = new ArrayList();
    ArrayList<String> queenPosibleTurnsGreenskins = new ArrayList();
    public ArrayList<FigureImage> imagesArray = new ArrayList<>();

    public void addFigure(FigureImage figureImage) {
        imagesArray.add(figureImage);
    }

    //CHECKING IF THE TWO CELLS ARE HORIZONTAL, VERTICAL OR DIAGONAL
    public boolean isHorizontal(int firstTile, int secondTile){
        boolean result = false;
        if((firstTile / 24) == ((secondTile -1) / 24)){
            result = true;
        }
        return result;
    }
    public boolean isVertical(int firstTile, int secondTile){
        boolean result = false;
        if((firstTile - secondTile) % 24 == 0){
            result = true;
        }
        return result;
    }

    public boolean isDiagonalTopRightbottomLeft(int firstTile, int secondTile){
        boolean result = false;
        if((firstTile - secondTile) % 25 == 0){
            result = true;
        }
        return result;
    }
    public boolean isDiagonalTopLeftbottomRight(int firstTile, int secondTile){
        boolean result = false;
        if((firstTile - secondTile) % 23 == 0){
            result = true;
        }
        return result;
    }

    //SWAP TILES
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

    //HIGHTLIGHT PICKED TILES
    public void hightlightTile(String tile) {
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(tile)) {
                imagesArray.get(i).setBorders("10px");
                break;

            }

        }
    }

    //UNHIGHTLIGHT PICKED TILES
    public void unHightlightTile() {
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).borders.equals("10px")) {
                imagesArray.get(i).setBorders("");
                break;

            }

        }
    }

    public void updateModelWithHighlightedTiles(Model model) {
        for (FigureImage figureImage : imagesArray) {
            model.addAttribute(figureImage.getNameForColor(), figureImage.getBorders());
        }
    }

    //EMPTY TILE PLAYER TURN CHECKER
    public boolean isTileEmpty(String tile) {
        boolean returnstatement = false;
        boolean isfound = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(tile)) {
                if (imagesArray.get(i).getPath().equals("images/blank.png") && isfound == false) {
                    returnstatement = true;
                    isfound = true;
                    break;
                } else {
                    returnstatement = false;
                }
            }
        }
        return returnstatement;
    }

    //FIRST PLAYER TURN CHECKER
    public boolean isFirstPlayerTurn(String tile) {
        boolean returnstatement = false;
        boolean isfound = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(tile)) {
                if (imagesArray.get(i).getPath().contains("greenskin") || imagesArray.get(i).getPath().contains("blank") && isfound == false) {
                    returnstatement = true;
                    isfound = true;
                    break;
                }
                if (!imagesArray.get(i).getPath().contains("greenskin") || imagesArray.get(i).getPath().contains("blank")) {
                    returnstatement = false;
                }
            }
        }
        return returnstatement;
    }

    //SECOND PLAYER TURN CHECKER
    public boolean isSecondPlayerTurn(String tile) {
        boolean returnstatement = false;
        boolean isfound = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(tile)) {
                if (!imagesArray.get(i).getPath().contains("greenskin") || imagesArray.get(i).getPath().contains("blank") && isfound == false) {
                    returnstatement = true;
                    isfound = true;
                    break;
                }
            }
        }

        return returnstatement;
    }

    //FIGURE DETECTOR
    public String figureChecker(String tile) {
        String returnstatement = "";
        boolean isfound = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(tile) && isfound == false) {
                if (imagesArray.get(i).getPath().contains("pawn")) {
                    returnstatement = "pawn";
                    isfound = true;
                    break;
                }
                if (imagesArray.get(i).getPath().contains("king")) {
                    returnstatement = "king";
                    isfound = true;
                    break;
                }
                if (imagesArray.get(i).getPath().contains("vizard")) {
                    returnstatement = "vizard";
                    isfound = true;
                    break;
                }
                if (imagesArray.get(i).getPath().contains("cavalry")) {
                    returnstatement = "cavalry";
                    isfound = true;
                    break;
                }
                if (imagesArray.get(i).getPath().contains("queen")) {
                    returnstatement = "queen";
                    isfound = true;
                    break;
                }
            }
        }
        return returnstatement;
    }

    //PAWN MOVEMENT LOGIC GREEN SKIN
    public boolean PawnLogicGreenSkin(String fromTile, String toTile) {
        boolean returnstatement = false;
        int fromPosition = 0;
        int toPosition = 0;
        boolean figurePresenceLeft = false;
        boolean figurePresenceRight = false;
        boolean figurePresenceAhead = false;
        boolean isfound1 = false;
        boolean isfound2 = false;

        for (int i = 0; i < imagesArray.size(); i++) {
            try {
                if (imagesArray.get(i).getTileName().equals(fromTile) && isfound1 == false) {
                    fromPosition = imagesArray.get(i).tilenumber;
                    isfound1 = true;
                }
                if (imagesArray.get(i).getTileName().equals(toTile) && isfound2 == false) {
                    toPosition = imagesArray.get(i).tilenumber;
                    isfound2 = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 25).getPath().equals("images/blank.png") && isfound2 == false && figurePresenceLeft != true) {
                    figurePresenceLeft = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 23).getPath().equals("images/blank.png") && isfound2 == false && figurePresenceRight != true) {
                    figurePresenceRight = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 24).getPath().equals("images/blank.png") && isfound2 == false && figurePresenceAhead != true) {
                    figurePresenceAhead = true;
                }


                if (fromPosition != 0 && toPosition != 0) {
                    if (toPosition == fromPosition + 24 && figurePresenceAhead == false) {
                        returnstatement = true;
                        break;
                    } else if (toPosition == fromPosition + 23 && figurePresenceRight == true) {
                        returnstatement = true;
                        break;
                    } else if (toPosition == fromPosition + 25 && figurePresenceLeft == true) {
                        returnstatement = true;
                        break;
                    } else if (toPosition == fromPosition + 25 && figurePresenceLeft == false) {
                        returnstatement = false;
                        break;
                    } else if (toPosition == fromPosition + 23 && figurePresenceRight != false) {
                        returnstatement = false;
                        break;
                    }
//                    if (toPosition == fromPosition + 24 && figurePresenceAhead != false) {
//                        returnstatement = false;
//                        break;
//                    }
                }


            } catch (IndexOutOfBoundsException e) {
                // Логирование ошибки (опционально)
                System.err.println("IndexOutOfBoundsException caught: " + e.getMessage());
                return false;
            }
        }
        return returnstatement;
    }

    //PAWN MOVEMENT LOGIC Reikland
    public boolean PawnLogicReikland(String fromTile, String toTile) {
        boolean returnstatement = false;
        int fromPosition = 0;
        int toPosition = 0;
        boolean figurePresenceLeft = false;
        boolean figurePresenceRight = false;
        boolean figurePresenceAhead = false;
        boolean isfound1 = false;
        boolean isfound2 = false;

        for (int i = 0; i < imagesArray.size(); i++) {
            try {
                int iterationStopper = 0;
                if (imagesArray.get(i).getTileName().equals(fromTile) && isfound2 == false || iterationStopper == 1 && isfound2 == false) {
                    fromPosition = imagesArray.get(i).tilenumber;
                    if (iterationStopper == 1){
                    isfound2 = true;}
                    iterationStopper ++;

                }
                if (imagesArray.get(i).getTileName().equals(toTile) && isfound1 == false) {
                    toPosition = imagesArray.get(i).tilenumber;
                    isfound1 = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 25).getPath().equals("images/blank.png") && isfound2 == false && figurePresenceLeft != true) {
                    figurePresenceLeft = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 23).getPath().equals("images/blank.png") && isfound2 == false && figurePresenceRight != true) {
                    figurePresenceRight = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 24).getPath().equals("images/blank.png") && isfound2 == false && figurePresenceAhead != true) {
                    figurePresenceAhead = true;
                }


                if (fromPosition != 0 && toPosition != 0) {
                    if (toPosition == fromPosition - 24 && figurePresenceAhead == false) {
                        returnstatement = true;
                        break;
                    } else if (toPosition == fromPosition - 23 && figurePresenceRight == true) {
                        returnstatement = true;
                        break;
                    } else if (toPosition == fromPosition - 25 && figurePresenceLeft == true) {
                        returnstatement = true;
                        break;
                    } else if (toPosition == fromPosition - 25 && figurePresenceLeft == false) {
                        returnstatement = false;
                        break;
                    } else if (toPosition == fromPosition - 23 && figurePresenceRight != false) {
                        returnstatement = false;
                        break;
                    }
                    if (toPosition == fromPosition - 24 && figurePresenceAhead != false) {
                        returnstatement = false;
                        break;
                    }
                }


            } catch (IndexOutOfBoundsException e) {
                // Логирование ошибки (опционально)
                System.err.println("IndexOutOfBoundsException caught: " + e.getMessage());
                return false;
            }
        }
        return returnstatement;
    }

    //QUEEN MOVEMENT LOGIC Reikland
    public boolean QueenLogicReikland(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (queenPosibleTurnsReikland.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland QUEEN
    public void HightlightPosibleTurnsReiklandQueen(String fromTile) {
        boolean returnstatement = false;
        int fromPosition = 0;
        boolean isfound1 = false;
        boolean isfound2 = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            int iterationStopper = 0;
            if (imagesArray.get(i).getTileName().equals(fromTile) && isfound2 == false || iterationStopper == 1 && isfound2 == false) {
                fromPosition = imagesArray.get(i).tilenumber;
                if (iterationStopper == 1) {
                    isfound2 = true;
                }
                iterationStopper++;
            }
            if (fromPosition != 0) {
                for (int j = 0; j < imagesArray.size(); j++) {
                    boolean isSkip = false;
                    if (isHorizontal(fromPosition, imagesArray.get(j).getTilenumber()) || isVertical(fromPosition, imagesArray.get(j).getTilenumber()) || isDiagonalTopLeftbottomRight(fromPosition, imagesArray.get(j).getTilenumber()) || isDiagonalTopRightbottomLeft(fromPosition, imagesArray.get(j).getTilenumber())) {
                        ArrayList<Integer> arrayOfNumberBetween = new ArrayList<>();
                        if (!imagesArray.get(j).getPath().equals(null)) {

                            if (isHorizontal(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int minposition = min(fromPosition, imagesArray.get(j).getTilenumber());
                                int maxposition = max(fromPosition, imagesArray.get(j).getTilenumber());
                                for (int m = minposition; m < maxposition - 1; m++) {
                                    arrayOfNumberBetween.add(m);
                                }
                            } else if (isVertical(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int columnFrom = fromPosition % 24;
                                int columnTo = imagesArray.get(j).getTilenumber() % 24;
                                if (columnFrom == columnTo) {
                                    int minPosition = Math.min(fromPosition - 1, imagesArray.get(j - 1).getTilenumber());
                                    int maxPosition = Math.max(fromPosition - 1, imagesArray.get(j - 1).getTilenumber());
                                    for (int m = minPosition + 24; m < maxPosition; m += 24) {
                                        arrayOfNumberBetween.add(m);
                                    }
                                }

                            } else if (isDiagonalTopLeftbottomRight(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int step = 23;
                                for (int w = minPosition + step; w != maxPosition; w += step) {
                                    arrayOfNumberBetween.add(w);
                                }


                        } else if (isDiagonalTopRightbottomLeft(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int step = 25;
                                for (int w = minPosition + step; w != maxPosition; w += step) {
                                    arrayOfNumberBetween.add(w);
                                }

                        }


                        for (int q = 0; q < arrayOfNumberBetween.size(); q++) {
                            if (!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png") && isSkip != true) {
                                isSkip = true;
                            }
                        }
                        if (isSkip == false && !imagesArray.get(j).getPath().contains("Reikland")) {
                            queenPosibleTurnsReikland.add(imagesArray.get(j).tileName);
                            imagesArray.get(j).setBorders("15px");
                        }
                    }
                }
            }
        }
    }
}

    //QUEEN MOVEMENT LOGIC Greenskin
    public boolean QueenLogicGreenskin(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (queenPosibleTurnsGreenskins.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }
    //HIGHTLIGHT POSIBLE TURNS FOR GREENSKIN QUEEN
    public void HightlightPosibleTurnsGreenskindQueen(String fromTile) {
        boolean returnstatement = false;
        int fromPosition = 0;
        boolean isfound1 = false;
        boolean isfound2 = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            int iterationStopper = 0;
            if (imagesArray.get(i).getTileName().equals(fromTile) && isfound2 == false || iterationStopper == 1 && isfound2 == false) {
                fromPosition = imagesArray.get(i).tilenumber;
                if (iterationStopper == 1) {
                    isfound2 = true;
                }
                iterationStopper++;
            }
            if (fromPosition != 0) {
                for (int j = 0; j < imagesArray.size(); j++) {
                    boolean isSkip = false;
                    if (isHorizontal(fromPosition, imagesArray.get(j).getTilenumber()) || isVertical(fromPosition, imagesArray.get(j).getTilenumber()) || isDiagonalTopLeftbottomRight(fromPosition, imagesArray.get(j).getTilenumber()) || isDiagonalTopRightbottomLeft(fromPosition, imagesArray.get(j).getTilenumber())) {
                        ArrayList<Integer> arrayOfNumberBetween = new ArrayList<>();
                        if (!imagesArray.get(j).getPath().equals(null)) {

                            if (isHorizontal(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int minposition = min(fromPosition, imagesArray.get(j).getTilenumber());
                                int maxposition = max(fromPosition, imagesArray.get(j).getTilenumber());
                                for (int m = minposition; m < maxposition - 1; m++) {
                                    arrayOfNumberBetween.add(m);
                                }
                            } else if (isVertical(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int columnFrom = fromPosition % 24;
                                int columnTo = imagesArray.get(j).getTilenumber() % 24;
                                if (columnFrom == columnTo) {
                                    int minPosition = Math.min(fromPosition - 1, imagesArray.get(j - 1).getTilenumber());
                                    int maxPosition = Math.max(fromPosition - 1, imagesArray.get(j - 1).getTilenumber());
                                    for (int m = minPosition + 24; m < maxPosition; m += 24) {
                                        arrayOfNumberBetween.add(m);
                                    }
                                }

                            } else if (isDiagonalTopLeftbottomRight(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int step = 23;
                                for (int w = minPosition + step; w != maxPosition; w += step) {
                                    arrayOfNumberBetween.add(w);
                                }


                            } else if (isDiagonalTopRightbottomLeft(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int step = 25;
                                for (int w = minPosition + step; w != maxPosition; w += step) {
                                    arrayOfNumberBetween.add(w);
                                }

                            }


                            for (int q = 0; q < arrayOfNumberBetween.size(); q++) {
                                if (!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png") && isSkip != true) {
                                    isSkip = true;
                                }
                            }
                            if (isSkip == false && !imagesArray.get(j).getPath().contains("greenskin")) {
                                queenPosibleTurnsGreenskins.add(imagesArray.get(j).tileName);
                                imagesArray.get(j).setBorders("15px");
                            }
                        }
                    }
                }
            }
        }
    }


    //HIGHTLIGHT POSIBLE TURNS FOR GREEN SKIN PAWN
    public void HightlightPosibleTurnsGreenSkin(String fromTile) {
    int fromPosition = 0;
    boolean figurePresenceLeft = false;
    boolean figurePresenceRight = false;
    boolean figurePresenceAhead = false;
    int trueI = 0;

    for (int i = 0; i < imagesArray.size(); i++) {
        try {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                fromPosition = imagesArray.get(i).tilenumber;
                trueI = i;

            }

            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 25).getPath().equals("images/blank.png") && !imagesArray.get(i + 25).getPath().contains("greenskin") && figurePresenceLeft != true) {
                figurePresenceLeft = true;
            }
            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 23).getPath().equals("images/blank.png") && !imagesArray.get(i + 23).getPath().contains("greenskin") && figurePresenceRight != true) {
                figurePresenceRight = true;
            }
            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 24).getPath().equals("images/blank.png") && !imagesArray.get(i + 24).getPath().contains("greenskin") && figurePresenceAhead != true) {
                figurePresenceAhead = true;
            }


            if (fromPosition != 0) {
                if (figurePresenceAhead != true) {
                    imagesArray.get(trueI+24).setBorders("15px");
                }
                if (figurePresenceLeft == true) {
                    imagesArray.get(trueI+25).setBorders("15px");
                    break;
                }
                if (figurePresenceRight == true) {
                    imagesArray.get(trueI+23).setBorders("15px");
                    break;
                }

            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException caught: " + e.getMessage());
        }
    }
}

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland PAWN
    public void HightlightPosibleTurnsReiklandPawn(String fromTile) {
        int fromPosition = 0;
        boolean figurePresenceLeft = false;
        boolean figurePresenceRight = false;
        boolean figurePresenceAhead = false;
        int trueI = 0;

        for (int i = 0; i < imagesArray.size(); i++) {
            try {
                if (imagesArray.get(i).getTileName().equals(fromTile)) {
                    fromPosition = imagesArray.get(i).tilenumber;
                    trueI = i;

                }

                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 25).getPath().equals("images/blank.png") && !imagesArray.get(i - 25).getPath().contains("Reikland") && figurePresenceLeft != true) {
                    figurePresenceLeft = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 23).getPath().equals("images/blank.png") && !imagesArray.get(i - 23).getPath().contains("Reikland") && figurePresenceRight != true) {
                    figurePresenceRight = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 24).getPath().equals("images/blank.png") && !imagesArray.get(i - 24).getPath().contains("Reikland") && figurePresenceAhead != true) {
                    figurePresenceAhead = true;
                }


                if (fromPosition != 0) {
                    if (figurePresenceAhead != true) {
                        imagesArray.get(trueI-24).setBorders("15px");
                    }
                    if (figurePresenceLeft == true) {
                        imagesArray.get(trueI-25).setBorders("15px");
                        break;
                    }
                    if (figurePresenceRight == true) {
                        imagesArray.get(trueI-23).setBorders("15px");
                        break;
                    }

                }
            } catch (IndexOutOfBoundsException e) {
                System.err.println("IndexOutOfBoundsException caught: " + e.getMessage());
            }
        }
    }





    // CHECK IF SECOND CLICK IS SAME FIGURE OR ALLIE GREEN SKIN
    public boolean figureCheckerSameOrAllieGreenSkin(String tile) {
    boolean isAllie = false;
    for (int i = 0; i < imagesArray.size(); i++) {
        if (imagesArray.get(i).getTileName().equals(tile) && isAllie != true) {
            if (imagesArray.get(i).getPath().contains("greenskin")) {
                isAllie = true;
                break;
            }
        }
    }
    return isAllie;
}

    // CHECK IF SECOND CLICK IS SAME FIGURE OR ALLIE Reikland
     public boolean figureCheckerSameOrAllieReikland(String tile) {
        boolean isAllie = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(tile) && isAllie != true) {
                if (imagesArray.get(i).getPath().contains("Reikland")) {
                    isAllie = true;
                    break;
                }
            }
        }
        return isAllie;
    }

    //REMOVE HIGHLIGHTS
        public void clearHighlights() {
        for (FigureImage image : imagesArray) {
            if (image.getBorders().equals("15px")){
                image.setBorders("");
            }
        }
    }



}











