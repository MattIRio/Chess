package com.Mat.TotalWarChess.imagesSetter;

import com.Mat.TotalWarChess.Model.ChessMoves;
import com.Mat.TotalWarChess.Model.FigureImage;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class ImagesSetter {

   public Model imagesSetter(Model model, HttpSession session) {
        ChessMoves chessMoves = new ChessMoves();

        FigureImage figureImage1_1 = new FigureImage("cell1_1", "paw_w_img", "images/pawn-w.png");
        FigureImage figureImage2_1 = new FigureImage("cell2_1", "cell2_1_img", "images/blank.png");
        chessMoves.addFigure(figureImage1_1);
        chessMoves.addFigure(figureImage2_1);

        session.setAttribute("chessMoves", chessMoves);

       model.addAttribute(figureImage1_1.getImageName(), figureImage1_1.getPath());
       model.addAttribute(figureImage2_1.getImageName(), figureImage2_1.getPath());
   return model;
   }
}