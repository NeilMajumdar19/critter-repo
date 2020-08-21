package com.udacity.jdnd.c3.critter.repository;

import com.udacity.jdnd.c3.critter.entity.Employee;
import com.udacity.jdnd.c3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public List<Employee> findAllByDaysAvailableContaining(DayOfWeek day);

    @Query("select e from Employee e where :skill not member of e.skills")
    public List<Employee> findEmployeesWithoutSkill(EmployeeSkill skill);
}
