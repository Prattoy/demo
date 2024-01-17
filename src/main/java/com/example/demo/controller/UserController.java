// UserController.java
package com.example.demo.controller;

import com.example.demo.model.TestUser;
import com.example.demo.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TestUserService testUserService;
    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

//    @PostMapping("/register")
//    public String registerUser(
//            @RequestParam("userName") String name,
//            @RequestParam("email") String email,
//            @RequestParam("phoneNo") String phn,
//            @RequestParam("status") String status,
//            Model model) {
//
//        // Perform registration logic here
//        // For demonstration purposes, just print the data to the console
//        System.out.println("Name: " + name);
//        System.out.println("Email: " + email);
//        System.out.println("Phone Number: " + phn);
//        System.out.println("Status: " + status);
//
//        // Add a success message to the model
//        model.addAttribute("message", "Registration successful!");
//
//        // You can redirect to a success page or show a confirmation message
//        return "registration";
//    }

    @PostMapping("/register")
    public ModelAndView saveUser(TestUser testUser, final RedirectAttributes redirectAttributes) throws IOException {

        // Print the details of the testUser object
//        System.out.println("User ID: " + testUser.getUserId());
//        System.out.println("User Name: " + testUser.getUserName());
//        System.out.println("Email: " + testUser.getEmail());
//        System.out.println("Phone Number: " + testUser.getPhoneNo());
//        System.out.println("Status: " + request.getAttribute("status"));
//        Long userId = (Long) request.getAttribute("user_id");
//        Users user = (Users) request.getAttribute("user");
//        Long BranchId = user.getBranch_id();

        TestUser oTestUser;

        // Update
        if(testUser.getUserId() != null)
        {
            oTestUser = testUserService.updateTestUser(testUser);
        } else { // Insert
            oTestUser = testUserService.createTestUser(testUser);
        }

//        redirectAttributes.addFlashAttribute("documentLot", oDocumentLOT);
        redirectAttributes.addFlashAttribute("message", oTestUser.getMessage());
        redirectAttributes.addFlashAttribute("messageCode", oTestUser.getMessageCode());

        return new ModelAndView("redirect:/user/registration");
    }

    @ResponseBody
    @GetMapping("/list")
    public List<TestUser> getTestUsers() {
        return testUserService.getAllTestUsers();
    }
    @ResponseBody
    @GetMapping("/delete")
    public Map<String, Object> deleteUser(@RequestParam Long userId) {
        System.out.println("User ID: " + userId);
        Map<String, Object> response = new HashMap<>();

        TestUser oTestUser = new TestUser();
        oTestUser.setUserId(userId);

        oTestUser = testUserService.deleteTestUser(oTestUser);

        // Add multiple messages to the response
        response.put("messageCode", oTestUser.getMessageCode());
        response.put("message", oTestUser.getMessage());

        return response;
    }
}
