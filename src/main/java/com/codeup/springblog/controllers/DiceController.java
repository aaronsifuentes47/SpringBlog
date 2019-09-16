package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DiceController {

    private int guess;


    @GetMapping("rollDice")
    public String mainPage() {
        return "dice";
    }

    @PostMapping("/dice")
    public String redirect(@RequestParam(name = "guess") int guess, @RequestParam(name = "dice") int dice, Model model){
        model.addAttribute("numOfDice", dice);
        model.addAttribute("guess", guess);
        this.guess = guess;
        return "redirect:/roll-dice/" + dice;


    }

    @GetMapping("/roll-dice/1")
    @ResponseBody
    public String rollOne(){
        int random = (int) Math.floor(Math.random() * 20);
        String answer = "You\'re rolling one die! \n" +
                "You rolled: " + random +
                "\nYou guessed: " + this.guess;
        if (this.guess == random) {
            return answer + "\n You won!";
        } else if (this.guess != random) {
            return answer + "\n You lost! Nice!";
        } else {
            return "why are you here?";
        }

    }

    @GetMapping("/roll-dice/2")
    @ResponseBody
    public String rollTwo(){
        int[] rolls = new int[2];
        int random = (int) Math.floor(Math.random() * 20);
        for (int i = 0; i <= 1; i++) {
            rolls[i] = random;
        }
        String answer = "You\'re rolling two dice! \n" +
                "You rolled: " + rolls[0] + " and " + rolls[1] + "\n" +
                "You guessed: " + this.guess;
        if (this.guess == rolls[0] || this.guess == rolls[1] ) {
            return answer + "\n You won!";
        } else if (this.guess != rolls[0] || this.guess != rolls[1]) {
            return answer + "\n You lost! Nice!";
        } else {
            return "why are you here?";
        }

    }
//    @GetMapping("/roll-dice/3")
//    @ResponseBody
//    public String rollThree(){
//        int random = (int) Math.floor(Math.random() * 20);
//        String answer = "You\'re rolling one die! \n" +
//                "You rolled: " + random + "!";
//        return answer;
//    }
//    @GetMapping("/roll-dice/4")
//    @ResponseBody
//    public String rollFour(){
//
//    }
//    @GetMapping("/roll-dice/5")
//    @ResponseBody
//    public String rollFive(){
//
//    }
//    @GetMapping("/roll-dice/6")
//    @ResponseBody
//    public String rollSix(){
//
//    }
//
}
