package com.udacity.jdnd.c3.critter.pet;

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
        pet.setOwner(customerService.getOwnerById(petDTO.getOwnerId()));
        petService.savePet(pet);
        return petDTO;
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
        return convertPetsToPetDTOs(petService.getPetsByOwner(ownerId));
    }

    private static PetDTO convertPetToPetDTO(Pet pet)
    {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }

    private static List<PetDTO> convertPetsToPetDTOs(List<Pet> pets)
    {
        List<PetDTO> petDTOs = new ArrayList<PetDTO>();
        for(Pet p : pets)
            petDTOs.add(convertPetToPetDTO(p));
        return petDTOs;
    }
}
