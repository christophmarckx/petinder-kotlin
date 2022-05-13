package com.switchfully.pettinder.pet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Value("${twilio.account.sid}")
    private String accountSid;
    @Value("${twilio.auth.token}")
    private String authToken;
    @Value("${whatsapp.telephone.number.to}")
    private String whatsAppTelephoneNumberTo;
    @Value("${whatsapp.telephone.number.from}")
    private String whatsAppTelephoneNumberFrom;

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetService(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    public List<PetDTO> getAllPets() {
        return petMapper.toDTO(petRepository.findAll());
    }

    public PetDTO getPet(String name) {
        return petMapper.toDTO(petRepository.findByName(name));
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public void increasePopularity(String name) {
        petRepository.findByName(name).increasePopularity();
    }

    public void save(PetDTO petDTO) {
        petRepository.save(petMapper.toEntity(petDTO));
    }

    public void sendWhatsApp(String name) {
         Twilio.init(accountSid, authToken);
        whatsAppTelephoneNumberFrom = "whatsapp:+14155238886";
        Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(whatsAppTelephoneNumberTo),
                    new com.twilio.type.PhoneNumber(whatsAppTelephoneNumberFrom),
                    name + " just got a date with your pet!")
                    .create();

            System.out.println(message.getSid());
        }
    }
