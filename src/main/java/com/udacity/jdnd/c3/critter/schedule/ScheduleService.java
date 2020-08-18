package com.udacity.jdnd.c3.critter.schedule;

import com.udacity.jdnd.c3.critter.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public List<Schedule> getScheduleForPet(Long petId)
    {
        return scheduleRepository.findAllByPetIdsContaining(petId);
    }
}
