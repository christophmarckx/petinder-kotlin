package com.switchfully.petinder.api

import com.switchfully.petinder.services.PetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PetController @Autowired constructor(val petService: PetService) {

  @GetMapping("/")
  fun getAllpets() = petService.getAllPets()
}
