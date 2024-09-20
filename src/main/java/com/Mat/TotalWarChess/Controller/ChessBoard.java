package com.Mat.TotalWarChess.Controller;


import com.Mat.TotalWarChess.Model.ChessMoves;
import com.Mat.TotalWarChess.Model.FigureImage;
import com.Mat.TotalWarChess.imagesSetter.ImagesSetter;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@SessionAttributes("cellInterface")
public class ChessBoard {
    ChessMoves chessMoves = new ChessMoves();


    //GET MAPPING
    @GetMapping("/chessboard")
    public String chessboard(Model model, HttpSession session) {

        ChessMoves chessMoves = (ChessMoves) session.getAttribute("chessMoves");
        if (chessMoves == null) {
            chessMoves = new ChessMoves();
            session.setAttribute("chessMoves", chessMoves);
        }

        model.addAttribute("players_turn_image", "images/Reikland.png");
        ImagesSetter imagesSetter = new ImagesSetter();
        imagesSetter.imagesSetter(model, session);

        String username = (String) session.getAttribute("username");
        String secondUsername = (String) session.getAttribute("secondUsername");

        model.addAttribute("username", username);
        model.addAttribute("secondUsername", secondUsername);
        model.addAttribute("playerss_turn", session.getAttribute("secondUsername"));

        return "ChessBoard";
    }



    // POST MAPPING

    @PostMapping("/chessboard")
    public String movePiece(@RequestParam(value = "currentCell") String fromCell,
                            @RequestParam(value = "secondCurrentCell", required = false) String toCell,
                            HttpSession session, Model model) {

        HashMap<Integer, String> hightlightedTile = (HashMap<Integer, String>) session.getAttribute("hightlightedTile");
        Integer playerMovePoints = (Integer) session.getAttribute("playerMovePoints");
        Integer playersTurn = (Integer) session.getAttribute("playersTurn");
        String playersFigure = (String) session.getAttribute("playersFigure");
        String playersFirstChoosenFigure = (String) session.getAttribute("playersFirstChoosenFigure");
        Integer isMortarTimeToShoot = (Integer) session.getAttribute("mortarTimeToShoot");
        Integer isMortarTimeToShoot1 = (Integer) session.getAttribute("mortarTimeToShoot1");
        String targetCell = (String) session.getAttribute("targetCell");

        if (playersFigure == null) {
            playersFigure = "";
        }
        if (hightlightedTile == null) {
            hightlightedTile = new HashMap<>();
        }
        if (playerMovePoints == null) {
            playerMovePoints = 0;
        }
        if (playersTurn == null) {
            playersTurn = 2;
        }
        if (playersFirstChoosenFigure == null) {
            playersFirstChoosenFigure = "";
        }
        if (isMortarTimeToShoot == null) {
            isMortarTimeToShoot = 0;
        }
        if (isMortarTimeToShoot1 == null) {
            isMortarTimeToShoot1 = 0;
        }
        if (targetCell == null) {
            targetCell = "";
        }

        System.out.println(hightlightedTile.size() + " 1");


        ChessMoves chessMoves = (ChessMoves) session.getAttribute("chessMoves");
        if (chessMoves == null) {
            chessMoves = new ChessMoves();
            session.setAttribute("chessMoves", chessMoves);
        }


        String username = (String) session.getAttribute("username");
        String secondUsername = (String) session.getAttribute("secondUsername");
        model.addAttribute("username", username);
        model.addAttribute("secondUsername", secondUsername);
        String firstCellSaver = (String) session.getAttribute("firstCellSaver");
        String secondCellSaver = (String) session.getAttribute("secondCellSaver");


        //GAME LOGICK
        if (firstCellSaver == null) {
            firstCellSaver = "";
        }
        if (secondCellSaver == null) {
            secondCellSaver = "";
        }


// MOVEMENTS

        // ====================================================GREEN SKIN PLAYER LOGIC=================================================
        if (fromCell != null && playersTurn == 1) {

            //==============================================FIRST TURN PAWN=====================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("pawn")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsGreenSkin(fromCell);

                //UN CHOOSE CELL
            } else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true || fromCell.contains("cell00")) && chessMoves.figureChecker(fromCell).equals("pawn") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);

                //SECOND TURN PAWN
            } else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("pawn") && chessMoves.PawnLogicGreenSkin(firstCellSaver, fromCell)) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                    chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }


