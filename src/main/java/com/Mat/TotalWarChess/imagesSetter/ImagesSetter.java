package com.Mat.TotalWarChess.imagesSetter;

import com.Mat.TotalWarChess.Model.ChessMoves;
import com.Mat.TotalWarChess.Model.FigureImage;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class ImagesSetter {

   public Model imagesSetter(Model model, HttpSession session) {
       ChessMoves chessMoves = (ChessMoves) session.getAttribute("chessMoves");
       if (chessMoves == null) {
           chessMoves = new ChessMoves();
           session.setAttribute("chessMoves", chessMoves);
       }

        // CREATING FIGURES

        FigureImage figureImage1_1 = new FigureImage("cell1_1", "cell1_1_img", "images/boyz.png");
        FigureImage figureImage1_2 = new FigureImage("cell1_2", "cell1_2_img", "images/boyz.png");
        FigureImage figureImage1_3 = new FigureImage("cell1_3", "cell1_3_img", "images/boyz.png");
        FigureImage figureImage1_4 = new FigureImage("cell1_4", "cell1_4_img", "images/boyz.png");
        FigureImage figureImage1_5 = new FigureImage("cell1_5", "cell1_5_img", "images/boyz.png");
        FigureImage figureImage1_6 = new FigureImage("cell1_6", "cell1_6_img", "images/boyz.png");
       FigureImage figureImage1_7 = new FigureImage("cell1_7", "cell1_7_img", "images/boyz.png");
       FigureImage figureImage1_8 = new FigureImage("cell1_8", "cell1_8_img", "images/boyz.png");
       FigureImage figureImage1_9 = new FigureImage("cell1_9", "cell1_9_img", "images/boyz.png");
       FigureImage figureImage1_10 = new FigureImage("cell1_10", "cell1_10_img", "images/boyz.png");
       FigureImage figureImage1_11 = new FigureImage("cell1_11", "cell1_11_img", "images/boyz.png");
       FigureImage figureImage1_12 = new FigureImage("cell1_12", "cell1_12_img", "images/boyz.png");
       FigureImage figureImage1_13 = new FigureImage("cell1_13", "cell1_13_img", "images/boyz.png");
       FigureImage figureImage1_14 = new FigureImage("cell1_14", "cell1_14_img", "images/boyz.png");
       FigureImage figureImage1_15 = new FigureImage("cell1_15", "cell1_15_img", "images/boyz.png");
       FigureImage figureImage1_16 = new FigureImage("cell1_16", "cell1_16_img", "images/boyz.png");
       FigureImage figureImage1_17 = new FigureImage("cell1_17", "cell1_17_img", "images/boyz.png");
       FigureImage figureImage1_18 = new FigureImage("cell1_18", "cell1_18_img", "images/boyz.png");
       FigureImage figureImage1_19 = new FigureImage("cell1_19", "cell1_19_img", "images/boyz.png");
       FigureImage figureImage1_20 = new FigureImage("cell1_20", "cell1_20_img", "images/boyz.png");
       FigureImage figureImage1_21 = new FigureImage("cell1_21", "cell1_21_img", "images/boyz.png");
       FigureImage figureImage1_22 = new FigureImage("cell1_22", "cell1_22_img", "images/boyz.png");
       FigureImage figureImage1_23 = new FigureImage("cell1_23", "cell1_23_img", "images/boyz.png");
       FigureImage figureImage1_24 = new FigureImage("cell1_24", "cell1_24_img", "images/boyz.png");

        FigureImage figureImage2_1 = new FigureImage("cell2_1", "cell2_1_img", "images/blank.png");


        // ADDITING IMAGES TO ARRAY

        chessMoves.addFigure(figureImage1_1);
        chessMoves.addFigure(figureImage1_2);
        chessMoves.addFigure(figureImage1_3);
        chessMoves.addFigure(figureImage1_4);
        chessMoves.addFigure(figureImage1_5);
        chessMoves.addFigure(figureImage1_6);
       chessMoves.addFigure(figureImage1_7);
       chessMoves.addFigure(figureImage1_8);
       chessMoves.addFigure(figureImage1_9);
       chessMoves.addFigure(figureImage1_10);
       chessMoves.addFigure(figureImage1_11);
       chessMoves.addFigure(figureImage1_12);
       chessMoves.addFigure(figureImage1_13);
       chessMoves.addFigure(figureImage1_14);
       chessMoves.addFigure(figureImage1_15);
       chessMoves.addFigure(figureImage1_16);
       chessMoves.addFigure(figureImage1_17);
       chessMoves.addFigure(figureImage1_18);
       chessMoves.addFigure(figureImage1_19);
       chessMoves.addFigure(figureImage1_20);
       chessMoves.addFigure(figureImage1_21);
       chessMoves.addFigure(figureImage1_22);
       chessMoves.addFigure(figureImage1_23);
       chessMoves.addFigure(figureImage1_24);

        chessMoves.addFigure(figureImage2_1);

        session.setAttribute("chessMoves", chessMoves);


        // ADDING TO MODEL


       model.addAttribute(figureImage1_1.getImageName(), figureImage1_1.getPath());
       model.addAttribute(figureImage1_2.getImageName(), figureImage1_2.getPath());
       model.addAttribute(figureImage1_3.getImageName(), figureImage1_3.getPath());
       model.addAttribute(figureImage1_4.getImageName(), figureImage1_4.getPath());
       model.addAttribute(figureImage1_5.getImageName(), figureImage1_5.getPath());
       model.addAttribute(figureImage1_6.getImageName(), figureImage1_6.getPath());
       model.addAttribute(figureImage1_7.getImageName(), figureImage1_7.getPath());
       model.addAttribute(figureImage1_8.getImageName(), figureImage1_8.getPath());
       model.addAttribute(figureImage1_9.getImageName(), figureImage1_9.getPath());
       model.addAttribute(figureImage1_10.getImageName(), figureImage1_10.getPath());
       model.addAttribute(figureImage1_11.getImageName(), figureImage1_11.getPath());
       model.addAttribute(figureImage1_12.getImageName(), figureImage1_12.getPath());
       model.addAttribute(figureImage1_13.getImageName(), figureImage1_13.getPath());
       model.addAttribute(figureImage1_14.getImageName(), figureImage1_14.getPath());
       model.addAttribute(figureImage1_15.getImageName(), figureImage1_15.getPath());
       model.addAttribute(figureImage1_16.getImageName(), figureImage1_16.getPath());
       model.addAttribute(figureImage1_17.getImageName(), figureImage1_17.getPath());
       model.addAttribute(figureImage1_18.getImageName(), figureImage1_18.getPath());
       model.addAttribute(figureImage1_19.getImageName(), figureImage1_19.getPath());
       model.addAttribute(figureImage1_20.getImageName(), figureImage1_20.getPath());
       model.addAttribute(figureImage1_21.getImageName(), figureImage1_21.getPath());
       model.addAttribute(figureImage1_22.getImageName(), figureImage1_22.getPath());
       model.addAttribute(figureImage1_23.getImageName(), figureImage1_23.getPath());
       model.addAttribute(figureImage1_24.getImageName(), figureImage1_24.getPath());


       model.addAttribute(figureImage2_1.getImageName(), figureImage2_1.getPath());

   return model;
   }
}