package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.exception.EmployeeInfoNoSuchException;
import com.employee.EmployeeDatabaseManagement.EDM.model.Department;
import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.model.StringIDGenerator;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.IDGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private IDGeneratorRepository idGeneratorRepository;
    @Autowired
    DepartmentService departmentService;

    @Autowired
    private EmailService emailService;

    public List<Employee> listAll() {

        return employeeRepository.findAll();
    }
    public String getId(Department department){
        if (idGeneratorRepository.findAll().isEmpty()){
            idGeneratorRepository.save(new StringIDGenerator(10001,10001));
            return "PD-DEPT-1001";
        }
        String id="PD"+department.getDepartment().substring(0,1).toUpperCase()+idGeneratorRepository.findAll().get(0).getDepartment();
        return id;
    }

    public Employee save(Employee employee) {
        StringIDGenerator id =idGeneratorRepository.findAll().stream().findFirst().get();
        id.setDepartment(id.getEmployee()+1);
        idGeneratorRepository.save(id);
        return employeeRepository.save(employee);
    }

    public Employee get(String id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeInfoNoSuchException::new);
    }

    public Employee update(String id, Employee updateEmployee) {
        Employee existingEmployee = get(id);
        existingEmployee.setId(updateEmployee.getId());
        existingEmployee.setName(updateEmployee.getName());
        existingEmployee.setEmail(updateEmployee.getEmail());
        existingEmployee.setNumber(updateEmployee.getNumber());
        existingEmployee.setDOJ(updateEmployee.getDOJ());
        existingEmployee.setDOL(updateEmployee.getDOL());
        existingEmployee.setAadharNo(updateEmployee.getAadharNo());
        existingEmployee.setPanNo(updateEmployee.getPanNo());

        return employeeRepository.save(existingEmployee);
    }

    public void delete(Long id) {

        employeeRepository.deleteById(String.valueOf(id));
    }
    public void addEmployee(Employee employee) {
        // Save the new employee to the database
        Employee savedEmployee = employeeRepository.save(employee);

        // Send confirmation email to the employee
        emailService.sendConfirmationEmail(savedEmployee.getEmail(), savedEmployee.getName());

        // Send confirmation email to the manager
        Employee manager = savedEmployee.getManager();
        // Send confirmation email to team members
    }
}