//===============================================FIRST TURN KING======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("king")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsGreenskinKing(fromCell);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true || fromCell.contains("cell00")) && chessMoves.figureChecker(firstCellSaver).equals("king") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN KING
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("king") && chessMoves.KingLogicGreenskin(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }


            // ===================================FIRST TURN QUEEN==========================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("queen")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsGreenskindQueen(fromCell);
            } else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true && chessMoves.figureChecker(fromCell).equals("queen") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.clearOficerTurnsGreenskin();
            }
            //SECOND TURN QUEEN
            if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("queen") && chessMoves.QueenLogicGreenskin(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                    chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
                chessMoves.clearOficerTurnsGreenskin();
            }
            //===============================================FIRST TURN Wizard======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("wizard")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsGreenskinWizard(fromCell);
            } else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true && chessMoves.figureChecker(fromCell).equals("wizard") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.clearOficerTurnsGreenskin();
            }

            //SECOND TURN Wizard
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("wizard") && chessMoves.WizardLogicGreenskin(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                    chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
                chessMoves.clearOficerTurnsGreenskin();
            }

            //===============================================FIRST TURN OFICER======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("oficer")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsGreenskinOficer(fromCell);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true || fromCell.contains("cell00")) && chessMoves.figureChecker(firstCellSaver).equals("oficer") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN OFICER
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("oficer") && chessMoves.OficerLogicGreenskin(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }

            //===============================================FIRST TURN SPRINTER======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("sprinter")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsGreenskinSprinter(fromCell);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true || fromCell.contains("cell00")) && chessMoves.figureChecker(firstCellSaver).equals("sprinter") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN OFICER
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("sprinter") && chessMoves.OficerLogicGreenskin(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }

            //=================================================FIRST TURN ROOK=====================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("rook")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsGreenskinRook(fromCell);
            }else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true && chessMoves.figureChecker(firstCellSaver).equals("rook") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.clearOficerTurnsReikland();
            }

            //SECOND TURN QUEEN
            if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("rook") && chessMoves.RookLogicGreenskin(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
                chessMoves.clearOficerTurnsReikland();
            }

            //===============================================FIRST TURN CAVALRY======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("cavalry")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsGreenskinCavalry(fromCell);
            } else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true && chessMoves.figureChecker(fromCell).equals("cavalry") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearOficerTurnsGreenskin();
                chessMoves.clearCavalryTurns();
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }

            //SECOND TURN CAVALRY
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("cavalry") && chessMoves.CavalryLogicGreenskin(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                    chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
                chessMoves.clearOficerTurnsGreenskin();
                chessMoves.clearCavalryTurns();
            }

            //===============================================FIRST TURN MORTAR======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmptyMorta(fromCell) != true && chessMoves.isFirstPlayerTurnMorta(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && fromCell.contains("cell00_01")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTileMortarGreenskin(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieGreenSkin(fromCell) == true && firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                firstCellSaver = "";
                chessMoves.unHightlightTileMortarGreenskin();
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN MORTAR
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && firstCellSaver.contains("cell00") && chessMoves.MortarReiklandLogic(fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
                chessMoves.unHightlightTileMortarGreenskin();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }

            //END OF MOVE MORTAR

            if (!firstCellSaver.isEmpty() && !secondCellSaver.isEmpty() && firstCellSaver.contains("cell00")) {
                chessMoves.MortarGreenskinMOVE(fromCell);
                session.setAttribute("targetCell1", secondCellSaver);
                isMortarTimeToShoot1 = 1;
                session.setAttribute("mortarTimeToShoot1", isMortarTimeToShoot1);
                session.setAttribute("playersFirstChoosenFigure", firstCellSaver);
                firstCellSaver = "";
                secondCellSaver = "";
                session.setAttribute("secondCellSaver", secondCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                playerMovePoints++;
                session.setAttribute("playerMovePoints", playerMovePoints);
                chessMoves.clearOficerTurnsGreenskin();
                chessMoves.clearCavalryTurns();
            }


            //=======================================END OF MOVE============================================

            if (!firstCellSaver.isEmpty() && !secondCellSaver.isEmpty()) {
                if (chessMoves.imagesArray.get(chessMoves.numberDetect(secondCellSaver)).getPath().contains("target")) {
                    chessMoves.swapTilesTargetSecondCell(firstCellSaver, secondCellSaver);
                } else if (chessMoves.imagesArray.get(chessMoves.numberDetect(firstCellSaver)).getPath().contains("target")) {
                    chessMoves.swapTilesTargetFirstCell(firstCellSaver, secondCellSaver);
                } else {
                    chessMoves.imagesArray.get(22);
                    chessMoves.swapTiles(firstCellSaver, secondCellSaver);
                }

                if (chessMoves.isSecondPlayerTurn(firstCellSaver)) {
                    if (hightlightedTile.get(5) == null) {
                        hightlightedTile.put(5, firstCellSaver);
                        hightlightedTile.put(6, secondCellSaver);
                    } else if (hightlightedTile.get(5) != null) {
                        hightlightedTile.put(7, firstCellSaver);
                        hightlightedTile.put(8, secondCellSaver);
                    }
                }
                session.setAttribute("hightlightedTile", hightlightedTile);
                System.out.println(hightlightedTile.size() + "2");
                chessMoves.hightlightTile(firstCellSaver);
                chessMoves.hightlightTile(secondCellSaver);


                session.setAttribute("playersFirstChoosenFigure", secondCellSaver);
                playerMovePoints++;
                firstCellSaver = "";
                secondCellSaver = "";
                session.setAttribute("secondCellSaver", secondCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                session.setAttribute("playerMovePoints", playerMovePoints);
                chessMoves.clearOficerTurnsGreenskin();
            }
        }




        // ===========================================Reikland PLAYER LOGIC========================================
        if (fromCell != null && playersTurn == 2) {

            //===============================================FIRST TURN PAWNS======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("pawn")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandPawn(fromCell);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true || fromCell.contains("cell00")) && chessMoves.figureChecker(firstCellSaver).equals("pawn") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN PAWN
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("pawn") && chessMoves.PawnLogicReikland(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }


//===============================================FIRST TURN KING======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("king")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandKing(fromCell);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true || fromCell.contains("cell00")) && chessMoves.figureChecker(firstCellSaver).equals("king") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN KING
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("king") && chessMoves.KingLogicReikland(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }


            //===============================================FIRST TURN TANK======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !chessMoves.figureChecker(playersFirstChoosenFigure).contains("tank")  && chessMoves.figureChecker(fromCell).equals("tank_head")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTileTank(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandTANK(fromCell);
            }

            //TANK BACK
            else if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && !playersFirstChoosenFigure.equals(chessMoves.tankBackInHead(fromCell)) && chessMoves.figureChecker(fromCell).equals("tank_back")) {
                firstCellSaver = chessMoves.tankBackInHead(fromCell);
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTileTank(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandTANK(firstCellSaver);
            }


            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true || fromCell.contains("cell00")) && (chessMoves.figureChecker(fromCell).equals("tank_back") || chessMoves.figureChecker(fromCell).equals("tank_head"))  && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTileTank(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN TANK
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("tank_head") && chessMoves.TankLogicReikland(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;

                session.setAttribute("secondCellSaver", secondCellSaver);
            }

            //END OF MOVE TANK

            if (!firstCellSaver.isEmpty() && !secondCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).contains("tank")) {
                chessMoves.TankReiklandMOVE(firstCellSaver, secondCellSaver);



                if (chessMoves.isSecondPlayerTurn(firstCellSaver)) {
                    if (hightlightedTile.get(1) == null) {
                        hightlightedTile.put(1, firstCellSaver);
                        hightlightedTile.put(2, secondCellSaver);
                        hightlightedTile.put(10, chessMoves.numberToName(chessMoves.numberDetect(secondCellSaver)+49));
                        hightlightedTile.put(11, chessMoves.numberToName(chessMoves.numberDetect(secondCellSaver)+24));
                        hightlightedTile.put(12, chessMoves.numberToName(chessMoves.numberDetect(secondCellSaver)+26));
                        hightlightedTile.put(13, chessMoves.numberToName(chessMoves.numberDetect(firstCellSaver)+25));

                    } else {
                        hightlightedTile.put(3, firstCellSaver);
                        hightlightedTile.put(4, secondCellSaver);
                        hightlightedTile.put(10, chessMoves.numberToName(chessMoves.numberDetect(secondCellSaver)+49));
                        hightlightedTile.put(11, chessMoves.numberToName(chessMoves.numberDetect(secondCellSaver)+24));
                        hightlightedTile.put(12, chessMoves.numberToName(chessMoves.numberDetect(secondCellSaver)+26));
                        hightlightedTile.put(13, chessMoves.numberToName(chessMoves.numberDetect(secondCellSaver)+25));
                    }
                }
                session.setAttribute("hightlightedTile", hightlightedTile);
                chessMoves.hightlightTile(firstCellSaver);
                chessMoves.hightlightTile(secondCellSaver);

                session.setAttribute("playersFirstChoosenFigure", firstCellSaver);
                firstCellSaver = "";
                secondCellSaver = "";
                session.setAttribute("secondCellSaver", secondCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                playerMovePoints++;
                session.setAttribute("playerMovePoints", playerMovePoints);
                chessMoves.clearOficerTurnsReikland();
                chessMoves.clearCavalryTurns();
            }

            //===============================================FIRST TURN OFICER======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("oficer")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandOficer(fromCell);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true || fromCell.contains("cell00")) && chessMoves.figureChecker(firstCellSaver).equals("oficer") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN OFICER
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("oficer") && chessMoves.OficerLogicReikland(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }

            //===============================================FIRST TURN SPRINTER======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("sprinter")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandSprinter(fromCell);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && (chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true || fromCell.contains("cell00")) && chessMoves.figureChecker(firstCellSaver).equals("sprinter") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN OFICER
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("sprinter") && chessMoves.OficerLogicReikland(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }

            //=================================================FIRST TURN QUEEN=====================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("queen")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandQueen(fromCell);
            }else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true && chessMoves.figureChecker(firstCellSaver).equals("queen") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.clearOficerTurnsReikland();
            }
            //SECOND TURN QUEEN
            if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("queen") && chessMoves.QueenLogicReikland(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
                chessMoves.clearOficerTurnsReikland();
            }


            //=================================================FIRST TURN ROOK=====================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("rook")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandRook(fromCell);
            }else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true && chessMoves.figureChecker(firstCellSaver).equals("rook") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.clearOficerTurnsReikland();
            }
            //SECOND TURN ROOK
            if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("rook") && chessMoves.RookLogicREIKLAND(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
                chessMoves.clearOficerTurnsReikland();
            }

        //===============================================FIRST TURN Wizard======================================
        if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("wizard")) {
            firstCellSaver = fromCell;
            session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
            chessMoves.hightlightTile(firstCellSaver);
            session.setAttribute("firstCellSaver", firstCellSaver);
            chessMoves.HightlightPosibleTurnsReiklandWizard(fromCell);
        }else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true && chessMoves.figureChecker(firstCellSaver).equals("wizard") && !firstCellSaver.contains("cell00")) {
            chessMoves.clearHighlights();
            chessMoves.unHightlightTile(firstCellSaver);
            firstCellSaver = "";
            session.setAttribute("firstCellSaver", firstCellSaver);
            chessMoves.clearOficerTurnsReikland();
        }

        //SECOND TURN Wizard
        else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("wizard") && chessMoves.WizardLogicReikland(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
            chessMoves.clearHighlights();
            secondCellSaver = fromCell;
//            chessMoves.unHightlightTile(firstCellSaver);
            session.setAttribute("secondCellSaver", secondCellSaver);
            chessMoves.clearOficerTurnsReikland();
        }

        //===============================================FIRST TURN CAVALRY======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && chessMoves.figureChecker(fromCell).equals("cavalry")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                chessMoves.HightlightPosibleTurnsReiklandCavalry(fromCell);
            }else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true && chessMoves.figureChecker(firstCellSaver).equals("cavalry") && !firstCellSaver.contains("cell00")) {
                chessMoves.clearOficerTurnsReikland();
                chessMoves.clearCavalryTurns();
                chessMoves.clearHighlights();
                chessMoves.unHightlightTile(firstCellSaver);
                firstCellSaver = "";
                session.setAttribute("firstCellSaver", firstCellSaver);
            }

        //SECOND TURN CAVALRY
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureChecker(firstCellSaver).equals("cavalry") && chessMoves.CavalryLogicReikland(firstCellSaver, fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
//                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
                chessMoves.clearOficerTurnsReikland();
                chessMoves.clearCavalryTurns();
            }

