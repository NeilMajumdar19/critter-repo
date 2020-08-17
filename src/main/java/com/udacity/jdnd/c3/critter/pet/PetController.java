package com.udacity.jdnd.c3.critter.pet;

import com.udacity.jdnd.c3.critter.entity.Pet;
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

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        petService.savePet(convertPetDTOToPet(petDTO));
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

    private static Pet convertPetDTOToPet(PetDTO petDTO)
    {
         Pet pet = new Pet();
         BeanUtils.copyProperties(petDTO, pet);
         return pet;
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
