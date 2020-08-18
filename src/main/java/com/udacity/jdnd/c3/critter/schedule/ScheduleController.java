package com.udacity.jdnd.c3.critter.schedule;

import com.udacity.jdnd.c3.critter.entity.Pet;
import com.udacity.jdnd.c3.critter.entity.Schedule;
import com.udacity.jdnd.c3.critter.pet.PetDTO;
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

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.createSchedule(convertScheduleDTOToSchedule(scheduleDTO));
        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertSchedulesToScheduleDTOs(scheduleService.getAllSchedules());

    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return convertSchedulesToScheduleDTOs(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }

    private static Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO)
    {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
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
