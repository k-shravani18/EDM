package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.exception.DailyAttendanceNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.Department;
import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeCred;
import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeInfo;
import com.employee.EmployeeDatabaseManagement.EDM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/emp-api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/dashboard")
    public String login(Model model) {
        model.addAttribute("loginForm", new EmployeeCred());
        return "employee_dashboard";
    }
    @GetMapping("/emp_view_dept")
    public String emp_view(Model model) {
        return "emp_dept_view";
    }

    @GetMapping("/dept_view")
    public String dept_view(Model model) {
        return "/dept_view";
    }
    @GetMapping("/attendance_view")
    public String attendance_view(Model model) {
        return "/attendance_view";
    }
    @GetMapping("/apply_leave")
    public String leaveApply(Model model) {
        return "leave_apply";
    }

    @GetMapping("/retrieve")
    public List<Employee> list() {
        return employeeService.listAll();
    }
    @GetMapping("/employee-create")
    public String registerEmployee( @ModelAttribute Department department, Model model){
            Employee employee1=new Employee();
            employee1.setId(employeeService.getId(department));
            model.addAttribute("employee",new Employee());
            return "add-employee";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute Employee employee, Model model) {

        employeeService.save(employee);
        model.addAttribute("employee",employee);
        return  "redirect:/employeeinfo/create";
    }

    @GetMapping("/retrieve/{id}")
    public String get(@ModelAttribute String id,Model model) {
        model.addAttribute("employee",employeeService.get(id));
        return "page";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            employeeService.delete(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (DailyAttendanceNoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
