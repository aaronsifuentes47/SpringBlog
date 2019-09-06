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
        int random = (int) Math.floor(Math.random() * 20);
        int random2 = (int) Math.floor(Math.random() * 20);
        String answer = "You\'re rolling two dice! \n" +
                "You rolled: " + random + " and " + random2 + "\n" +
                "You guessed: " + this.guess;
        if (this.guess == random || this.guess == random2) {
            return answer + "\n You won!";
        } else if (this.guess != random || this.guess != random2) {
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
