package com.udacity.jdnd.c3.critter.service;

import com.udacity.jdnd.c3.critter.entity.Customer;
import com.udacity.jdnd.c3.critter.entity.Pet;
import com.udacity.jdnd.c3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isPresent())
           return pet.get();
        throw new NoSuchElementException("Pet does not exist");
    }

    public List<Pet> getPets()
    {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Customer owner)
    {
        return petRepository.findByOwner(owner);
    }
}
