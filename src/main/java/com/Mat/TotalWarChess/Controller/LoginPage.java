package com.Mat.TotalWarChess.Controller;

import com.Mat.TotalWarChess.Model.LoginModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginPage {
    @RequestMapping("/login")
    public String LoginPage(Model model){
        model.addAttribute("loginModel", new LoginModel());
        return "loginPage";

    }

    @PostMapping("/processLogin")
    public String processLogin(@Valid @ModelAttribute("loginModel")LoginModel loginModel, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginModel", loginModel);
            return "loginPage";
        }
        model.addAttribute("loginModel", loginModel);
        redirectAttributes.addFlashAttribute("username", loginModel.getUsername());
        redirectAttributes.addFlashAttribute("secondUsername", loginModel.getSecondUsername());
        return "redirect:/chessboard";
    }
}
