package com.Mat.TotalWarChess.imagesSetter;

import com.Mat.TotalWarChess.Model.ChessMoves;
import com.Mat.TotalWarChess.Model.FigureImage;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class ImagesSetter {

   public Model imagesSetter(Model model, HttpSession session) {
        ChessMoves chessMoves = new ChessMoves();

        // CREATING FIGURES

        FigureImage figureImage1_1 = new FigureImage("cell1_1", "cell1_1_img", "images/pawn-w.png");
        FigureImage figureImage1_2 = new FigureImage("cell1_2", "cell1_2_img", "images/pawn-w.png");
        FigureImage figureImage1_3 = new FigureImage("cell1_3", "cell1_3_img", "images/pawn-w.png");
        FigureImage figureImage1_4 = new FigureImage("cell1_4", "cell1_4_img", "images/pawn-w.png");
        FigureImage figureImage1_5 = new FigureImage("cell1_5", "cell1_5_img", "images/pawn-w.png");
        FigureImage figureImage1_6 = new FigureImage("cell1_6", "cell1_6_img", "images/pawn-w.png");

        FigureImage figureImage2_1 = new FigureImage("cell2_1", "cell2_1_img", "images/blank.png");


        // ADDITING IMAGES TO ARRAY

        chessMoves.addFigure(figureImage1_1);
        chessMoves.addFigure(figureImage1_2);
        chessMoves.addFigure(figureImage1_3);
        chessMoves.addFigure(figureImage1_4);
        chessMoves.addFigure(figureImage1_5);
        chessMoves.addFigure(figureImage1_6);

        chessMoves.addFigure(figureImage2_1);

        session.setAttribute("chessMoves", chessMoves);


        // ADDING TO MODEL


       model.addAttribute(figureImage1_1.getImageName(), figureImage1_1.getPath());
       model.addAttribute(figureImage1_2.getImageName(), figureImage1_2.getPath());
       model.addAttribute(figureImage1_3.getImageName(), figureImage1_3.getPath());
       model.addAttribute(figureImage1_4.getImageName(), figureImage1_4.getPath());
       model.addAttribute(figureImage1_5.getImageName(), figureImage1_5.getPath());
       model.addAttribute(figureImage1_6.getImageName(), figureImage1_6.getPath());

       model.addAttribute(figureImage2_1.getImageName(), figureImage2_1.getPath());

   return model;
   }
}