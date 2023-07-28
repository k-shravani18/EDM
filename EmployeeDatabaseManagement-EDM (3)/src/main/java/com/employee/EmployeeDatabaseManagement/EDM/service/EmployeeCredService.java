package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeCred;
import com.employee.EmployeeDatabaseManagement.EDM.model.UserRole;
import com.employee.EmployeeDatabaseManagement.EDM.model.user.UserDetail;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeCredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCredService implements UserDetailsService {
    EmployeeCredRepository employeeRepository;
    DepartmentService departmentService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public EmployeeCredService(EmployeeCredRepository employeeRepository, DepartmentService departmentService, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        saveUserdetail();
//        if (employeeRepository.findAll().stream().filter(employee ->employee.getUsername().equalsIgnoreCase(username)).toList().isEmpty()){
//            throw new UsernameNotFoundException("Username Not Found");
//        }
        System.out.println(employeeRepository.findAll().stream().findFirst().get());
        EmployeeCred employeeCred=employeeRepository.findAll().stream().findFirst().get();
        System.out.println(employeeCred);
        UserDetail userdetail=new UserDetail(employeeCred);
        return userdetail;
    }
    public void saveUserdetail(){
//        EmployeeCred employeeCred=new EmployeeCred("dummy@gmail.com", passwordEncoder.encode("dummy@123"),true,true,true,true);
//        employeeRepository.save(employeeCred);
    }

    public void save(EmployeeCred employeeCred) {
        employeeRepository.save(employeeCred);
    }
}
