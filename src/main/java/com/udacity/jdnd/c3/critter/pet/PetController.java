package com.udacity.jdnd.c3.critter.pet;

import com.udacity.jdnd.c3.critter.entity.Customer;
import com.udacity.jdnd.c3.critter.entity.Pet;
import com.udacity.jdnd.c3.critter.service.CustomerService;
import com.udacity.jdnd.c3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer owner = customerService.getCustomerById(petDTO.getOwnerId());
        pet.setOwner(owner);
        if(owner.getPets() == null)
            owner.setPets(new ArrayList<>());
        owner.getPets().add(pet);
        petService.savePet(pet);
        return convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDTO(petService.getPetById(petId));

    }

    @GetMapping
    public List<PetDTO> getPets(){
        return convertPetsToPetDTOs(petService.getPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertPetsToPetDTOs(petService.getPetsByOwner(customerService.getCustomerById(ownerId)));
    }

    private static PetDTO convertPetToPetDTO(Pet pet)
    {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    private static List<PetDTO> convertPetsToPetDTOs(List<Pet> pets)
    {
        List<PetDTO> petDTOs = new ArrayList<PetDTO>();
        PetDTO petDTO;
        for(Pet p : pets)
        {
            petDTO = convertPetToPetDTO(p);
            petDTOs.add(petDTO);
        }

        return petDTOs;
    }
}
