// UserController.java
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phn") String phn,
            @RequestParam("password") String password,
            Model model) {

        // Perform registration logic here
        // For demonstration purposes, just print the data to the console
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phn);
        System.out.println("Password: " + password);

        // Add a success message to the model
        model.addAttribute("message", "Registration successful!");

        // You can redirect to a success page or show a confirmation message
        return "registration";
    }
}
