package com.udacity.jdnd.c3.critter.service;

import com.udacity.jdnd.c3.critter.entity.Employee;
import com.udacity.jdnd.c3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long empId)
    {
        return employeeRepository.findById(empId).get();
    }
}
