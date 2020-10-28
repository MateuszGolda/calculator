package com.codecool.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "calculator")
public class CalculatorController {
    @RequestMapping(method = RequestMethod.GET)
    String displayForm() {
        return "calculator/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    String submitForm(Model model,
                      @RequestParam String input1,
                      @RequestParam String input2,
                      @RequestParam String operation) {
        String result;
        try {
            result = getResult(input1, input2, operation);
        } catch (NumberFormatException e) {
            result = "Provided invalid number. Must be type Integer";
            e.printStackTrace();
        } catch (ArithmeticException e) {
            result = "You can't divide by zero";
            e.printStackTrace();
        }
        model.addAttribute("result", result);
        return "calculator/index";
    }

    private String getResult(String input1, String input2, String operation) {
        int n1 = Integer.parseInt(input1);
        int n2 = Integer.parseInt(input2);

        String equation = String.format("%s %s %s = ", input1, operation, input2);

        return switch (operation) {
            case "+" -> equation + (n1 + n2);
            case "-" -> equation + (n1 - n2);
            case "*" -> equation + (n1 * n2);
            case "/" -> equation + (n1 / n2);
            case "%" -> equation + (n1 % n2);
            case "&" -> equation + (n1 & n2);
            case "|" -> equation + (n1 | n2);
            case "^" -> equation + (n1 ^ n2);
            case "<<" -> equation + (n1 << n2);
            case ">>" -> equation + (n1 >> n2);
            case ">>>" -> equation + (n1 >>> n2);
            default -> "Unsupported operation";
        };
    }
}