//===============================================FIRST TURN MORTAR======================================
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmptyMorta(fromCell) != true && chessMoves.isSecondPlayerTurnMorta(fromCell) == true && !playersFirstChoosenFigure.equals(fromCell) && fromCell.contains("cell00_00")) {
                firstCellSaver = fromCell;
                session.setAttribute("playersFigure", chessMoves.figureChecker(firstCellSaver));
                chessMoves.hightlightTileMortarReikland(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //UN CHOOSE CELL
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.figureCheckerSameOrAllieReikland(fromCell) == true && firstCellSaver.contains("cell00")) {
                chessMoves.clearHighlights();
                firstCellSaver = "";
                chessMoves.unHightlightTileMortarReikland();
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
            //SECOND TURN MORTAR
            else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && firstCellSaver.contains("cell00") && chessMoves.MortarReiklandLogic(fromCell) && firstCellSaver != fromCell) {
                chessMoves.clearHighlights();
                secondCellSaver = fromCell;
                chessMoves.unHightlightTileMortarReikland();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }

            //END OF MOVE MORTAR

            if (!firstCellSaver.isEmpty() && !secondCellSaver.isEmpty() && firstCellSaver.contains("cell00")) {
                chessMoves.MortarReiklandMOVE(fromCell);
                session.setAttribute("targetCell", secondCellSaver);
                isMortarTimeToShoot = 1;
                session.setAttribute("hightlightedTile", hightlightedTile);
                session.setAttribute("mortarTimeToShoot", isMortarTimeToShoot);
                session.setAttribute("playersFirstChoosenFigure", firstCellSaver);
                firstCellSaver = "";
                secondCellSaver = "";
                session.setAttribute("secondCellSaver", secondCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                playerMovePoints++;
                session.setAttribute("playerMovePoints", playerMovePoints);
                chessMoves.clearOficerTurnsReikland();
                chessMoves.clearCavalryTurns();
            }



//===================================END OF MOVE======================================
            if (!firstCellSaver.isEmpty() && !secondCellSaver.isEmpty() && !firstCellSaver.contains("cell00")) {
                if (chessMoves.imagesArray.get(chessMoves.numberDetect(secondCellSaver)).getPath().contains("target")){
                    chessMoves.swapTilesTargetSecondCell(firstCellSaver,secondCellSaver);
                } else {
                    chessMoves.swapTiles(firstCellSaver, secondCellSaver);
                }

                    if (chessMoves.isSecondPlayerTurn(firstCellSaver)) {
                        if (hightlightedTile.get(1) == null) {
                            hightlightedTile.put(1, firstCellSaver);
                            hightlightedTile.put(2, secondCellSaver);
                        } else {
                            hightlightedTile.put(3, firstCellSaver);
                            hightlightedTile.put(4, secondCellSaver);
                        }
                    }
                    session.setAttribute("hightlightedTile", hightlightedTile);
                chessMoves.hightlightTile(firstCellSaver);
                chessMoves.hightlightTile(secondCellSaver);

                session.setAttribute("playersFirstChoosenFigure", secondCellSaver);
                firstCellSaver = "";
                secondCellSaver = "";
                session.setAttribute("secondCellSaver", secondCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                playerMovePoints++;
                session.setAttribute("playerMovePoints", playerMovePoints);
                chessMoves.clearOficerTurnsReikland();
                chessMoves.clearCavalryTurns();
            }
        }




//TURN CHANGER
        if (playerMovePoints == 2 && playersTurn == 1){
            if (chessMoves.RemeiningPiecesCounter() == "Reikland"){
            model.addAttribute("Victor", session.getAttribute("secondUsername"));
                model.addAttribute("VictorFraction", "GREENSKIN");
            return "VictoryPage.html";
            }





            playerMovePoints = 0;
            playersTurn = 2;
            System.out.println(hightlightedTile + " 4");

            chessMoves.unHightlightTile(hightlightedTile.get(1));
            chessMoves.unHightlightTile(hightlightedTile.get(2));
            chessMoves.unHightlightTile(hightlightedTile.get(3));
            chessMoves.unHightlightTile(hightlightedTile.get(4));
            chessMoves.unHightlightTile(hightlightedTile.get(10));
            chessMoves.unHightlightTile(hightlightedTile.get(11));
            chessMoves.unHightlightTile(hightlightedTile.get(12));
            chessMoves.unHightlightTile(hightlightedTile.get(13));
            hightlightedTile.put(1, null);
            hightlightedTile.put(2, null);
            hightlightedTile.put(3, null);
            hightlightedTile.put(4, null);
            hightlightedTile.put(10, null);
            hightlightedTile.put(11, null);
            hightlightedTile.put(12, null);
            hightlightedTile.put(13, null);
            chessMoves.imagesArray.get(0);
            session.setAttribute("hightlightedTile", hightlightedTile);

            if (isMortarTimeToShoot.equals(1)){
                String targetCell1 = (String) (session.getAttribute("targetCell"));
                chessMoves.mortarShooting(targetCell1);
                session.setAttribute("targetCell", "");
                session.setAttribute("mortarTimeToShoot", 0);
            }
            session.setAttribute("playersFirstChoosenFigure", "");



        }
         else if(playerMovePoints == 2 && playersTurn == 2){
            playerMovePoints = 0;
            playersTurn = 1;
            if (chessMoves.RemeiningPiecesCounter() == "Greenskin"){
                model.addAttribute("Victor", session.getAttribute("username"));
                model.addAttribute("VictorFraction", "REIKLAND");
                return "VictoryPage.html";
            }

            chessMoves.unHightlightTile(hightlightedTile.get(5));
            chessMoves.unHightlightTile(hightlightedTile.get(6));
            chessMoves.unHightlightTile(hightlightedTile.get(7));
            chessMoves.unHightlightTile(hightlightedTile.get(8));
            hightlightedTile.put(5, null);
            hightlightedTile.put(6, null);
            hightlightedTile.put(7, null);
            hightlightedTile.put(8, null);
            session.setAttribute("hightlightedTile", hightlightedTile);
            if (isMortarTimeToShoot1.equals(1)){
                String targetCell2 = (String) (session.getAttribute("targetCell1"));
                chessMoves.mortarShooting(targetCell2);
                session.setAttribute("targetCell1", "");
                session.setAttribute("mortarTimeToShoot1", 0);
            }
            session.setAttribute("playersFirstChoosenFigure", "");

        }
        if(playersTurn == 1){
            model.addAttribute("playerss_turn", session.getAttribute("username"));
            model.addAttribute("players_turn_image", "images/Orcs.png");
        } else if (playersTurn == 2){
            model.addAttribute("playerss_turn", session.getAttribute("secondUsername"));
            model.addAttribute("players_turn_image", "images/Reikland.png");
        }

        session.setAttribute("playerMovePoints", playerMovePoints);
        session.setAttribute("playersTurn", playersTurn);

// SAVE UPDATED TILES
            chessMoves.updateModelWithHighlightedTiles(model);


// BOTH VALUES TO NULL
            if (fromCell.equals("clear")) {
                firstCellSaver = "";
                secondCellSaver = "";
                session.removeAttribute("firstCellSaver");
                session.removeAttribute("secondCellSaver");
            }

// PRINT INFO
            System.out.println(fromCell);
            System.out.println("1st " + firstCellSaver);
            System.out.println("2nd " + secondCellSaver);
            System.out.println("move points " + playerMovePoints);
            System.out.println("players turn " + playersTurn);
            System.out.println(session.getAttribute("playersFigure"));

            System.out.println("");


// SAVE UPDATED DESK
            for (FigureImage figureImage : chessMoves.imagesArray) {
                model.addAttribute(figureImage.getImageName(), figureImage.getPath());
            }
        for (FigureImage figureImage : chessMoves.mortaArray) {
            model.addAttribute(figureImage.getImageName(), figureImage.getPath());
        }


            return "ChessBoard";

    }
}
