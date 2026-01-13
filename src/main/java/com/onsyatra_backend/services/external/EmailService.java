package com.onsyatra_backend.services.external;

import com.onsyatra_backend.dto.ContactDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(ContactDto contactDto){
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo("krishnapokharel.2022@gmail.com");
//            info@onsyatra.com
            helper.setSubject("Contact Form Enquiries of ONS Yatra");
            helper.setFrom("krishnapokharel.2022@gmail.com");

            String htmlContent = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "  <meta charset='UTF-8'>" +
                    "  <style>" +
                    "    body { font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; }" +
                    "    .container { background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }" +
                    "    h2 { color: #333333; }" +
                    "    p { color: #555555; line-height: 1.5; }" +
                    "    .label { font-weight: bold; color: #333333; }" +
                    "  </style>" +
                    "</head>" +
                    "<body>" +
                    "  <div class='container'>" +
                    "    <h2>New Contact Message</h2>" +
                    "    <p><span class='label'>Name:</span> " + contactDto.getName() + "</p>" +
                    "    <p><span class='label'>Email:</span> " + contactDto.getEmail() + "</p>" +
                    "    <p><span class='label'>Phone Number:</span> " + contactDto.getPhoneNumber() + "</p>" +
                    "    <p><span class='label'>Country:</span> " + contactDto.getCountry() + "</p>" +
                    "    <p><span class='label'>Tour Type:</span> " + contactDto.getTourType() + "</p>" +
                    "    <p><span class='label'>Message:</span><br/>" + contactDto.getMessage() + "</p>" +
                    "  </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
