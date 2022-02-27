package emlakburada.controller;

import emlakburada.dto.MessageRequest;
import emlakburada.dto.response.MessageResponse;
import emlakburada.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    @PostMapping(value = "/messages")
    public ResponseEntity<?> createMessage(@RequestBody MessageRequest request) {
        messageService.saveMessage(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/messages")
    public ResponseEntity<List<MessageResponse>> getAllMessages() {
        return new ResponseEntity<>(messageService.getAllMessage(), HttpStatus.OK);
    }

    @GetMapping(value = "/messages/{title}")
    public ResponseEntity<MessageResponse> getMessageByTitle(@PathVariable(required = false) String title) {
        return new ResponseEntity<>(messageService.getMessageByTitle(title), HttpStatus.OK);
    }


}
