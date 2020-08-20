package com.udacity.jdnd.c3.critter.service;

import com.udacity.jdnd.c3.critter.entity.Customer;
import com.udacity.jdnd.c3.critter.entity.Employee;
import com.udacity.jdnd.c3.critter.entity.Pet;
import com.udacity.jdnd.c3.critter.entity.Schedule;
import com.udacity.jdnd.c3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule createSchedule(Schedule schedule)
    {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules()
    {
        return scheduleRepository.findAll();

    }

    public List<Schedule> getScheduleForPet(Pet pet)
    {
        return scheduleRepository.findAllByPetsContaining(pet);
    }

    public List<Schedule> getScheduleForEmployee(Employee employee)
    {
        return scheduleRepository.findAllByEmployeesContaining(employee);
    }

    public List<Schedule> getScheduleForCustomer(Customer customer)
    {
        return scheduleRepository.findAllByCustomersContaining(customer);
    }
}
