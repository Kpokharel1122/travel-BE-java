package com.onsyatra_backend.services;

import com.onsyatra_backend.dto.ContactDto;
import com.onsyatra_backend.services.external.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    EmailService emailService;

    public void postContact(ContactDto contactDto){
        emailService.sendHtmlEmail(contactDto);
    }

}
