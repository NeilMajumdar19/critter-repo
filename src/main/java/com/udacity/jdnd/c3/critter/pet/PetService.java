package com.udacity.jdnd.c3.critter.pet;

import com.udacity.jdnd.c3.critter.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet savePet(Pet pet)
    {
        return petRepository.save(pet);
    }

    public Pet getPetById(Long petId)
    {
        return petRepository.findById(petId).get();
    }

    public List<Pet> getPets()
    {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long ownerId)
    {
        return petRepository.findByOwnerId(ownerId);
    }
}
