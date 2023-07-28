package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.model.DailyAttendance;
import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.service.DailyAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attendance")
public class DailyAttendanceController {
    @Autowired
    private DailyAttendanceService dailyAttendanceService;

    @GetMapping("/retrieve")
    public String list(@ModelAttribute Employee employee, Model model) {
         model.addAttribute("AttendenceList",dailyAttendanceService.listAll());
         return "page";
    }

    @PostMapping("/create")
    public DailyAttendance add(@ModelAttribute DailyAttendance dailyAttendance) {
        return dailyAttendanceService.save(dailyAttendance);
    }

    @GetMapping("/update")
    public String update( @ModelAttribute DailyAttendance dailyAttendance) {
        dailyAttendanceService.update(dailyAttendance.getEmployee().getId(), dailyAttendance);
        return "page";
    }
}
