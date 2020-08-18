package com.udacity.jdnd.c3.critter.schedule;

import com.udacity.jdnd.c3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    public List<Schedule> findAllByPetIdsContaining(Long petId);
}
