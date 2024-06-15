package com.project.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam double firstNumber,
            @RequestParam double secondNumber,
            @RequestParam String operation,
            Model model) {
        double result = 0;
        String error = null;

        try {
            result = calculatorService.calculate(firstNumber, secondNumber, operation);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        model.addAttribute("result", result);
        model.addAttribute("error", error);
        return "index";
    }
       
}