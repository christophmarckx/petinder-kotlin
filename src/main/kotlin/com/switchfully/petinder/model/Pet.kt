package com.switchfully.petinder.model

import jakarta.persistence.*

@Entity
@Table(name = "PET")
class Pet (
  @Id @SequenceGenerator(name = "pet_seq", sequenceName = "pet_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq") val id: Long,
  var name: String,
  @Enumerated(EnumType.STRING) var kind: Kind,
  var image: String,
  var profileText: String,
  var popularity: Int
) {


}
