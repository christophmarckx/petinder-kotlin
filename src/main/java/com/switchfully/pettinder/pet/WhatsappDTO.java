package com.switchfully.pettinder.pet;

import com.fasterxml.jackson.annotation.JsonCreator;

public class WhatsappDTO {

    private String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public WhatsappDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
