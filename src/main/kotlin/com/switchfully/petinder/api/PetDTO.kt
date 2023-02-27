package com.switchfully.petinder.api

import com.switchfully.petinder.model.Kind

class PetDTO (val id: Long,
              var name: String,
              var kind: Kind,
              var image: String,
              var profileText: String,
              var popularity: Int) {
}
