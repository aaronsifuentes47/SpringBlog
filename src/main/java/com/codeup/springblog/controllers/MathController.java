package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String doAdd(@PathVariable long num1, @PathVariable long num2) {
        return "" + num1 + " plus " + num2 + " is " + (num1 + num2) + "!";

    }
    @GetMapping("/subtract/{num1}/and/{num2}")
    @ResponseBody
    public String doSub(@PathVariable long num1, @PathVariable long num2) {
        return "" + num1 + " minus " + num2 + " is " + (num1 - num2) + "!";

    }
    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String doMult(@PathVariable long num1, @PathVariable long num2) {
        return "" + num1 + " times " + num2 + " is " + (num1 * num2) + "!";

    }
    @GetMapping("/divide/{num1}/and/{num2}")
    @ResponseBody
    public String doDiv(@PathVariable long num1, @PathVariable long num2) {
        return "" + num1 + " divided by " + num2 + " is " + (num1 / num2) + "!";

    }

}
