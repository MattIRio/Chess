package com.Mat.TotalWarChess.Controller;


import com.Mat.TotalWarChess.Model.ChessMoves;
import com.Mat.TotalWarChess.Model.FigureImage;
import com.Mat.TotalWarChess.imagesSetter.ImagesSetter;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

        model.addAttribute("players_turn_image", "images/Orcs.png");

        ImagesSetter imagesSetter = new ImagesSetter();
        imagesSetter.imagesSetter(model, session);

        String username = (String) session.getAttribute("username");
        String secondUsername = (String) session.getAttribute("secondUsername");

        model.addAttribute("username", username);
        model.addAttribute("secondUsername", secondUsername);
        model.addAttribute("playerss_turn", session.getAttribute("username"));

        return "ChessBoard";
    }



    // POST MAPPING

    @PostMapping("/chessboard")
    public String movePiece(@RequestParam(value = "currentCell") String fromCell,
                            @RequestParam(value = "secondCurrentCell", required = false) String toCell,
                            HttpSession session, Model model) {


        Integer playerMovePoints = (Integer) session.getAttribute("playerMovePoints");
        Integer playersTurn = (Integer) session.getAttribute("playersTurn");
        String playersFigure = (String) session.getAttribute("playersFigure");

        if (playersFigure == null) {
            playersFigure = "";
        }
        if (playerMovePoints == null) {
            playerMovePoints = 0;
        }
        if (playersTurn == null) {
            playersTurn = 1;
        }


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

        // FIRST PLAYER LOGIC
            if (fromCell != null && playersTurn == 1)  {

                //FROM CELL

                if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isFirstPlayerTurn(fromCell) == true) {
                    firstCellSaver = fromCell;
                    session.setAttribute("playersFigure",chessMoves.figureChecker(firstCellSaver));
                    chessMoves.hightlightTile(firstCellSaver);
                    session.setAttribute("firstCellSaver", firstCellSaver);

                //TO CELL

                } else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.PawnLogic(firstCellSaver, fromCell) == true) {
                    secondCellSaver = fromCell;
                    chessMoves.unHightlightTile();
                    session.setAttribute("secondCellSaver", secondCellSaver);
                }

                if (!firstCellSaver.isEmpty() && !secondCellSaver.isEmpty()) {
                    chessMoves.swapTiles(firstCellSaver, secondCellSaver);
                    playerMovePoints++;
                    firstCellSaver = "";
                    secondCellSaver = "";
                    session.setAttribute("secondCellSaver", secondCellSaver);
                    session.setAttribute("firstCellSaver", firstCellSaver);
                    session.setAttribute("playerMovePoints", playerMovePoints);
                }
            }

        // SECOND PLAYER LOGIC
        if (fromCell != null && playersTurn == 2)  {
            if (firstCellSaver.isEmpty() && chessMoves.isTileEmpty(fromCell) != true && chessMoves.isSecondPlayerTurn(fromCell) == true) {
                firstCellSaver = fromCell;
                chessMoves.hightlightTile(firstCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);

            } else if (secondCellSaver.isEmpty() && !firstCellSaver.isEmpty() && chessMoves.isSecondPlayerTurn(fromCell) == true) {
                secondCellSaver = fromCell;
                chessMoves.unHightlightTile();
                session.setAttribute("secondCellSaver", secondCellSaver);
            }
            if (!firstCellSaver.isEmpty() && !secondCellSaver.isEmpty()) {
                chessMoves.swapTiles(firstCellSaver, secondCellSaver);
                firstCellSaver = "";
                secondCellSaver = "";
                session.setAttribute("secondCellSaver", secondCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
                playerMovePoints++;
                session.setAttribute("playerMovePoints", playerMovePoints);
            }
        }






        if (playerMovePoints == 2 && playersTurn == 1){
            playerMovePoints = 0;
            playersTurn = 2;

        } else if(playerMovePoints == 2 && playersTurn == 2){
            playerMovePoints = 0;
            playersTurn = 1;

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


            return "ChessBoard";

    }
}
