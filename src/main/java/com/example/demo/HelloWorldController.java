package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(Model model) {
        System.out.printf("Hello!!!");
        model.addAttribute("name", "Prattoy");
        return "hello"; // This corresponds to hello.jsp
    }
}
