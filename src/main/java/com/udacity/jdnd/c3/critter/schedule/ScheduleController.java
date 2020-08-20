package com.udacity.jdnd.c3.critter.schedule;

import com.udacity.jdnd.c3.critter.entity.Employee;
import com.udacity.jdnd.c3.critter.entity.Pet;
import com.udacity.jdnd.c3.critter.entity.Schedule;
import com.udacity.jdnd.c3.critter.service.CustomerService;
import com.udacity.jdnd.c3.critter.service.EmployeeService;
import com.udacity.jdnd.c3.critter.service.PetService;
import com.udacity.jdnd.c3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Employee> employees = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();
        for(Long empId : scheduleDTO.getEmployeeIds())
            employees.add(employeeService.getEmployeeById(empId));
        for(Long petId: scheduleDTO.getPetIds())
            pets.add(petService.getPetById(petId));
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        scheduleService.createSchedule(schedule);
        return scheduleDTO;



    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertSchedulesToScheduleDTOs(scheduleService.getAllSchedules());

    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return convertSchedulesToScheduleDTOs(scheduleService.getScheduleForPet(petService.getPetById(petId)));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return convertSchedulesToScheduleDTOs(scheduleService.getScheduleForEmployee(employeeService.getEmployeeById(employeeId)));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return convertSchedulesToScheduleDTOs(scheduleService.getScheduleForCustomer(customerService.getOwnerById(customerId)));
    }

    private static ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule)
    {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        return scheduleDTO;
    }

    private static List<ScheduleDTO> convertSchedulesToScheduleDTOs(List<Schedule> schedules)
    {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
        for(Schedule s : schedules)
            scheduleDTOs.add(convertScheduleToScheduleDTO(s));
        return scheduleDTOs;
    }


}
