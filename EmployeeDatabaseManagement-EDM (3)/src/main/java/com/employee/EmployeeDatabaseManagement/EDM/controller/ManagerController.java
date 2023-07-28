package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeCred;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager-api")
public class ManagerController {

    @GetMapping("/dashboard")
    public String login(Model model) {
        return "manager_dashboard";
    }

    @GetMapping("/emp_view")
    public String emp_view(Model model) {
        return "emp_dept_view";
    }
    @GetMapping("/dept_view")
    public String dept_view(Model model) {
        return "dept_view";
    }
    @GetMapping("/att_view")
    public String att_view(Model model) {
        return "attendance_view";
    }
}
