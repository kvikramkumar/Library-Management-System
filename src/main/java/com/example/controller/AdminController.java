package com.example.controller;

import com.example.model.Admin;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login.html")
    public ModelAndView loginPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @GetMapping("/signup.html")
    public ModelAndView signupPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("signup");
        return model;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Admin admin) {


        // Simulate successful response
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");

        adminService.registerAdmin(admin);
        return ResponseEntity.ok(response); // Return a JSON response
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> verifyUser(@RequestBody Admin admin) {

        // Simulate successful response
        Map<String, String> response = new HashMap<>();


        boolean isValid = adminService.verifyAdmin(admin);
        if(isValid){
            response.put("message", "User Logged in successfully");

            return ResponseEntity.ok(response);
        }
        else{
            response.put("message", "Invalid Credentials");
            return ResponseEntity.ok(response);
        }
         // Return a JSON response
    }

    @PostMapping("/logout")
    public ModelAndView logout(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @GetMapping("/dashboard.html")
    public ModelAndView dashboard(){
        ModelAndView model = new ModelAndView();
        model.setViewName("dashboard");
        return model;
    }

}
