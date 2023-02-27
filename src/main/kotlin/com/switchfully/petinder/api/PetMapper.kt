package com.switchfully.petinder.api

import com.switchfully.petinder.model.Pet
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class PetMapper {
  fun toDTO(pet: Pet): PetDTO {
    return PetDTO(pet.id, pet.name, pet.kind, pet.image, pet.profileText, pet.popularity)
  }

  fun toDTO(petList: List<Pet>): MutableList<PetDTO> {
    return petList.stream().map(this::toDTO).collect(Collectors.toList())
  }

  fun toEntity(petDTO: PetDTO): Pet {
    return Pet(petDTO.id, petDTO.name, petDTO.kind, petDTO.image, petDTO.profileText, petDTO.popularity)
  }
}
