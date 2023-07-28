package com.employee.EmployeeDatabaseManagement.EDM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class RegisterLoginController {
    @GetMapping("/login")
    public String getLogin(){
        return "index";
    }
    @GetMapping("/dashboard")
    public String getDashboard(){return "dept-dash";
    }

}
