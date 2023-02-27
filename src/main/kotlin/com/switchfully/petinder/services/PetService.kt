package com.switchfully.petinder.services

import com.switchfully.petinder.api.PetDTO
import com.switchfully.petinder.api.PetMapper
import com.switchfully.petinder.model.Pet
import com.switchfully.petinder.repository.PetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PetService @Autowired constructor(val petRepository: PetRepository, val petMapper: PetMapper) {


  fun getAllPets(): MutableList<PetDTO> {
    return petMapper.toDTO(petRepository.findAll())
  }
}
