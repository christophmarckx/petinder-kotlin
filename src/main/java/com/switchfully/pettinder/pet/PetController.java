package com.switchfully.pettinder.pet;

import net.bytebuddy.pool.TypePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(value = "pets")
public class PetController {

    private final PetService petService;
    public static final Logger logger = LoggerFactory.getLogger(PetController.class);

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping(produces = "application/json")
    public List<PetDTO> getAllPets() {
        return petService.getAllPets();
    }

    // example using ResponseStatusException - This is Exception handling on a method level
    @GetMapping(path = "{name}", produces = "application/json")
    public PetDTO getPet(@PathVariable String name) {
        try {
            return petService.getPet(name);
        }
        catch (NullPointerException nullPointerException) {
            throw new ResponseStatusException(BAD_REQUEST, "The provided username " + name + " does not exist.", nullPointerException);
        }
    }

    @GetMapping(path = "{name}/incrementPopularity", produces = "application/json")
    public void increasePopularity(@PathVariable String name) {
        petService.increasePopularity(name);
    }

    @PostMapping(consumes = "application/json")
    public void addPet(@RequestBody PetDTO petDTO){
        logger.info("Pet created");
        petService.save(petDTO);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Map<String, Boolean> deletePet(@PathVariable(value = "id") Long id) {
        petService.deletePet(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping(path = "/sendText")
    public void sendWhatsApp(@RequestBody WhatsappDTO whatsappDTO) {
        logger.info("Text sent");
        petService.sendWhatsApp(whatsappDTO.getName());
    }

    // This is Exception handling for every method in *this* specific controller, for *this* specific Exception
    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentException(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
      response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }
}
