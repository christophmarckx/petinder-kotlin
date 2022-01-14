package com.switchfully.pettinder.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "messages")
@CrossOrigin(origins = {"http://localhost:4200","https://petinder-deploy-test.netlify.app/"})
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(produces = "application/json")
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @DeleteMapping(path = "{messageId}")
    public MessageDTO getMessage(@PathVariable Long messageId) {
        return messageService.deleteMessage(messageId);
    }

    @PostMapping(consumes = "application/json")
    public void addMessage(@RequestBody MessageDTO messageDTO) {
        messageService.addMessage(messageDTO);
    }
}
