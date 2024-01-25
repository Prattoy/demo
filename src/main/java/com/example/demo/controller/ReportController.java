package com.example.demo.controller;


import com.example.demo.model.LookupModel;
import com.example.demo.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping(value = "/reports")
public class ReportController {

/*    @Autowired
    private ReportService reportService;*/

    @Autowired
    private LookupService lookupService;

    @GetMapping("/registration-report")
    public ModelAndView registrationReport(LookupModel lookupModel) {
        System.out.printf("report method");
        ModelAndView modelAndView = new ModelAndView("reportExample");

        modelAndView.addObject("formName", "Registration Information");
        modelAndView.addObject("branchList", lookupService.branchList());

        return modelAndView;
    }
}
