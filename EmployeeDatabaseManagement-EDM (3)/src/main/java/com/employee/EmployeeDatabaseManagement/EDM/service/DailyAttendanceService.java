package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.exception.DailyAttendanceNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.DailyAttendance;
import com.employee.EmployeeDatabaseManagement.EDM.model.Department;
import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.repository.DailyAttendanceRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.DepartmentRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyAttendanceService {
    private DailyAttendanceRepository dailyAttendanceRepository;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    @Autowired
    public DailyAttendanceService(DailyAttendanceRepository dailyAttendanceRepository, DepartmentRepository departmentRepository) {
        this.dailyAttendanceRepository = dailyAttendanceRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<DailyAttendance> listAll() {
        return dailyAttendanceRepository.findAll();
    }

    public DailyAttendance save(DailyAttendance dailyAttendance) {
        return dailyAttendanceRepository.save(dailyAttendance);
    }

    public Department get(String id) {
        return departmentRepository.findById(id).orElseThrow(DailyAttendanceNoSuchElementException::new);
    }

    public DailyAttendance update(String id,DailyAttendance updatedDailyAttendance) {
      DailyAttendance daily= dailyAttendanceRepository.findAll().stream().filter(employee1 -> employee1.getEmployee().getId().equals(id) &&employee1.getDate().compareTo(updatedDailyAttendance.getDate())==0).findFirst().get();
      daily.setMark(updatedDailyAttendance.getMark());
      dailyAttendanceRepository.save(daily);
      return daily;
    }

    public void delete(String  id) {
        dailyAttendanceRepository.deleteById(id);
    }
}
