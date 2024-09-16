package com.Mat.TotalWarChess.Model;

import org.springframework.ui.Model;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ChessMoves {
    ArrayList<String> oficerPosibleTurnsReikland = new ArrayList();
    ArrayList<String> oficerPosibleTurnsGreenskins = new ArrayList();
    ArrayList<Integer> cavalryPosiblePositions = new ArrayList<>();
    public ArrayList<FigureImage> imagesArray = new ArrayList<>();
    public ArrayList<FigureImage> mortaArray = new ArrayList<>();

    public void addFigure(FigureImage figureImage) {
        imagesArray.add(figureImage);
    }
    public void addFigureMorta(FigureImage figureImage) {
        mortaArray.add(figureImage);
    }

    public void clearOficerTurnsReikland(){
        oficerPosibleTurnsReikland.clear();
    }
    public void clearOficerTurnsGreenskin(){
        oficerPosibleTurnsGreenskins.clear();
    }
    public void clearCavalryTurns(){
        cavalryPosiblePositions.clear();
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
        int rowFirstTile = ((firstTile - 1) / 24)+1;
        int rowSecondTile = ((secondTile - 1) / 24)+1;
        if(firstTile != secondTile &&(firstTile - secondTile) % 25 == 0) {
            if (Math.abs(rowFirstTile - rowSecondTile) == Math.abs((firstTile - secondTile) / 25)) {
                result = true;
            }
        }
        return result;
    }
    public boolean isDiagonalTopLeftbottomRight(int firstTile, int secondTile){
        boolean result = false;
        int rowFirstTile = ((firstTile - 1) / 24)+1;
        int rowSecondTile = ((secondTile - 1) / 24)+1;

        if(firstTile != secondTile && (firstTile - secondTile) % 23 == 0) {
            if (Math.abs(rowFirstTile - rowSecondTile) == Math.abs((firstTile - secondTile) / 23)) {
                result = true;
            }
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
    //SWAP TILES TARGET SECOND CELL

    public void swapTilesTargetSecondCell(String firstTileName, String secondTileName) {
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
                switch (path) {
                    case "images/greenskin/greenskin_black_orcs_queen.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_black_orcs_queen_target.png");
                        break;
                    case "images/greenskin/greenskin_boyz_pawn.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_boyz_pawn_target.png");
                        break;
                    case "images/greenskin/greenskin_cavalry.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_cavalry_target.png");
                        break;
                    case "images/greenskin/greenskin_chariot.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_chariot_target.png");
                        break;
                    case "images/greenskin/greenskin_cuggle.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_cuggle_target.png");
                        break;
                    case "images/greenskin/greenskin_hound.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_hound_target.png");
                        break;
                    case "images/greenskin/greenskin_ogre_head.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_ogre_head_target.png");
                        break;
                    case "images/greenskin/greenskin_shaman_wizard.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_shaman_wizard_target.png");
                        break;
                    case "images/greenskin/greenskin_troll_rook.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_troll_rook_target.png");
                        break;
                    case "images/greenskin/greenskin_warlord_king.png":
                        imagesArray.get(i).setPath("images/Target/greenskin_warlord_king_target.png");
                        break;

                    case "images/Reikland/Reikland_cavalry.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_cavalry_target.png");
                        break;
                    case "images/Reikland/Reikland_demigriph_queen.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_demigriph_queen_target.png");
                        break;
                    case "images/Reikland/Reikland_griffin.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_griffin_target.png");
                        break;
                    case "images/Reikland/Reikland_King.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_King_target.png");
                        break;
                    case "images/Reikland/Reikland_oficer.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_oficer_target.png");
                        break;
                    case "images/Reikland/Reikland_spearman_pawn.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_spearman_pawn_target.png");
                        break;
                    case "images/Reikland/Reikland_tank_back.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_tank_back_target.png");
                        break;
                    case "images/Reikland/Reikland_tank_head.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_tank_head_target.png");
                        break;
                    case "images/Reikland/Reikland_witchhunter.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_witchhunter_target.png");
                        break;
                    case "images/Reikland/Reikland_wizard.png":
                        imagesArray.get(i).setPath("images/Target/Reikland_wizard_target.png");
                        break;
                }
            }

        }
    }



    //SWAP TILES TARGET FIRSTCELL
    public void swapTilesTargetFirstCell(String firstTileName, String secondTileName) {
        String path = "";
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(firstTileName)) {
                path = imagesArray.get(i).path;
                imagesArray.get(i).setPath("images/Target/target_red.png");
                break;
            }
        }
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(secondTileName)) {
                switch (path) {
                    case "images/Target/greenskin_black_orcs_queen_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_black_orcs_queen.png");
                        break;
                    case "images/Target/greenskin_boyz_pawn_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_boyz_pawn.png");
                        break;
                    case "images/Target/greenskin_cavalry_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_cavalry.png");
                        break;
                    case "images/Target/greenskin_chariot_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_chariot.png");
                        break;
                    case "images/Target/greenskin_cuggle_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_cuggle.png");
                        break;
                    case "images/Target/greenskin_hound_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_hound.png");
                        break;
                    case "images/Target/greenskin_ogre_head_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_ogre_head.png");
                        break;
                    case "images/Target/greenskin_shaman_wizard_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_shaman_wizard.png");
                        break;
                    case "images/Target/greenskin_troll_rook_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_troll_rook.png");
                        break;
                    case "images/Target/greenskin_warlord_king_target.png":
                        imagesArray.get(i).setPath("images/greenskin/greenskin_warlord_king.png");
                        break;

                    case "images/Target/Reikland_cavalry_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_cavalry.png");
                        break;
                    case "images/Target/Reikland_demigriph_queen_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_demigriph_queen.png");
                        break;
                    case "images/Target/Reikland_griffin_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_griffin.png");
                        break;
                    case "images/Target/Reikland_King_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_King.png");
                        break;
                    case "images/Target/Reikland_oficer_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_oficer.png");
                        break;
                    case "images/Target/Reikland_spearman_pawn_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_spearman_pawn.png");
                        break;
                    case "images/Target/Reikland_tank_back_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_tank_back.png");
                        break;
                    case "images/Target/Reikland_tank_head_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_tank_head.png");
                        break;
                    case "images/Target/Reikland_witchhunter_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_witchhunter.png");
                        break;
                    case "images/Target/Reikland_wizard_target.png":
                        imagesArray.get(i).setPath("images/Reikland/Reikland_wizard.png");
                        break;
                }
            }

        }
    }

    //TILE NUMBER DETECTOR
    public int numberDetect(String firstTileName) {
        int returnstatement = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(firstTileName)) {
            returnstatement = i;
            }
        }
        return returnstatement;
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

    //HIGHTLIGHT PICKED TILES MORTAR Reikland

    public void hightlightTileMortarReikland(String tile) {
        for (int i = 0; i < mortaArray.size(); i++) {
            if (mortaArray.get(i).tileName.equals(tile)) {
                mortaArray.get(i).setPath("images/mortar_reikland_choosed.png");
                break;
            }
        }
    }

    //HIGHTLIGHT PICKED TILES MORTAR Reikland

    public void hightlightTileMortarGreenskin(String tile) {
        for (int i = 0; i < mortaArray.size(); i++) {
            if (mortaArray.get(i).tileName.equals(tile)) {
                mortaArray.get(i).setPath("images/catapulta_mortar_greenskin_choosed.png");
                break;
            }
        }
    }



    //UNHIGHTLIGHT PICKED TILES MORTAR Reikland
    public void unHightlightTileMortarReikland() {
        for (int i = 0; i < mortaArray.size(); i++) {
            if (mortaArray.get(i).getPath().equals("images/mortar_reikland_choosed.png")) {
                mortaArray.get(i).setPath("images/mortar_reikland.png");
                break;

            }

        }
    }

    //UNHIGHTLIGHT PICKED TILES MORTAR GREENSKIN
    public void unHightlightTileMortarGreenskin() {
        for (int i = 0; i < mortaArray.size(); i++) {
            if (mortaArray.get(i).getPath().equals("images/catapulta_mortar_greenskin_choosed.png")) {
                mortaArray.get(i).setPath("images/catapulta_mortar_greenskin.png");
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

    //EMPTY TILE PLAYER TURN CHECKER MORTA
    public boolean isTileEmptyMorta(String tile) {
        boolean returnstatement = false;
        boolean isfound = false;
        for (int i = 0; i < mortaArray.size(); i++) {
            if (mortaArray.get(i).tileName.equals(tile)) {
                if (mortaArray.get(i).getPath().equals("images/blank.png") && isfound == false) {
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

    //SECOND PLAYER TURN CHECKER MORTA
    public boolean isSecondPlayerTurnMorta(String tile) {
        boolean returnstatement = false;
        boolean isfound = false;
        for (int i = 0; i < mortaArray.size(); i++) {
            if (mortaArray.get(i).tileName.equals(tile)) {
                if (!mortaArray.get(i).getPath().contains("greenskin") || mortaArray.get(i).getPath().contains("blank") && isfound == false) {
                    returnstatement = true;
                    isfound = true;
                    break;
                }
            }
        }

        return returnstatement;
    }


    //FIRST PLAYER TURN CHECKER MORTA
    public boolean isFirstPlayerTurnMorta(String tile) {
        boolean returnstatement = false;
        boolean isfound = false;
        for (int i = 0; i < mortaArray.size(); i++) {
            if (mortaArray.get(i).tileName.equals(tile)) {
                if (!mortaArray.get(i).getPath().contains("Reikland") || mortaArray.get(i).getPath().contains("blank") && isfound == false) {
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

        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(tile) && returnstatement.equals("")) {
                if (imagesArray.get(i).getPath().contains("pawn")) {
                    returnstatement = "pawn";
                    break;
                }
                if (imagesArray.get(i).getPath().contains("king")) {
                    returnstatement = "king";
                    break;
                }
                if (imagesArray.get(i).getPath().toLowerCase().contains("wizard")) {
                    returnstatement = "wizard";
                    break;
                }
                if (imagesArray.get(i).getPath().contains("cavalry")) {
                    returnstatement = "cavalry";
                    break;
                }
                if (imagesArray.get(i).getPath().contains("queen")) {
                    returnstatement = "queen";
                    break;
                }
                if (imagesArray.get(i).getPath().contains("mortar")) {
                    returnstatement = "mortar";
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
        int savedI = 0;
        boolean isfound1 = false;
        boolean isfound2 = false;

        for (int i = 0; i < imagesArray.size(); i++) {
            try {
                if (imagesArray.get(i).getTileName().equals(fromTile) && isfound1 == false) {
                    fromPosition = imagesArray.get(i).tilenumber;
                    isfound1 = true;
                    savedI = i;

                }
                if (imagesArray.get(i).getTileName().equals(toTile) && isfound2 == false) {
                    toPosition = imagesArray.get(i).tilenumber;
                    isfound2 = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 24).getPath().equals("images/blank.png") && !imagesArray.get(i + 24).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 24).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 24).getPath().contains("greenskin") && figurePresenceAhead != true) {
                    figurePresenceLeft = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 23).getPath().equals("images/blank.png") && !imagesArray.get(i + 23).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 23).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 23).getPath().contains("greenskin") && figurePresenceRight != true) {
                    figurePresenceRight = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 25).getPath().equals("images/blank.png") && !imagesArray.get(i + 25).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 25).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 25).getPath().contains("greenskin") && figurePresenceLeft != true) {
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
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 25).getPath().equals("images/blank.png") && !imagesArray.get(i - 25).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i - 25).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i - 25).getPath().contains("Reikland") && figurePresenceLeft != true){
                    figurePresenceLeft = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 23).getPath().equals("images/blank.png") && !imagesArray.get(i - 23).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i - 23).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 23).getPath().contains("Reikland") && figurePresenceRight != true) {
                    figurePresenceRight = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 24).getPath().equals("images/blank.png") && !imagesArray.get(i - 24).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i - 24).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i - 24).getPath().contains("Reikland") && figurePresenceAhead != true) {
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

        if (oficerPosibleTurnsReikland.contains(toTile)) {
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
                                if (columnFrom == columnTo && fromPosition > 0 && fromPosition < 240) {
                                    if (j == 0){
                                        j = 1;
                                    }
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
                            if ((!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png") && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().contains("target")) && isSkip != true) {
                                isSkip = true;
                            }
                        }
                        if (isSkip == false && !imagesArray.get(j).getPath().contains("Reikland")) {
                            oficerPosibleTurnsReikland.add(imagesArray.get(j).tileName);
                            imagesArray.get(j).setBorders("15px");
                        }
                    }
                }
            }
        }
    }
}

    //MORTAR LOGIC Reikland
    public boolean MortarReiklandLogic(String firstTileName) {
        boolean returnstatement = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(firstTileName)) {
                if (imagesArray.get(i).getTilenumber() < 169 && imagesArray.get(i).getTilenumber() > 72){
                    returnstatement = true;

                }
            }
        }
        return returnstatement;
    }



    //MORTAR MOVE Reikland
    public void MortarReiklandMOVE(String targetTile) {
        for (int i = 72; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(targetTile)){
                if (!imagesArray.get(i).getPath().contains("reikland")){
                    switch (imagesArray.get(i).getPath()){
                        case "images/greenskin/greenskin_black_orcs_queen.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_black_orcs_queen_target.png");
                            break;
                        case "images/greenskin/greenskin_boyz_pawn.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_boyz_pawn_target.png");
                            break;
                        case "images/greenskin/greenskin_cavalry.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_cavalry_target.png");
                            break;
                        case "images/greenskin/greenskin_chariot.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_chariot_target.png");
                            break;
                        case "images/greenskin/greenskin_cuggle.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_cuggle_target.png");
                            break;
                        case "images/greenskin/greenskin_hound.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_hound_target.png");
                            break;
                        case "images/greenskin/greenskin_ogre_head.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_ogre_head_target.png");
                            break;
                        case "images/greenskin/greenskin_shaman_wizard.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_shaman_wizard_target.png");
                            break;
                        case "images/greenskin/greenskin_troll_rook.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_troll_rook_target.png");
                            break;
                        case "images/greenskin/greenskin_warlord_king.png":
                            imagesArray.get(i).setPath("images/Target/greenskin_warlord_king_target.png");
                            break;
                        case "images/blank.png":
                            imagesArray.get(i).setPath("images/Target/target_red.png");
                            break;
                    }
                }
            }
        }
    }

    //MORTAR MOVE GREENSKIN
    public void MortarGreenskinMOVE(String targetTile) {
        for (int i = 72; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(targetTile)){
                if (!imagesArray.get(i).getPath().contains("greenskin")){
                    switch (imagesArray.get(i).getPath()){
                        case "images/Reikland/Reikland_cavalry.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_cavalry_target.png");
                            break;
                        case "images/Reikland/Reikland_demigriph_queen.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_demigriph_queen_target.png");
                            break;
                        case "images/Reikland/Reikland_griffin.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_griffin_target.png");
                            break;
                        case "images/Reikland/Reikland_King.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_King_target.png");
                            break;
                        case "images/Reikland/Reikland_oficer.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_oficer_target.png");
                            break;
                        case "images/Reikland/Reikland_spearman_pawn.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_spearman_pawn_target.png");
                            break;
                        case "images/Reikland/Reikland_tank_back.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_tank_back_target.png");
                            break;
                        case "images/Reikland/Reikland_tank_head.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_tank_head_target.png");
                            break;
                        case "images/Reikland/Reikland_witchhunter.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_witchhunter_target.png");
                            break;
                        case "images/Reikland/Reikland_wizard.png":
                            imagesArray.get(i).setPath("images/Target/Reikland_wizard_target.png");
                            break;
                        case "images/blank.png":
                            imagesArray.get(i).setPath("images/Target/target_black.png");
                            break;
                    }
                }
            }
        }
    }

    // MORTAR SHOOTING
    public void mortarShooting(String targetTile) {
    for (int i = 0; i < imagesArray.size(); i++) {
        if (imagesArray.get(i).tileName.equals(targetTile)) {
            imagesArray.get(i).setPath("images/blank.png");
            break;
        }
    }
}

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland Wizard
    public void HightlightPosibleTurnsReiklandWizard(String fromTile) {
        boolean returnstatement = false;
        int fromPosition = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile) && fromPosition == 0) {
                fromPosition = imagesArray.get(i).tilenumber;
            }
            if (fromPosition != 0) {
                for (int j = 0; j < imagesArray.size(); j++) {
                    boolean isSkip = false;
                    if (isDiagonalTopLeftbottomRight(fromPosition, imagesArray.get(j).getTilenumber()) || isDiagonalTopRightbottomLeft(fromPosition, imagesArray.get(j).getTilenumber())) {
                        ArrayList<Integer> arrayOfNumberBetween = new ArrayList<>();
                            
                            if (isDiagonalTopLeftbottomRight(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int step = 23;
                                for (int w = minPosition + step; w != maxPosition; w += step) {
                                    System.out.println(w);
                                    arrayOfNumberBetween.add(w);
                                }


                            } else if (isDiagonalTopRightbottomLeft(fromPosition, imagesArray.get(j).getTilenumber())) {
                                int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int step = 25;
                                for (int w = minPosition + step; w != maxPosition; w += step) {
                                    System.out.println("b");
                                    arrayOfNumberBetween.add(w);
                                }

                            }


                            for (int q = 0; q < arrayOfNumberBetween.size(); q++) {
                                if (!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png") && isSkip != true) {
                                    isSkip = true;
                                }
                            }
                            if (isSkip == false && !imagesArray.get(j).getPath().contains("Reikland")) {
                                oficerPosibleTurnsReikland.add(imagesArray.get(j).tileName);
                                imagesArray.get(j).setBorders("15px");
                            }
                        }
                    }
                }
            }
        }

    //WIZARD MOVEMENT LOGIC REIKLAND
    public boolean WizardLogicReikland(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsReikland.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }

    //QUEEN MOVEMENT LOGIC Greenskin
     public boolean QueenLogicGreenskin(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsGreenskins.contains(toTile)) {
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
                                if (!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png")  && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/Target/target_red.png") && isSkip != true) {
                                    isSkip = true;
                                }
                            }
                            if (isSkip == false && !imagesArray.get(j).getPath().contains("greenskin")) {
                                oficerPosibleTurnsGreenskins.add(imagesArray.get(j).tileName);
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

            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 25).getPath().equals("images/blank.png") && !imagesArray.get(i + 25).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 25).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 25).getPath().contains("greenskin") && figurePresenceLeft != true) {
                figurePresenceLeft = true;
            }
            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 23).getPath().equals("images/blank.png") && !imagesArray.get(i + 23).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 23).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 23).getPath().contains("greenskin") && figurePresenceRight != true) {
                figurePresenceRight = true;
            }
            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 24).getPath().equals("images/blank.png") && !imagesArray.get(i + 24).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 24).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 24).getPath().contains("greenskin") && figurePresenceAhead != true) {
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

                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 25).getPath().equals("images/blank.png") && !imagesArray.get(i - 25).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i - 25).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i - 25).getPath().contains("Reikland") && figurePresenceLeft != true){
                    figurePresenceLeft = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 23).getPath().equals("images/blank.png") && !imagesArray.get(i - 23).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i - 23).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 23).getPath().contains("Reikland") && figurePresenceRight != true) {
                    figurePresenceRight = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i - 24).getPath().equals("images/blank.png") && !imagesArray.get(i - 24).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i - 24).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i - 24).getPath().contains("Reikland") && figurePresenceAhead != true) {
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

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland Wizard
    public void HightlightPosibleTurnsGreenskinWizard(String fromTile) {
        boolean returnstatement = false;
        int fromPosition = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile) && fromPosition == 0) {
                fromPosition = imagesArray.get(i).tilenumber;
            }
            if (fromPosition != 0) {
                for (int j = 0; j < imagesArray.size(); j++) {
                    boolean isSkip = false;
                    if (isDiagonalTopLeftbottomRight(fromPosition, imagesArray.get(j).getTilenumber()) || isDiagonalTopRightbottomLeft(fromPosition, imagesArray.get(j).getTilenumber())) {
                        ArrayList<Integer> arrayOfNumberBetween = new ArrayList<>();

                        if (isDiagonalTopLeftbottomRight(fromPosition, imagesArray.get(j).getTilenumber())) {
                            int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                            int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                            int step = 23;
                            for (int w = minPosition + step; w != maxPosition; w += step) {
                                System.out.println(w);
                                arrayOfNumberBetween.add(w);
                            }


                        } else if (isDiagonalTopRightbottomLeft(fromPosition, imagesArray.get(j).getTilenumber())) {
                            int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                            int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                            int step = 25;
                            for (int w = minPosition + step; w != maxPosition; w += step) {
                                System.out.println("b");
                                arrayOfNumberBetween.add(w);
                            }

                        }


                        for (int q = 0; q < arrayOfNumberBetween.size(); q++) {
                            if (!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png") && isSkip != true) {
                                isSkip = true;
                            }
                        }
                        if (isSkip == false && !imagesArray.get(j).getPath().toLowerCase().contains("greenskin")) {
                            oficerPosibleTurnsGreenskins.add(imagesArray.get(j).tileName);
                            imagesArray.get(j).setBorders("15px");
                        }
                    }
                }
            }
        }
    }

    //WIZARD MOVEMENT LOGIC GREENSKIN
    public boolean WizardLogicGreenskin(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsGreenskins.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland CAVALRY
    public void HightlightPosibleTurnsReiklandCavalry(String fromTile) {
        boolean returnstatement = false;

        int fromPosition = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile) && fromPosition == 0) {
                fromPosition = imagesArray.get(i).tilenumber;
            }
        }
        if (fromPosition != 0) {
            fromPosition--;
            int n1 = (fromPosition-22) / 24;
            int n2 = fromPosition / 24 -1;
            if ((fromPosition-22) / 24 == fromPosition / 24 -1){
                cavalryPosiblePositions.add(fromPosition - 22);
            }
            if ((fromPosition-26) / 24 == fromPosition / 24 -1){
                cavalryPosiblePositions.add(fromPosition - 26);
            }
            if ((fromPosition+22) / 24 == fromPosition / 24 +1){
                cavalryPosiblePositions.add(fromPosition + 22);
            }
            if ((fromPosition+26) / 24 == fromPosition / 24 +1){
                cavalryPosiblePositions.add(fromPosition + 26);
            }
            if ((fromPosition+49) / 24 == fromPosition / 24 +2){
                cavalryPosiblePositions.add(fromPosition + 49);
            }
            if ((fromPosition+47) / 24 == fromPosition / 24 +2){
                cavalryPosiblePositions.add(fromPosition + 47);
            }
            if ((fromPosition-49) / 24 == fromPosition / 24 -2){
                cavalryPosiblePositions.add(fromPosition - 49);
            }
            if ((fromPosition-47) / 24 == fromPosition / 24 -2){
                cavalryPosiblePositions.add(fromPosition - 47);
            }
        }
        for (Integer i : cavalryPosiblePositions) {
            if (i < 240 && i > 0) {
                if (!imagesArray.get(i).getPath().toLowerCase().contains("reikland")) {
                    imagesArray.get(i).setBorders("15px");
                    oficerPosibleTurnsReikland.add(imagesArray.get(i).tileName);
                }
            }
        }
    }

    //CAVALRY MOVEMENT LOGIC REIKLAND
    public boolean CavalryLogicReikland(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsReikland.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }

    //HIGHTLIGHT POSIBLE TURNS FOR GREENSKIN CAVALRY
    public void HightlightPosibleTurnsGreenskinCavalry(String fromTile) {
        boolean returnstatement = false;

        int fromPosition = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile) && fromPosition == 0) {
                fromPosition = imagesArray.get(i).tilenumber;
            }
        }
        if (fromPosition != 0) {
            fromPosition--;
            int n1 = (fromPosition-22) / 24;
            int n2 = fromPosition / 24 -1;
            if ((fromPosition-22) / 24 == fromPosition / 24 -1){
                cavalryPosiblePositions.add(fromPosition - 22);
            }
            if ((fromPosition-26) / 24 == fromPosition / 24 -1){
                cavalryPosiblePositions.add(fromPosition - 26);
            }
            if ((fromPosition+22) / 24 == fromPosition / 24 +1){
                cavalryPosiblePositions.add(fromPosition + 22);
            }
            if ((fromPosition+26) / 24 == fromPosition / 24 +1){
                cavalryPosiblePositions.add(fromPosition + 26);
            }
            if ((fromPosition+49) / 24 == fromPosition / 24 +2){
                cavalryPosiblePositions.add(fromPosition + 49);
            }
            if ((fromPosition+47) / 24 == fromPosition / 24 +2){
                cavalryPosiblePositions.add(fromPosition + 47);
            }
            if ((fromPosition-49) / 24 == fromPosition / 24 -2){
                cavalryPosiblePositions.add(fromPosition - 49);
            }
            if ((fromPosition-47) / 24 == fromPosition / 24 -2){
                cavalryPosiblePositions.add(fromPosition - 47);
            }
        }
        for (Integer i : cavalryPosiblePositions) {
            if (i < 240 && i > 0) {
                if (!imagesArray.get(i).getPath().toLowerCase().contains("greenskin")) {
                    imagesArray.get(i).setBorders("15px");
                    oficerPosibleTurnsGreenskins.add(imagesArray.get(i).tileName);
                }
            }
        }
    }

    //CAVALRY MOVEMENT LOGIC REIKLAND
    public boolean CavalryLogicGreenskin(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsGreenskins.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
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
        if (tile.contains("cell00")){
            isAllie = true;
        }
    }
    return isAllie;
}

    // CHECK IF SECOND CLICK IS SAME FIGURE OR ALLIE Reikland
    public boolean figureCheckerSameOrAllieReikland(String tile) {
        boolean isAllie = false;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(tile) && isAllie != true) {
                if (imagesArray.get(i).getPath().toLowerCase().contains("reikland")) {
                    isAllie = true;
                    break;
                }
            }
            if (tile.contains("cell00")){
                isAllie = true;
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











