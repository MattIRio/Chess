package com.Mat.TotalWarChess.Model;

import org.springframework.ui.Model;

import java.util.ArrayList;

public class ChessMoves {

    public ArrayList<FigureImage> imagesArray = new ArrayList<>();

    public void addFigure(FigureImage figureImage) {
        imagesArray.add(figureImage);
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

    //HIGHTLIGHT TILES
    public void hightlightTile(String tile) {
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(tile)) {
                imagesArray.get(i).setBorders("1.5px dashed  #1994da");
                break;

            }

        }
    }

    //UNHIGHTLIGHT TILES
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
            }
        }
        return returnstatement;
    }


    //PAWN MOVEMENT LOGIC
    public boolean PawnLogic(String fromTile, String toTile) {
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
}











