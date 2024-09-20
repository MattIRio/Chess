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
        if(((firstTile-1) / 24) == ((secondTile -1) / 24)){
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
                if (imagesArray.get(i).getPath().contains("tank_head")){
                    imagesArray.get(i+24).setPath("images/blank.png");
                }
                if (imagesArray.get(i).getPath().contains("tank_back")){
                    imagesArray.get(i-24).setPath("images/blank.png");
                }
                imagesArray.get(i).setPath(path);
                break;
            }
        }

    }

    //REMAINING PIECES COUNTER
    public String RemeiningPiecesCounter() {
        int ReiklandCounter = 0;
        int GreenSkinCounter = 0;
        String returnstatement = "";
        boolean reiklandKingAlive = false;
        boolean greenskinKingAlive = false;
        ArrayList <String> kings = new ArrayList<>();
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).path.contains("Reikland")) {
                ReiklandCounter++;
                kings.add(imagesArray.get(i).getPath());
            }
            if (imagesArray.get(i).path.contains("greenskin")) {
                GreenSkinCounter++;
                kings.add(imagesArray.get(i).getPath());
            }
        }
        for (String i : kings){
            if (i.contains("Reikland_king")){
                reiklandKingAlive = true;
            }
            if (i.contains("greenskin_warlord_king")){
                greenskinKingAlive = true;
            }

        }

        if (ReiklandCounter < 14){  //14
            returnstatement = "Reikland";
        }
        if (ReiklandCounter < 22 && reiklandKingAlive == false){
            returnstatement = "Reikland";
        }

        if (GreenSkinCounter < 14){
            returnstatement = "Greenskin";
        }
        if (GreenSkinCounter < 22 && greenskinKingAlive == false){
            returnstatement = "Greenskin";
        }

        return returnstatement;
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

    //TILE NUMBER DETECTOR
    public String numberToName(int firstTileNumber) {
        String returnstatement = "";
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTilenumber() == firstTileNumber) {
                returnstatement = imagesArray.get(i).getTileName();
            }
        }
        return returnstatement;
    }


    //HIGHTLIGHT PICKED TILES
    public void hightlightTile(String tile) {
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(tile)) {
                imagesArray.get(i).setBorders("7px");
                break;

            }

        }
    }

    //HIGHTLIGHT PICKED TILES TANK
    public void hightlightTileTank(String tile) {
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).tileName.equals(tile)) {
                imagesArray.get(i+24).setBorders("7px");
                imagesArray.get(i).setBorders("7px");
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
    public void unHightlightTile(String tile) {
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(tile)) {
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

    //UNHIGHTLIGHT PICKED TILES TANK
    public void unHightlightTileTank(String tile) {
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(tile)) {
                imagesArray.get(i).setBorders("");
                imagesArray.get(i+24).setBorders("");
                break;

            }

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
        if (imagesArray.get(i).getPath().contains("king")) {
            returnstatement = "king";
            break;
        }
        if (imagesArray.get(i).getPath().contains("rook")) {
            returnstatement = "rook";
            break;
        }
        if (imagesArray.get(i).getPath().contains("oficer")) {
            returnstatement = "oficer";
            break;
        }
        if (imagesArray.get(i).getPath().contains("sprinter")) {
            returnstatement = "sprinter";
            break;
        }
        if (imagesArray.get(i).getPath().contains("tank_head")) {
            returnstatement = "tank_head";
            break;
        }
        if (imagesArray.get(i).getPath().contains("tank_back")) {
            returnstatement = "tank_back";
            break;
        }
    }
}
 return returnstatement;
}

    //OFICER MOVEMENT LOGIC Reikland
    public boolean OficerLogicReikland(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsReikland.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }

    //TRANSFORMING TANK BACK TO TANK HEAD
    public String tankBackInHead (String fromTile){
        String returnstatement = "";
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                returnstatement = imagesArray.get(i-24).getTileName();
                break;

            }
        }
        return returnstatement;
    }

    //TANK MOVE LOGIC
    public void TankReiklandMOVE (String fromTile, String toTile){
        int firstCell = 0;
        int secondCell = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                firstCell =i;
                if (secondCell != 0) {
                    break;
                }
            }
            if (imagesArray.get(i).getTileName().equals(toTile)) {
                secondCell =i;
                if (firstCell != 0) {
                    break;
                }
            }
        }
        if (imagesArray.get(secondCell).getPath().contains("target")){
            imagesArray.get(firstCell).setPath("images/Target/Reikland_tank_head_target.png");

        }
        if (firstCell - secondCell < 50 && secondCell != 0){
            imagesArray.get(secondCell).setPath("images/blank.png");
        }
        if ((firstCell == secondCell + 25 || secondCell == firstCell + 1) && secondCell != 0 && firstCell != 0 && !imagesArray.get(firstCell+1).getPath().contains("Reikland") && !imagesArray.get(firstCell+25).getPath().contains("Reikland")){
            imagesArray.get(firstCell+1).setPath(imagesArray.get(firstCell).getPath());
            imagesArray.get(firstCell+25).setPath(imagesArray.get(firstCell+24).getPath());
            imagesArray.get(firstCell).setPath("images/blank.png");
            imagesArray.get(firstCell+24).setPath("images/blank.png");
        }
        else if ((secondCell == firstCell - 1 || firstCell == secondCell + 23) && secondCell != 0 && firstCell != 0 && !imagesArray.get(firstCell-1).getPath().contains("Reikland") && !imagesArray.get(firstCell+23).getPath().contains("Reikland")){
            imagesArray.get(firstCell-1).setPath(imagesArray.get(firstCell).getPath());
            imagesArray.get(firstCell+23).setPath(imagesArray.get(firstCell+24).getPath());
            imagesArray.get(firstCell).setPath("images/blank.png");
            imagesArray.get(firstCell+24).setPath("images/blank.png");
        }

        else if (firstCell - secondCell < 26 && firstCell - secondCell > 0 && secondCell != 0 && firstCell != 0){
            imagesArray.get(secondCell).setPath(imagesArray.get(firstCell).getPath());
            imagesArray.get(secondCell+24).setPath(imagesArray.get(firstCell+24).getPath());
            imagesArray.get(firstCell+24).setPath("images/blank.png");
        }
        else if (firstCell + 23 < secondCell && secondCell != 0 && firstCell != 0){
            imagesArray.get(secondCell).setPath(imagesArray.get(firstCell+24).getPath());
            imagesArray.get(firstCell+24).setPath(imagesArray.get(firstCell).getPath());
            imagesArray.get(firstCell).setPath("images/blank.png");
        }





    }

    //PAWN MOVEMENT LOGIC GREEN SKIN
    public boolean PawnLogicGreenSkin(String fromTile, String toTile) {
        boolean returnstatement = false;
        int fromPosition = 0;
        int toPosition = 0;
        boolean figurePresenceLeft = false;
        boolean figurePresenceRight = false;
        boolean figurePresenceAhead = false;
        boolean figurePresenceAhead2 = false;
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
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 25).getPath().equals("images/blank.png") && !imagesArray.get(i + 25).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 25).getPath().equals("images/Target/target_red.png")  && figurePresenceLeft != true) {
                    figurePresenceLeft = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 23).getPath().equals("images/blank.png") && !imagesArray.get(i + 23).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 23).getPath().equals("images/Target/target_red.png")  && figurePresenceRight != true) {
                    figurePresenceRight = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 24).getPath().equals("images/blank.png") && !imagesArray.get(i + 24).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 24).getPath().equals("images/Target/target_red.png") && figurePresenceAhead != true) {
                    figurePresenceAhead = true;
                }
                if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 48).getPath().equals("images/blank.png") && !imagesArray.get(i + 48).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 48).getPath().equals("images/Target/target_red.png") && (imagesArray.get(i + 48).getPath().contains("greenskin")  || imagesArray.get(i + 48).getPath().contains("Reikland")) && figurePresenceAhead2 != true) {
                    figurePresenceAhead2 = true;
                }



                if (fromPosition != 0 && toPosition != 0) {
                    if (toPosition == fromPosition + 24 && figurePresenceAhead == false) {
                        returnstatement = true;
                        break;
                    } if (toPosition == fromPosition + 48 && figurePresenceAhead2 == false && (imagesArray.get(fromPosition).getTilenumber()-1) / 24 == 1 ) {
                        returnstatement = true;

                    }

                    else if (toPosition == fromPosition + 23 && figurePresenceRight == true) {
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
    boolean figurePresenceAhead2 = false;
    boolean isfound1 = false;
    boolean isfound2 = false;
    int trueI = 0;

    for (int i = 0; i < imagesArray.size(); i++) {

        if (imagesArray.get(i).getTileName().equals(fromTile) && isfound2 == false) {
            fromPosition = imagesArray.get(i).tilenumber;
            isfound2 = true;
            trueI = i;
            if (toPosition != 0) {
                break;
            }
        }

        if (imagesArray.get(i).getTileName().equals(toTile)) {
            toPosition = imagesArray.get(i).tilenumber;
            isfound1 = true;
            if (fromPosition != 0) {
                break;
            }
        }
    }


            if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 25).getPath().equals("images/blank.png") && !imagesArray.get(trueI - 25).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI - 25).getPath().equals("images/Target/target_red.png") && !imagesArray.get(trueI - 25).getPath().contains("Reikland") && figurePresenceLeft != true){
                figurePresenceLeft = true;
            }
            if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 23).getPath().equals("images/blank.png") && !imagesArray.get(trueI - 23).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI - 23).getPath().equals("images/Target/target_red.png") && !imagesArray.get(trueI + 23).getPath().contains("Reikland") && figurePresenceRight != true) {
                figurePresenceRight = true;
            }
            if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 24).getPath().equals("images/blank.png") && !imagesArray.get(trueI - 24).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI - 24).getPath().equals("images/Target/target_red.png") && !imagesArray.get(trueI - 24).getPath().contains("Reikland") && figurePresenceAhead != true) {
                figurePresenceAhead = true;
            }
            if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 48).getPath().equals("images/blank.png") && !imagesArray.get(trueI - 48).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI - 48).getPath().equals("images/Target/target_red.png") && (imagesArray.get(trueI - 48).getPath().contains("Reikland")  || imagesArray.get(trueI - 48).getPath().contains("greenskin")) && figurePresenceAhead2 != true) {
                figurePresenceAhead2 = true;
            }


            if (fromPosition != 0 && toPosition != 0) {
                if (toPosition == fromPosition - 24 && figurePresenceAhead == false) {
                    returnstatement = true;

                }
                if (toPosition == fromPosition - 48 && figurePresenceAhead2 == false && (imagesArray.get(trueI).getTilenumber()-1) / 24 == 8 ) {
                    returnstatement = true;

                }
                else if (toPosition == fromPosition - 23 && figurePresenceRight == true) {
                    returnstatement = true;

                } else if (toPosition == fromPosition - 25 && figurePresenceLeft == true) {
                    returnstatement = true;

                } else if (toPosition == fromPosition - 25 && figurePresenceLeft == false) {
                    returnstatement = false;

                } else if (toPosition == fromPosition - 23 && figurePresenceRight != false) {
                    returnstatement = false;

                }
                if (toPosition == fromPosition - 24 && figurePresenceAhead != false) {
                    returnstatement = false;

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

    //Tank MOVEMENT LOGIC Reikland
    public boolean TankLogicReikland(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsReikland.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }

    //OFICER MOVEMENT LOGIC GREENSKIN
    public boolean OficerLogicGreenskin(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsGreenskins.contains(toTile)) {
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
                                if (columnFrom == columnTo && fromPosition > -1 && fromPosition < 241) {
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
                                if (fromPosition == 0){
                                    fromPosition = 1;
                                }
                                int minPosition = Math.min(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int maxPosition = Math.max(fromPosition -1, imagesArray.get(j-1).getTilenumber());
                                int step = 25;
                                for (int w = minPosition + step; w != maxPosition; w += step) {
                                    arrayOfNumberBetween.add(w);
                                }

                        }


                        for (int q = 0; q < arrayOfNumberBetween.size(); q++) {
                            if ((!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png") && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().contains("target_red")) && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().contains("target_black") && isSkip != true) {
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

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland ROOK
    public void HightlightPosibleTurnsReiklandRook(String fromTile) {
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
                    if (isHorizontal(fromPosition, imagesArray.get(j).getTilenumber()) || isVertical(fromPosition, imagesArray.get(j).getTilenumber())) {
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
                                if (columnFrom == columnTo && fromPosition > -1 && fromPosition < 241) {
                                    if (j == 0){
                                        j = 1;
                                    }
                                    int minPosition = Math.min(fromPosition - 1, imagesArray.get(j - 1).getTilenumber());
                                    int maxPosition = Math.max(fromPosition - 1, imagesArray.get(j - 1).getTilenumber());
                                    for (int m = minPosition + 24; m < maxPosition; m += 24) {
                                        arrayOfNumberBetween.add(m);
                                    }
                                }

                            }


                            for (int q = 0; q < arrayOfNumberBetween.size(); q++) {
                                if ((!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png")
                                        && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().contains("target_black")
                                        && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().contains("target_red"))
                                        && !isSkip) {
                                    isSkip = true;
                                }
                            }
                            if (isSkip == false && !imagesArray.get(j).getPath().toLowerCase().contains("reikland")) {
                                oficerPosibleTurnsReikland.add(imagesArray.get(j).tileName);
                                imagesArray.get(j).setBorders("15px");
                            }
                        }
                    }
                }
            }
        }
    }

    //ROOK MOVEMENT LOGIC REIKLAND
    public boolean RookLogicREIKLAND(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsReikland.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland TANK
    public void HightlightPosibleTurnsReiklandTANK(String fromTile) {
        int fromPosition = 0;
        boolean figurePresenceAhead3 = false;
        boolean figurePresenceRightAhead3 = false;
        boolean figurePresenceAhead = false;
        boolean figurePresenceLeft = false;
        boolean figurePresenceRight = false;
        boolean figurePresenceBehind = false;
        boolean figurePresenceLeftAhead3 = false;



        int trueI = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                fromPosition = imagesArray.get(i).tilenumber;
                trueI = i;
                break;

            }
        }


        if (trueI != 0) {


            if ((trueI - 24) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 24).getPath().contains("Reikland") && figurePresenceAhead != true  && isTheSameRow(trueI,trueI-24) == -1) {
                    figurePresenceAhead = true;
                }
            }
            if ((trueI - 1) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 1).getPath().contains("Reikland") && figurePresenceLeft != true  && isTheSameRow(trueI,trueI-1) == 0) {
                    figurePresenceLeft = true;
                }
            }
            if ((trueI + 1) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 1).getPath().contains("Reikland") && figurePresenceRight != true  && isTheSameRow(trueI,trueI+1) == 0) {
                    figurePresenceRight = true;
                }
            }

            if ((trueI + 48) < 241) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 48).getPath().contains("Reikland") && figurePresenceBehind != true  && isTheSameRow(trueI,trueI+48) == 1) {
                    figurePresenceBehind = true;
                }
            }

            if ((trueI - 48) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 48).getPath().contains("greenskin") && figurePresenceAhead3 != true  && isTheSameRow(trueI ,trueI-48) == -1) {
                    figurePresenceAhead3 = true;
                }
            }
            if ((trueI - 47) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 47).getPath().contains("greenskin") && figurePresenceRightAhead3 != true  && isTheSameRow(trueI,trueI-47) == -1) {
                    figurePresenceRightAhead3 = true;
                }
            }
            if ((trueI - 49) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 49).getPath().contains("greenskin") && figurePresenceLeftAhead3 != true  && isTheSameRow(trueI,trueI-49) == -1) {
                    figurePresenceLeftAhead3 = true;
                }
            }



        }


        if (fromPosition != 0) {
            if (figurePresenceAhead == true) {
                imagesArray.get(trueI-24).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-24).tileName);
            }

            if (figurePresenceAhead3 == true) {
                imagesArray.get(trueI-48).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-48).tileName);
            }
            if (figurePresenceRightAhead3 == true) {
                imagesArray.get(trueI-47).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-47).tileName);
            }
            if (figurePresenceLeftAhead3 == true) {
                imagesArray.get(trueI-49).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-49).tileName);
            }


            if (figurePresenceLeft == true && !imagesArray.get(trueI-1).getPath().contains("Reikland") && !imagesArray.get(trueI+23).getPath().contains("Reikland") ) {
                imagesArray.get(trueI-1).setBorders("15px");
                imagesArray.get(trueI+23).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-1).tileName);
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+23).tileName);
            }
            if (figurePresenceRight == true && !imagesArray.get(trueI+1).getPath().contains("Reikland") && !imagesArray.get(trueI+25).getPath().contains("Reikland")) {
                imagesArray.get(trueI+1).setBorders("15px");
                imagesArray.get(trueI+25).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+25).tileName);
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+1).tileName);

            }
            if (figurePresenceBehind == true) {
                imagesArray.get(trueI+48).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+48).tileName);

            }



        }
    }

    //HIGHTLIGHT POSIBLE TURNS FOR GREENSKIN ROOK
    public void HightlightPosibleTurnsGreenskinRook(String fromTile) {
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
                    if (isHorizontal(fromPosition, imagesArray.get(j).getTilenumber()) || isVertical(fromPosition, imagesArray.get(j).getTilenumber())) {
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
                                if (columnFrom == columnTo && fromPosition > -1 && fromPosition < 241) {
                                    if (j == 0){
                                        j = 1;
                                    }
                                    int minPosition = Math.min(fromPosition - 1, imagesArray.get(j - 1).getTilenumber());
                                    int maxPosition = Math.max(fromPosition - 1, imagesArray.get(j - 1).getTilenumber());
                                    for (int m = minPosition + 24; m < maxPosition; m += 24) {
                                        arrayOfNumberBetween.add(m);
                                    }
                                }

                            }


                            for (int q = 0; q < arrayOfNumberBetween.size(); q++) {
                                if ((!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png")
                                        && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().contains("target_black")
                                        && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().contains("target_red"))
                                        && !isSkip) {
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
    }

    //ROOK MOVEMENT LOGIC GREENSKIN
    public boolean RookLogicGreenskin(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsGreenskins.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
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
                        case "images/Target/target_black.png":
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
                        case "images/Target/target_red.png":
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
            if (imagesArray.get(i).getPath().contains("tank_head")){
                imagesArray.get(i+24).setPath("images/blank.png");
            }
            if (imagesArray.get(i).getPath().contains("tank_back")){
                imagesArray.get(i-24).setPath("images/blank.png");
            }
            imagesArray.get(i).setPath("images/blank.png");
            if (imagesArray.get(i).getPath().contains("tank_back")){
                imagesArray.get(i-24).setPath("images/blank.png");
            }
            if (imagesArray.get(i).getPath().contains("tank_head")){
                imagesArray.get(i+24).setPath("images/blank.png");
            }
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
                                if (!imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/blank.png")  && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().equals("images/Target/target_red.png") && !imagesArray.get(arrayOfNumberBetween.get(q)).getPath().contains("target_black") && isSkip != true) {
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
    boolean figurePresenceAhead2 = false;
    int trueI = 0;

    for (int i = 0; i < imagesArray.size(); i++) {
        try {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                fromPosition = imagesArray.get(i).tilenumber;
                trueI = i;

            }
            if (trueI + 25 < 241) {
            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 25).getPath().equals("images/blank.png") && !imagesArray.get(i + 25).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 25).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 25).getPath().contains("greenskin") && figurePresenceLeft != true) {
                figurePresenceLeft = true;
            }
            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 23).getPath().equals("images/blank.png") && !imagesArray.get(i + 23).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 23).getPath().equals("images/Target/target_red.png") && !imagesArray.get(i + 23).getPath().contains("greenskin") && figurePresenceRight != true) {
                figurePresenceRight = true;
            }
            if (imagesArray.get(i).getTileName().equals(fromTile) && !imagesArray.get(i + 24).getPath().equals("images/blank.png") && !imagesArray.get(i + 24).getPath().equals("images/Target/target_black.png") && !imagesArray.get(i + 24).getPath().equals("images/Target/target_red.png") && (imagesArray.get(i + 24).getPath().contains("greenskin") || imagesArray.get(i + 24).getPath().contains("Reikland")) && figurePresenceAhead != true) {
                figurePresenceAhead = true;
            }
            if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 48).getPath().equals("images/blank.png") && !imagesArray.get(trueI + 48).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI + 48).getPath().equals("images/Target/target_red.png") && (imagesArray.get(trueI + 48).getPath().contains("greenskin")  || imagesArray.get(trueI + 48).getPath().contains("Reikland")) && figurePresenceAhead2 != true) {
                figurePresenceAhead2 = true;
                }
            }


            if (fromPosition != 0) {
                if (figurePresenceAhead == false) {
                    imagesArray.get(trueI+24).setBorders("15px");
                }
                if (figurePresenceAhead2 == false && (imagesArray.get(trueI).getTilenumber()-1) / 24 == 1) {
                    imagesArray.get(trueI+48).setBorders("15px");
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
        boolean figurePresenceAhead2 = false;
        int trueI = 0;

        for (int i = 0; i < imagesArray.size(); i++) {
                if (imagesArray.get(i).getTileName().equals(fromTile)) {
                    fromPosition = imagesArray.get(i).tilenumber;
                    trueI = i;
                    break;

                }
        }
                if (trueI - 25 > -1) {
                    if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 25).getPath().equals("images/blank.png") && !imagesArray.get(trueI - 25).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI - 25).getPath().equals("images/Target/target_red.png") && !imagesArray.get(trueI - 25).getPath().contains("Reikland") && figurePresenceLeft != true) {
                        figurePresenceLeft = true;
                    }
                    if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 23).getPath().equals("images/blank.png") && !imagesArray.get(trueI - 23).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI - 23).getPath().equals("images/Target/target_red.png") && !imagesArray.get(trueI - 23).getPath().contains("Reikland") && figurePresenceRight != true) {
                        figurePresenceRight = true;
                    }
                    if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 24).getPath().equals("images/blank.png") && !imagesArray.get(trueI - 24).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI - 24).getPath().equals("images/Target/target_red.png") && (imagesArray.get(trueI - 24).getPath().contains("Reikland")  || imagesArray.get(trueI - 24).getPath().contains("greenskin")) && figurePresenceAhead != true) {
                        figurePresenceAhead = true;
                    }
                    if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 48).getPath().equals("images/blank.png") && !imagesArray.get(trueI - 48).getPath().equals("images/Target/target_black.png") && !imagesArray.get(trueI - 48).getPath().equals("images/Target/target_red.png") && (imagesArray.get(trueI - 48).getPath().contains("Reikland")  || imagesArray.get(trueI - 48).getPath().contains("greenskin")) && figurePresenceAhead2 != true) {
                        figurePresenceAhead2 = true;
                    }
                }


                if (fromPosition != 0) {
                    if (figurePresenceAhead == false) {
                        imagesArray.get(trueI-24).setBorders("15px");
                    }
                    if (figurePresenceAhead2 == false && (imagesArray.get(trueI).getTilenumber()-1) / 24 == 8) {
                        imagesArray.get(trueI-48).setBorders("15px");
                    }
                    if (figurePresenceLeft == true) {
                        imagesArray.get(trueI-25).setBorders("15px");

                    }
                    if (figurePresenceRight == true) {
                        imagesArray.get(trueI-23).setBorders("15px");
                    }
                }
    }

    public int isTheSameRow(int tile1, int tile2){
        int returnsatement = 44;

        if ((((tile1 - 1) / 24)+1) == (((tile2 - 1) / 24)+1) ){
            returnsatement = 0;
        } else if ((((tile1 - 1) / 24)+1) < (((tile2 - 1) / 24)+1) ){
            returnsatement = 1;
        } else if ((((tile1 - 1) / 24)+1) > (((tile2 - 1) / 24)+1) ){
            returnsatement = -1;
        }



return returnsatement;
    }

    public int isTheSameRow1(int tile1, int tile2){
        int returnsatement = 44;

        if ((((tile1) / 24)+1) == (((tile2 ) / 24)+1) ){
            returnsatement = 0;
            System.out.println("2");
        } else if ((((tile1) / 24)+1) < (((tile2 ) / 24)+1) ){
            returnsatement = 1;
        } else if ((((tile1) / 24)+1) > (((tile2 ) / 24)+1) ){
            returnsatement = -1;
        }



        return returnsatement;
    }

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland KING
    public void HightlightPosibleTurnsReiklandKing(String fromTile) {
        int fromPosition = 0;
        boolean figurePresenceLeftAhead = false;
        boolean figurePresenceRightAhead = false;
        boolean figurePresenceAhead = false;
        boolean figurePresenceLeft = false;
        boolean figurePresenceRight = false;
        boolean figurePresenceBehind = false;
        boolean figurePresenceLeftBehind = false;
        boolean figurePresenceRightBehind = false;


        int trueI = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
                if (imagesArray.get(i).getTileName().equals(fromTile)) {
                    fromPosition = imagesArray.get(i).tilenumber;
                    trueI = i;
                    break;

                }
        }


                if (trueI != 0) {
                    if ((trueI - 25) > 0) {
                        if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 25).getPath().contains("Reikland") && figurePresenceLeftAhead != true  && isTheSameRow(trueI,trueI-25) == -1) {
                            figurePresenceLeftAhead = true;
                        }
                    }
                    if ((trueI - 23) > 0) {
                        if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 23).getPath().contains("Reikland") && figurePresenceRightAhead != true  && isTheSameRow(trueI,trueI-23) == -1) {
                            figurePresenceRightAhead = true;
                        }
                    }
                    if ((trueI - 24) > 0) {
                        if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 24).getPath().contains("Reikland") && figurePresenceAhead != true  && isTheSameRow(trueI,trueI-24) == -1) {
                            figurePresenceAhead = true;
                        }
                    }
                    if ((trueI - 1) > 0) {
                        if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 1).getPath().contains("Reikland") && figurePresenceLeft != true  && isTheSameRow(trueI,trueI-1) == 0) {
                            figurePresenceLeft = true;
                        }
                    }
                    if ((trueI + 1) > 0) {
                        if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 1).getPath().contains("Reikland") && figurePresenceRight != true  && isTheSameRow(trueI,trueI+1) == 0) {
                            figurePresenceRight = true;
                        }
                    }

                    if ((trueI + 24) < 240) {
                        if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 24).getPath().contains("Reikland") && figurePresenceBehind != true  && isTheSameRow(trueI,trueI+24) == 1) {
                            figurePresenceBehind = true;
                        }
                    }
                    if ((trueI + 24) < 240) {
                        if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 25).getPath().contains("Reikland") && figurePresenceLeftBehind != true  && isTheSameRow(trueI,trueI+25) == 1) {
                            figurePresenceLeftBehind = true;
                        }
                    }
                    if ((trueI + 24) < 240) {
                        if (imagesArray.get(trueI).getTileName().equals(fromTile)  && !imagesArray.get(trueI + 23).getPath().contains("Reikland") && figurePresenceRightBehind != true  && isTheSameRow(trueI,trueI+23) == 1) {
                            figurePresenceRightBehind = true;
                        }
                    }
                }


                if (fromPosition != 0) {
                    if (figurePresenceAhead == true) {
                        imagesArray.get(trueI-24).setBorders("15px");
                        oficerPosibleTurnsReikland.add(imagesArray.get(trueI-24).tileName);
                    }
                    if (figurePresenceLeftAhead == true) {
                        imagesArray.get(trueI-25).setBorders("15px");
                        oficerPosibleTurnsReikland.add(imagesArray.get(trueI-25).tileName);

                    }
                    if (figurePresenceRightAhead == true) {
                        imagesArray.get(trueI-23).setBorders("15px");
                        oficerPosibleTurnsReikland.add(imagesArray.get(trueI-23).tileName);

                    }
                    if (figurePresenceLeft == true) {
                        imagesArray.get(trueI-1).setBorders("15px");
                        oficerPosibleTurnsReikland.add(imagesArray.get(trueI-1).tileName);
                    }
                    if (figurePresenceRight == true) {
                        imagesArray.get(trueI+1).setBorders("15px");
                        oficerPosibleTurnsReikland.add(imagesArray.get(trueI+1).tileName);

                    }
                    if (figurePresenceBehind == true) {
                        imagesArray.get(trueI+24).setBorders("15px");
                        oficerPosibleTurnsReikland.add(imagesArray.get(trueI+24).tileName);

                    }
                    if (figurePresenceLeftBehind == true) {
                        imagesArray.get(trueI+25).setBorders("15px");
                        oficerPosibleTurnsReikland.add(imagesArray.get(trueI+25).tileName);
                    }
                    if (figurePresenceRightBehind == true) {
                        imagesArray.get(trueI+23).setBorders("15px");
                        oficerPosibleTurnsReikland.add(imagesArray.get(trueI+23).tileName);

                    }


                }
            }

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland OFICER
    public void HightlightPosibleTurnsReiklandOficer(String fromTile) {
        int fromPosition = 0;

        boolean ReiklandfigurePresenceAhead = false;
        boolean GreenskindfigurePresenceAhead = false;
        boolean blankfigurePresenceAhead = false;

        boolean ReiklandfigurePresenceAhead2 = false;
        boolean GreenskindfigurePresenceAhead2 = false;
        boolean blankfigurePresenceAhead2 = false;

        boolean ReiklandfigurePresenceLeft = false;
        boolean GreenskinfigurePresenceLeft = false;
        boolean blankfigurePresenceLeft = false;

        boolean ReiklandfigurePresenceLeft2 = false;
        boolean GreenskinfigurePresenceLeft2 = false;
        boolean blankfigurePresenceLeft2 = false;

        boolean ReiklandfigurePresenceRight = false;
        boolean GreenskinfigurePresenceRight = false;
        boolean blankfigurePresenceRight = false;

        boolean ReiklandfigurePresenceRight2 = false;
        boolean GreenskinfigurePresenceRight2 = false;
        boolean blankfigurePresenceRight2 = false;



        int trueI = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                fromPosition = imagesArray.get(i).tilenumber;
                trueI = i;
                break;

            }
        }


        if (trueI != 0) {

            if ((trueI - 24) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 24).getPath().contains("Reikland") && ReiklandfigurePresenceAhead != true  && isTheSameRow1(trueI,trueI-24) == -1) {
                    ReiklandfigurePresenceAhead = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 24).getPath().contains("greenskin")&& GreenskindfigurePresenceAhead != true  && isTheSameRow1(trueI,trueI-24) == -1) {
                    GreenskindfigurePresenceAhead = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 24).getPath().contains("Reikland") && !imagesArray.get(trueI - 24).getPath().contains("greenskin")&& blankfigurePresenceAhead != true  && isTheSameRow1(trueI,trueI-24) == -1) {
                    blankfigurePresenceAhead = true;
                }
            }


            if ((trueI - 48) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 48).getPath().contains("Reikland") && ReiklandfigurePresenceAhead2 != true  && isTheSameRow1(trueI,trueI-48) == -1) {
                    ReiklandfigurePresenceAhead2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 48).getPath().contains("greenskin")&& GreenskindfigurePresenceAhead2 != true  && isTheSameRow1(trueI,trueI-48) == -1) {
                    GreenskindfigurePresenceAhead2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 48).getPath().contains("Reikland") && !imagesArray.get(trueI - 48).getPath().contains("greenskin")&& blankfigurePresenceAhead2 != true  && isTheSameRow1(trueI,trueI-48) == -1) {
                    blankfigurePresenceAhead2 = true;
                }


            }

            if ((trueI - 1) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 1).getPath().contains("Reikland") && ReiklandfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI-1) == 0) {
                    ReiklandfigurePresenceLeft = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 1).getPath().contains("greenskin")&& GreenskinfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI-1) == 0) {
                    GreenskinfigurePresenceLeft = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 1).getPath().contains("Reikland") && !imagesArray.get(trueI - 1).getPath().contains("greenskin")&& blankfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI-1) == 0) {
                    blankfigurePresenceLeft = true;
                }
            }



            if ((trueI - 2) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 2).getPath().contains("Reikland") && ReiklandfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    ReiklandfigurePresenceLeft2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 2).getPath().contains("greenskin")&& GreenskinfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    GreenskinfigurePresenceLeft2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 2).getPath().contains("Reikland") && !imagesArray.get(trueI - 2).getPath().contains("greenskin")&& blankfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    blankfigurePresenceLeft2 = true;
                }
            }

            if ((trueI + 1) < 240) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 1).getPath().contains("Reikland") && ReiklandfigurePresenceRight != true  && isTheSameRow1(trueI,trueI+1) == 0) {
                    ReiklandfigurePresenceRight = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 1).getPath().contains("greenskin")&& GreenskinfigurePresenceRight != true  && isTheSameRow1(trueI,trueI+1) == 0) {
                    GreenskinfigurePresenceRight = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 1).getPath().contains("Reikland") && !imagesArray.get(trueI + 1).getPath().contains("greenskin")&& blankfigurePresenceRight != true  && isTheSameRow1(trueI,trueI+1) == 0) {
                    blankfigurePresenceRight = true;
                }
            }
            if ((trueI + 2) < 240) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 2).getPath().contains("Reikland") && ReiklandfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    ReiklandfigurePresenceRight2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 2).getPath().contains("greenskin")&& GreenskinfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    GreenskinfigurePresenceRight2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 2).getPath().contains("Reikland") && !imagesArray.get(trueI + 2).getPath().contains("greenskin")&& blankfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    blankfigurePresenceRight2 = true;
                }
            }

        }


        if (fromPosition != 0) {

            if (GreenskindfigurePresenceAhead == false && ReiklandfigurePresenceAhead == false && (GreenskindfigurePresenceAhead2 == true || blankfigurePresenceAhead2 == true)) {
                imagesArray.get(trueI-48).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-48).tileName);
            }
            if (ReiklandfigurePresenceAhead == false && (GreenskindfigurePresenceAhead == true || blankfigurePresenceAhead == true)) {
                imagesArray.get(trueI-24).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-24).tileName);
            }

            if (ReiklandfigurePresenceLeft == false && GreenskinfigurePresenceLeft == false && (GreenskinfigurePresenceLeft2 == true || blankfigurePresenceLeft2 == true)) {
                imagesArray.get(trueI-2).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-2).tileName);
            }
            if (ReiklandfigurePresenceLeft== false && (GreenskinfigurePresenceLeft == true || blankfigurePresenceLeft == true)) {
                imagesArray.get(trueI-1).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-1).tileName);
            }

            if (ReiklandfigurePresenceRight== false && (GreenskinfigurePresenceRight == true || blankfigurePresenceRight == true)) {
                imagesArray.get(trueI+1).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+1).tileName);
            }
            if (ReiklandfigurePresenceRight == false && GreenskinfigurePresenceRight == false && (GreenskinfigurePresenceRight2 == true || blankfigurePresenceRight2 == true)) {
                imagesArray.get(trueI+2).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+2).tileName);
            }
        }
    }

    //HIGHTLIGHT POSIBLE TURNS FOR GREENSKIN OFICER
    public void HightlightPosibleTurnsGreenskinOficer(String fromTile) {
        int fromPosition = 0;

        boolean ReiklandfigurePresenceAhead = false;
        boolean GreenskindfigurePresenceAhead = false;
        boolean blankfigurePresenceAhead = false;

        boolean ReiklandfigurePresenceAhead2 = false;
        boolean GreenskindfigurePresenceAhead2 = false;
        boolean blankfigurePresenceAhead2 = false;

        boolean ReiklandfigurePresenceLeft = false;
        boolean GreenskinfigurePresenceLeft = false;
        boolean blankfigurePresenceLeft = false;

        boolean ReiklandfigurePresenceLeft2 = false;
        boolean GreenskinfigurePresenceLeft2 = false;
        boolean blankfigurePresenceLeft2 = false;

        boolean ReiklandfigurePresenceRight = false;
        boolean GreenskinfigurePresenceRight = false;
        boolean blankfigurePresenceRight = false;

        boolean ReiklandfigurePresenceRight2 = false;
        boolean GreenskinfigurePresenceRight2 = false;
        boolean blankfigurePresenceRight2 = false;



        int trueI = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                fromPosition = imagesArray.get(i).tilenumber;
                trueI = i;
                break;

            }
        }


        if (trueI != 0) {

            if ((trueI + 24) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 24).getPath().contains("Reikland") && ReiklandfigurePresenceAhead != true  && isTheSameRow(trueI,trueI+24) == 1) {
                    ReiklandfigurePresenceAhead = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 24).getPath().contains("greenskin")&& GreenskindfigurePresenceAhead != true  && isTheSameRow(trueI,trueI+24) == 1) {
                    GreenskindfigurePresenceAhead = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 24).getPath().contains("Reikland") && !imagesArray.get(trueI + 24).getPath().contains("greenskin")&& blankfigurePresenceAhead != true  && isTheSameRow(trueI,trueI+24) == 1) {
                    blankfigurePresenceAhead = true;
                }
            }


            if ((trueI + 48) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 48).getPath().contains("Reikland") && ReiklandfigurePresenceAhead2 != true  && isTheSameRow(trueI,trueI+48) == 1) {
                    ReiklandfigurePresenceAhead2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 48).getPath().contains("greenskin")&& GreenskindfigurePresenceAhead2 != true  && isTheSameRow(trueI,trueI+48) == 1) {
                    GreenskindfigurePresenceAhead2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 48).getPath().contains("Reikland") && !imagesArray.get(trueI + 48).getPath().contains("greenskin")&& blankfigurePresenceAhead2 != true  && isTheSameRow(trueI,trueI+48) == 1) {
                    blankfigurePresenceAhead2 = true;
                }


            }

            if ((trueI + 1) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 1).getPath().contains("Reikland") && ReiklandfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI+1) == 0) {
                    ReiklandfigurePresenceLeft = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 1).getPath().contains("greenskin")&& GreenskinfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI+1) == 0) {
                    GreenskinfigurePresenceLeft = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 1).getPath().contains("Reikland") && !imagesArray.get(trueI + 1).getPath().contains("greenskin")&& blankfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI+1) == 0) {
                    blankfigurePresenceLeft = true;
                }
            }



            if ((trueI + 2) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 2).getPath().contains("Reikland") && ReiklandfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    ReiklandfigurePresenceLeft2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 2).getPath().contains("greenskin")&& GreenskinfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    GreenskinfigurePresenceLeft2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 2).getPath().contains("Reikland") && !imagesArray.get(trueI + 2).getPath().contains("greenskin")&& blankfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    blankfigurePresenceLeft2 = true;
                }
            }

            if ((trueI - 1) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 1).getPath().contains("Reikland") && ReiklandfigurePresenceRight != true  && isTheSameRow1(trueI,trueI-1) == 0) {
                    ReiklandfigurePresenceRight = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 1).getPath().contains("greenskin")&& GreenskinfigurePresenceRight != true  && isTheSameRow1(trueI,trueI-1) == 0) {
                    GreenskinfigurePresenceRight = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 1).getPath().contains("Reikland") && !imagesArray.get(trueI - 1).getPath().contains("greenskin")&& blankfigurePresenceRight != true  && isTheSameRow1(trueI,trueI-1) == 0) {
                    blankfigurePresenceRight = true;
                }
            }
            if ((trueI - 2) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 2).getPath().contains("Reikland") && ReiklandfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    ReiklandfigurePresenceRight2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 2).getPath().contains("greenskin")&& GreenskinfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    GreenskinfigurePresenceRight2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 2).getPath().contains("Reikland") && !imagesArray.get(trueI - 2).getPath().contains("greenskin")&& blankfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    blankfigurePresenceRight2 = true;
                }
            }

        }


        if (fromPosition != 0) {

            if (GreenskindfigurePresenceAhead == false && ReiklandfigurePresenceAhead == false && (ReiklandfigurePresenceAhead2 == true || blankfigurePresenceAhead2 == true)) {
                imagesArray.get(trueI+48).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+48).tileName);
            }
            if (GreenskindfigurePresenceAhead == false && (ReiklandfigurePresenceAhead == true || blankfigurePresenceAhead == true)) {
                imagesArray.get(trueI+24).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+24).tileName);
            }

            if (ReiklandfigurePresenceLeft == false && GreenskinfigurePresenceLeft == false && (ReiklandfigurePresenceLeft2 == true || blankfigurePresenceLeft2 == true)) {
                imagesArray.get(trueI+2).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+2).tileName);
            }
            if (GreenskinfigurePresenceLeft== false && (ReiklandfigurePresenceLeft == true || blankfigurePresenceLeft == true)) {
                imagesArray.get(trueI+1).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+1).tileName);
            }

            if (GreenskinfigurePresenceRight== false && (ReiklandfigurePresenceRight == true || blankfigurePresenceRight == true)) {
                imagesArray.get(trueI-1).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI-1).tileName);
            }
            if (ReiklandfigurePresenceRight == false && GreenskinfigurePresenceRight == false && (ReiklandfigurePresenceRight2 == true || blankfigurePresenceRight2 == true)) {
                imagesArray.get(trueI-2).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI-2).tileName);
            }
        }
    }

    //HIGHTLIGHT POSIBLE TURNS FOR GREENSKIN SPRINTER
    public void HightlightPosibleTurnsGreenskinSprinter(String fromTile) {
        int fromPosition = 0;

        boolean ReiklandfigurePresenceAhead = false;
        boolean GreenskindfigurePresenceAhead = false;
        boolean blankfigurePresenceAhead = false;

        boolean ReiklandfigurePresenceAhead2 = false;
        boolean GreenskindfigurePresenceAhead2 = false;
        boolean blankfigurePresenceAhead2 = false;

        boolean ReiklandfigurePresenceLeft = false;
        boolean GreenskinfigurePresenceLeft = false;
        boolean blankfigurePresenceLeft = false;

        boolean ReiklandfigurePresenceLeft2 = false;
        boolean GreenskinfigurePresenceLeft2 = false;
        boolean blankfigurePresenceLeft2 = false;

        boolean ReiklandfigurePresenceRight = false;
        boolean GreenskinfigurePresenceRight = false;
        boolean blankfigurePresenceRight = false;

        boolean ReiklandfigurePresenceRight2 = false;
        boolean GreenskinfigurePresenceRight2 = false;
        boolean blankfigurePresenceRight2 = false;

        boolean ReiklandfigurePresenceBehind= false;
        boolean GreenskinfigurePresenceBehind = false;
        boolean blankfigurePresenceBehind = false;



        int trueI = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                fromPosition = imagesArray.get(i).tilenumber;
                trueI = i;
                break;

            }
        }


        if (trueI != 0) {

            if ((trueI + 48) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 48).getPath().contains("Reikland") && ReiklandfigurePresenceAhead != true  && isTheSameRow(trueI,trueI+48) == 1) {
                    ReiklandfigurePresenceAhead = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 48).getPath().contains("greenskin")&& GreenskindfigurePresenceAhead != true  && isTheSameRow(trueI,trueI+48) == 1) {
                    GreenskindfigurePresenceAhead = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 48).getPath().contains("Reikland") && !imagesArray.get(trueI + 48).getPath().contains("greenskin")&& blankfigurePresenceAhead != true  && isTheSameRow(trueI,trueI+48) == 1) {
                    blankfigurePresenceAhead = true;
                }
            }


            if ((trueI + 72) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 72).getPath().contains("Reikland") && ReiklandfigurePresenceAhead2 != true  && isTheSameRow(trueI,trueI+72) == 1) {
                    ReiklandfigurePresenceAhead2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 72).getPath().contains("greenskin")&& GreenskindfigurePresenceAhead2 != true  && isTheSameRow(trueI,trueI+72) == 1) {
                    GreenskindfigurePresenceAhead2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 72).getPath().contains("Reikland") && !imagesArray.get(trueI + 72).getPath().contains("greenskin")&& blankfigurePresenceAhead2 != true  && isTheSameRow(trueI,trueI+72) == 1) {
                    blankfigurePresenceAhead2 = true;
                }


            }

            if ((trueI + 2) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 2).getPath().contains("Reikland") && ReiklandfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    ReiklandfigurePresenceLeft = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 2).getPath().contains("greenskin")&& GreenskinfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    GreenskinfigurePresenceLeft = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 2).getPath().contains("Reikland") && !imagesArray.get(trueI + 2).getPath().contains("greenskin")&& blankfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    blankfigurePresenceLeft = true;
                }
            }



            if ((trueI + 3) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 3).getPath().contains("Reikland") && ReiklandfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI+3) == 0) {
                    ReiklandfigurePresenceLeft2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 3).getPath().contains("greenskin")&& GreenskinfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI+3) == 0) {
                    GreenskinfigurePresenceLeft2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 3).getPath().contains("Reikland") && !imagesArray.get(trueI + 3).getPath().contains("greenskin")&& blankfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI+3) == 0) {
                    blankfigurePresenceLeft2 = true;
                }
            }

            if ((trueI - 2) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 2).getPath().contains("Reikland") && ReiklandfigurePresenceRight != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    ReiklandfigurePresenceRight = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 2).getPath().contains("greenskin")&& GreenskinfigurePresenceRight != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    GreenskinfigurePresenceRight = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 2).getPath().contains("Reikland") && !imagesArray.get(trueI - 2).getPath().contains("greenskin")&& blankfigurePresenceRight != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    blankfigurePresenceRight = true;
                }
            }
            if ((trueI - 24) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 24).getPath().contains("Reikland") && ReiklandfigurePresenceBehind != true  && isTheSameRow1(trueI,trueI- 24) == -1) {
                    ReiklandfigurePresenceBehind = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 24).getPath().contains("greenskin")&& GreenskinfigurePresenceBehind != true  && isTheSameRow1(trueI,trueI- 24) == -1) {
                    GreenskinfigurePresenceBehind = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 24).getPath().contains("Reikland") && !imagesArray.get(trueI - 24).getPath().contains("greenskin")&& blankfigurePresenceBehind != true  && isTheSameRow1(trueI,trueI- 24) == -1) {
                    blankfigurePresenceBehind = true;
                }
            }
            
            if ((trueI - 3) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 3).getPath().contains("Reikland") && ReiklandfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI-3) == 0) {
                    ReiklandfigurePresenceRight2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 3).getPath().contains("greenskin")&& GreenskinfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI-3) == 0) {
                    GreenskinfigurePresenceRight2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 3).getPath().contains("Reikland") && !imagesArray.get(trueI - 3).getPath().contains("greenskin")&& blankfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI-3) == 0) {
                    blankfigurePresenceRight2 = true;
                }
            }

        }


        if (fromPosition != 0) {

            if (GreenskindfigurePresenceAhead == false && ReiklandfigurePresenceAhead == false && (ReiklandfigurePresenceAhead2 == true || blankfigurePresenceAhead2 == true)) {
                imagesArray.get(trueI+72).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+72).tileName);
            }
            if (ReiklandfigurePresenceBehind == false && (GreenskinfigurePresenceBehind == true || blankfigurePresenceBehind == true)) {
                imagesArray.get(trueI-24).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-24).tileName);
            }
            if (GreenskindfigurePresenceAhead == false && (ReiklandfigurePresenceAhead == true || blankfigurePresenceAhead == true)) {
                imagesArray.get(trueI+48).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+48).tileName);
            }

            if (ReiklandfigurePresenceLeft == false && GreenskinfigurePresenceLeft == false && (ReiklandfigurePresenceLeft2 == true || blankfigurePresenceLeft2 == true)) {
                imagesArray.get(trueI+3).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+3).tileName);
            }
            if (GreenskinfigurePresenceLeft== false && (ReiklandfigurePresenceLeft == true || blankfigurePresenceLeft == true)) {
                imagesArray.get(trueI+2).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+2).tileName);
            }

            if (GreenskinfigurePresenceRight== false && (ReiklandfigurePresenceRight == true || blankfigurePresenceRight == true)) {
                imagesArray.get(trueI-2).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI-2).tileName);
            }
            if (ReiklandfigurePresenceRight == false && GreenskinfigurePresenceRight == false && (ReiklandfigurePresenceRight2 == true || blankfigurePresenceRight2 == true)) {
                imagesArray.get(trueI-3).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI-3).tileName);
            }
        }
    }

    //HIGHTLIGHT POSIBLE TURNS FOR Reikland SPRINTER
    public void HightlightPosibleTurnsReiklandSprinter(String fromTile) {
        int fromPosition = 0;

        boolean ReiklandfigurePresenceAhead = false;
        boolean GreenskindfigurePresenceAhead = false;
        boolean blankfigurePresenceAhead = false;

        boolean ReiklandfigurePresenceAhead2 = false;
        boolean GreenskindfigurePresenceAhead2 = false;
        boolean blankfigurePresenceAhead2 = false;

        boolean ReiklandfigurePresenceLeft = false;
        boolean GreenskinfigurePresenceLeft = false;
        boolean blankfigurePresenceLeft = false;

        boolean ReiklandfigurePresenceLeft2 = false;
        boolean GreenskinfigurePresenceLeft2 = false;
        boolean blankfigurePresenceLeft2 = false;

        boolean ReiklandfigurePresenceRight = false;
        boolean GreenskinfigurePresenceRight = false;
        boolean blankfigurePresenceRight = false;

        boolean ReiklandfigurePresenceRight2 = false;
        boolean GreenskinfigurePresenceRight2 = false;
        boolean blankfigurePresenceRight2 = false;

        boolean ReiklandfigurePresenceBehind= false;
        boolean GreenskinfigurePresenceBehind = false;
        boolean blankfigurePresenceBehind = false;



        int trueI = 1;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                fromPosition = imagesArray.get(i).tilenumber;
                trueI = i;
                break;

            }
        }


        if (trueI != -1) {

            if ((trueI - 48) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 48).getPath().contains("Reikland") && ReiklandfigurePresenceAhead != true  && isTheSameRow1(trueI,trueI-48) == -1) {
                    ReiklandfigurePresenceAhead = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 48).getPath().contains("greenskin")&& GreenskindfigurePresenceAhead != true  && isTheSameRow1(trueI,trueI-48) == -1) {
                    GreenskindfigurePresenceAhead = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 48).getPath().contains("Reikland") && !imagesArray.get(trueI - 48).getPath().contains("greenskin")&& blankfigurePresenceAhead != true  && isTheSameRow1(trueI,trueI-48) == -1) {
                    blankfigurePresenceAhead = true;
                }
            }

            if ((trueI + 24) < 240) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 24).getPath().contains("Reikland") && ReiklandfigurePresenceBehind != true  && isTheSameRow1(trueI,trueI+ 24) == 1) {
                    ReiklandfigurePresenceBehind = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 24).getPath().contains("greenskin")&& GreenskinfigurePresenceBehind != true  && isTheSameRow1(trueI,trueI+ 24) == 1) {
                    GreenskinfigurePresenceBehind = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 24).getPath().contains("Reikland") && !imagesArray.get(trueI + 24).getPath().contains("greenskin")&& blankfigurePresenceBehind != true  && isTheSameRow1(trueI,trueI+ 24) == 1) {
                    blankfigurePresenceBehind = true;
                }
            }


            if ((trueI - 72) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 72).getPath().contains("Reikland") && ReiklandfigurePresenceAhead2 != true  && isTheSameRow1(trueI,trueI-72) == -1) {
                    ReiklandfigurePresenceAhead2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 72).getPath().contains("greenskin")&& GreenskindfigurePresenceAhead2 != true  && isTheSameRow1(trueI,trueI-72) == -1) {
                    GreenskindfigurePresenceAhead2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 72).getPath().contains("Reikland") && !imagesArray.get(trueI - 72).getPath().contains("greenskin")&& blankfigurePresenceAhead2 != true  && isTheSameRow1(trueI,trueI-72) == -1) {
                    blankfigurePresenceAhead2 = true;
                }


            }

            if ((trueI - 2) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 2).getPath().contains("Reikland") && ReiklandfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    ReiklandfigurePresenceLeft = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 2).getPath().contains("greenskin")&& GreenskinfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    GreenskinfigurePresenceLeft = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 2).getPath().contains("Reikland") && !imagesArray.get(trueI - 2).getPath().contains("greenskin")&& blankfigurePresenceLeft != true  && isTheSameRow1(trueI,trueI-2) == 0) {
                    blankfigurePresenceLeft = true;
                }
            }



            if ((trueI - 3) > -1) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 3).getPath().contains("Reikland") && ReiklandfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI-3) == 0) {
                    ReiklandfigurePresenceLeft2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI - 3).getPath().contains("greenskin")&& GreenskinfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI-3) == 0) {
                    GreenskinfigurePresenceLeft2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 3).getPath().contains("Reikland") && !imagesArray.get(trueI - 3).getPath().contains("greenskin")&& blankfigurePresenceLeft2 != true  && isTheSameRow1(trueI,trueI-3) == 0) {
                    blankfigurePresenceLeft2 = true;
                }
            }

            if ((trueI + 2) < 240) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 2).getPath().contains("Reikland") && ReiklandfigurePresenceRight != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    ReiklandfigurePresenceRight = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 2).getPath().contains("greenskin")&& GreenskinfigurePresenceRight != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    GreenskinfigurePresenceRight = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 2).getPath().contains("Reikland") && !imagesArray.get(trueI + 2).getPath().contains("greenskin")&& blankfigurePresenceRight != true  && isTheSameRow1(trueI,trueI+2) == 0) {
                    blankfigurePresenceRight = true;
                }
            }
            if ((trueI + 3) < 240) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 3).getPath().contains("Reikland") && ReiklandfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI+3) == 0) {
                    ReiklandfigurePresenceRight2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && imagesArray.get(trueI + 3).getPath().contains("greenskin")&& GreenskinfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI+3) == 0) {
                    GreenskinfigurePresenceRight2 = true;
                }
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 3).getPath().contains("Reikland") && !imagesArray.get(trueI + 3).getPath().contains("greenskin")&& blankfigurePresenceRight2 != true  && isTheSameRow1(trueI,trueI+3) == 0) {
                    blankfigurePresenceRight2 = true;
                }
            }

        }


        if (fromPosition != 0) {

            if (GreenskindfigurePresenceAhead == false && ReiklandfigurePresenceAhead == false && (GreenskindfigurePresenceAhead2 == true || blankfigurePresenceAhead2 == true)) {
                imagesArray.get(trueI-48).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-48).tileName);
            }

            if (ReiklandfigurePresenceAhead == false && (GreenskindfigurePresenceAhead == true || blankfigurePresenceAhead == true)) {
                imagesArray.get(trueI-72).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-72).tileName);
            }
            if (ReiklandfigurePresenceBehind == false && (GreenskinfigurePresenceBehind == true || blankfigurePresenceBehind == true)) {
                imagesArray.get(trueI+24).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+24).tileName);
            }

            if (ReiklandfigurePresenceLeft == false && GreenskinfigurePresenceLeft == false && (GreenskinfigurePresenceLeft2 == true || blankfigurePresenceLeft2 == true)) {
                imagesArray.get(trueI-3).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-3).tileName);
            }
            if (ReiklandfigurePresenceLeft== false && (GreenskinfigurePresenceLeft == true || blankfigurePresenceLeft == true)) {
                imagesArray.get(trueI-2).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI-2).tileName);
            }

            if (ReiklandfigurePresenceRight== false && (GreenskinfigurePresenceRight == true || blankfigurePresenceRight == true)) {
                imagesArray.get(trueI+2).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+2).tileName);
            }
            if (ReiklandfigurePresenceRight == false && GreenskinfigurePresenceRight == false && (GreenskinfigurePresenceRight2 == true || blankfigurePresenceRight2 == true)) {
                imagesArray.get(trueI+3).setBorders("15px");
                oficerPosibleTurnsReikland.add(imagesArray.get(trueI+3).tileName);
            }
        }
    }

    //KING MOVEMENT LOGIC REIKLAND
    public boolean KingLogicReikland(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsReikland.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
    }

    //HIGHTLIGHT POSIBLE TURNS FOR GREENSKIN KING
    public void HightlightPosibleTurnsGreenskinKing(String fromTile) {
        int fromPosition = 0;
        boolean figurePresenceLeftAhead = false;
        boolean figurePresenceRightAhead = false;
        boolean figurePresenceAhead = false;
        boolean figurePresenceLeft = false;
        boolean figurePresenceRight = false;
        boolean figurePresenceBehind = false;
        boolean figurePresenceLeftBehind = false;
        boolean figurePresenceRightBehind = false;


        int trueI = 0;
        for (int i = 0; i < imagesArray.size(); i++) {
            if (imagesArray.get(i).getTileName().equals(fromTile)) {
                fromPosition = imagesArray.get(i).tilenumber;
                trueI = i;
                break;

            }
        }


        if (trueI != 0) {
            if ((trueI - 25) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 25).getPath().contains("greenskin") && figurePresenceLeftAhead != true && isTheSameRow(trueI,trueI-25) == -1) {
                    figurePresenceLeftAhead = true;
                }
            }
            if ((trueI - 23) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 23).getPath().contains("greenskin") && figurePresenceRightAhead != true  && isTheSameRow(trueI,trueI-23) == -1) {
                    figurePresenceRightAhead = true;
                }
            }
            if ((trueI - 24) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 24).getPath().contains("greenskin") && figurePresenceAhead != true  && isTheSameRow(trueI,trueI-24) == -1) {
                    figurePresenceAhead = true;
                }
            }
            if ((trueI - 1) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI - 1).getPath().contains("greenskin") && figurePresenceLeft != true  && isTheSameRow(trueI,trueI-1) == 0) {
                    figurePresenceLeft = true;
                }
            }
            if ((trueI + 1) > 0) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 1).getPath().contains("greenskin") && figurePresenceRight != true  && isTheSameRow(trueI,trueI+1) == 0) {
                    figurePresenceRight = true;
                }
            }

            if ((trueI + 24) < 240) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 24).getPath().contains("greenskin") && figurePresenceBehind != true  && isTheSameRow(trueI,trueI+24) == 1) {
                    figurePresenceBehind = true;
                }
            }
            if ((trueI + 24) < 240) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile) && !imagesArray.get(trueI + 25).getPath().contains("greenskin") && figurePresenceLeftBehind != true  && isTheSameRow(trueI,trueI+25) == 1) {
                    figurePresenceLeftBehind = true;
                }
            }
            if ((trueI + 24) < 240) {
                if (imagesArray.get(trueI).getTileName().equals(fromTile)  && !imagesArray.get(trueI + 23).getPath().contains("greenskin") && figurePresenceRightBehind != true  && isTheSameRow(trueI,trueI+23) == 1) {
                    figurePresenceRightBehind = true;
                }
            }
        }


        if (fromPosition != 0) {
            if (figurePresenceAhead == true) {
                imagesArray.get(trueI-24).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI-24).tileName);
            }
            if (figurePresenceLeftAhead == true) {
                imagesArray.get(trueI-25).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI-25).tileName);

            }
            if (figurePresenceRightAhead == true) {
                imagesArray.get(trueI-23).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI-23).tileName);

            }
            if (figurePresenceLeft == true) {
                imagesArray.get(trueI-1).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI-1).tileName);
            }
            if (figurePresenceRight == true) {
                imagesArray.get(trueI+1).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+1).tileName);

            }
            if (figurePresenceBehind == true) {
                imagesArray.get(trueI+24).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+24).tileName);

            }
            if (figurePresenceLeftBehind == true) {
                imagesArray.get(trueI+25).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+25).tileName);
            }
            if (figurePresenceRightBehind == true) {
                imagesArray.get(trueI+23).setBorders("15px");
                oficerPosibleTurnsGreenskins.add(imagesArray.get(trueI+23).tileName);

            }


        }
    }



    //KING MOVEMENT LOGIC GREENSKIN
    public boolean KingLogicGreenskin(String fromTile, String toTile) {
        boolean returnstatement = false;

        if (oficerPosibleTurnsGreenskins.contains(toTile)) {
            returnstatement = true;
        }
        return returnstatement;
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











