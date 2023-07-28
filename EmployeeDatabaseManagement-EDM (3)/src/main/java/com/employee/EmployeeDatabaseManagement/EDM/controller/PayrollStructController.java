package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.exception.PayRollStructNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.Department;
import com.employee.EmployeeDatabaseManagement.EDM.model.PayrollStruct;
import com.employee.EmployeeDatabaseManagement.EDM.repository.DepartmentRepository;
import com.employee.EmployeeDatabaseManagement.EDM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app")
public class PayrollStructController {
    DepartmentRepository departmentRepository;
    DepartmentService departmentService;
    @Autowired

    public PayrollStructController(DepartmentRepository departmentRepository,  DepartmentService departmentService) {
        this.departmentRepository = departmentRepository;
        this.departmentService = departmentService;
    }

    @GetMapping("/retrieve")
    public String list() {
        return "page";
    }

    @GetMapping("/create-department")
    public String add(Model model) {
        Department department=new Department();
        department.setId(departmentService.getId());
        System.out.println(departmentService.getId());
        model.addAttribute("department",department);
//        departmentService.savePayroll(payrollStruct);
        return "department";
    }
    @PostMapping("/department")
    public String addDepartment(@ModelAttribute Department department,Model model) {
        departmentService.save(department);
        PayrollStruct payrollStruct1=new PayrollStruct();
        payrollStruct1.setId(department.getId());
        model.addAttribute("payrollstruct",payrollStruct1);
        return "payroll-struct";
    }
    @PostMapping("/salary")
    public String addSalary(@ModelAttribute PayrollStruct payrollStruct) {
        String id=payrollStruct.getId();
        System.out.println(id);
        payrollStruct.setId(null);
        System.out.println(payrollStruct);
        departmentService.setPayrollStruct(id,payrollStruct);
        return "redirect:/app/dashboard";
    }

    @GetMapping("/retrieve/{id}")
    public String get(@ModelAttribute Department department) {
        departmentService.getPayrollStruct(department);
        return "page";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            return ResponseEntity.noContent().build();
        } catch (PayRollStructNoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
