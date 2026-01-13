package com.onsyatra_backend.controller;

import com.onsyatra_backend.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.onsyatra_backend.dto.ContactDto;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/submitContact")
    public ResponseEntity<?> postContact(@RequestBody ContactDto contactDto){
        contactService.postContact(contactDto);
        return ResponseEntity.ok("Contact Form submitted successfully");
    }


}
