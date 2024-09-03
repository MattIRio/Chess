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

    @GetMapping("/chessboard")
    public String chessboard(Model model, HttpSession session) {
        ImagesSetter imagesSetter = new ImagesSetter();
        imagesSetter.imagesSetter(model, session);

        String username = (String) session.getAttribute("username");
        String secondUsername = (String) session.getAttribute("secondUsername");

        model.addAttribute("username", username);
        model.addAttribute("secondUsername", secondUsername);

//        model.addAttribute("paw_w_img", "images/pawn-w.png");
        return "ChessBoard";
    }

    @PostMapping("/chessboard")
    public String movePiece(@RequestParam(value = "currentCell") String fromCell,
                            @RequestParam(value = "secondCurrentCell", required = false) String toCell,
                            HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        String secondUsername = (String) session.getAttribute("secondUsername");

        model.addAttribute("username", username);
        model.addAttribute("secondUsername", secondUsername);

        ImagesSetter imagesSetter = new ImagesSetter();
        imagesSetter.imagesSetter(model,session);

        String firstCellSaver = (String) session.getAttribute("firstCellSaver");
        String secondCellSaver = (String) session.getAttribute("secondCellSaver");


        if (firstCellSaver == null) {
            firstCellSaver = "";
        }
        if (secondCellSaver == null) {
            secondCellSaver = "";
        }



        if (fromCell != null) {
            if (firstCellSaver.isEmpty()) {
                firstCellSaver = fromCell;
                session.setAttribute("firstCellSaver", firstCellSaver);
            } else if (secondCellSaver.isEmpty()) {
                secondCellSaver = fromCell;
                session.setAttribute("secondCellSaver", secondCellSaver);
            } else if(!firstCellSaver.isEmpty() && !secondCellSaver.isEmpty()){
                chessMoves.swapTiles(firstCellSaver, secondCellSaver);
                firstCellSaver = "";
                secondCellSaver = "";
                session.setAttribute("secondCellSaver", secondCellSaver);
                session.setAttribute("firstCellSaver", firstCellSaver);
            }
        }





// BOTH VALUES TO NULL
        if (fromCell.equals("cell1_24")){
            firstCellSaver = "";
            secondCellSaver = "";
            session.removeAttribute("firstCellSaver");
            session.removeAttribute("secondCellSaver");
        }


        System.out.println(fromCell);
        System.out.println("1st " + firstCellSaver);
        System.out.println("2nd " + secondCellSaver);
        System.out.println("");








            return "ChessBoard";

    }
}
