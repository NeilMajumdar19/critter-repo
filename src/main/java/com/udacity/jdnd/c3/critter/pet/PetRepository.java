package com.udacity.jdnd.c3.critter.pet;

import com.udacity.jdnd.c3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {


    public List<Pet> findByOwnerId(Long ownerId);


}
