package com.employee.EmployeeDatabaseManagement.EDM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr-api")
public class HrController {

    @GetMapping("/department")
    public String panel(Model model) {
        return "department";
    }
    @GetMapping("/emp_info")
    public String employeeInfo(Model model) {
        return "employee_info";
    }
    @GetMapping("/emp_record")
    public String employeeRecord(Model model) {
        return "employee_record";
    }
    @GetMapping("/employee_cred")
    public String employee_cred(Model model) {
        return "add_credentials";
    }
    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }
    @GetMapping("/emp_view")
    public String emp_view(Model model) {
        return "employee_view";
    }
    @GetMapping("/dept_view")
    public String dept_view(Model model) {
        return "department_view";
    }
    @GetMapping("/att_view")
    public String att_view(Model model) {
        return "attendance_view";
    }
    @GetMapping("/add_attendance")
    public String add_attendance(Model model) {
        return "add_attendance";
    }
    @GetMapping("/add_dept")
    public String add_department(Model model) {
        return "add_department";
    }

    @GetMapping("/apply_leave")
    public String leaveApply(Model model) {
        return "leave_apply";
    }
}
