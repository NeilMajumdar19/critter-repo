package com.udacity.jdnd.c3.critter.service;

import com.udacity.jdnd.c3.critter.entity.Employee;
import com.udacity.jdnd.c3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.c3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long empId)
    {
        Optional<Employee> employee = employeeRepository.findById(empId);
        if(employee.isPresent())
            return employee.get();
        throw new NoSuchElementException("Employee does not exist");
    }

    public Employee saveEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Employee employee)
    {
        employee.setDaysAvailable(daysAvailable);
    }

    public List<Employee> getEmployeesByDaysAvailable(DayOfWeek day)
    {
        return employeeRepository.findAllByDaysAvailableContaining(day);
    }

    public List<Employee> getEmployeesWithoutSkill(EmployeeSkill skill)
    {
        return employeeRepository.findEmployeesWithoutSkill(skill);
    }
}
