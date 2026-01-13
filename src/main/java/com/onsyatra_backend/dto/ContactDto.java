package com.onsyatra_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {
    public String name;
    public String country;
    public String email;
    public String phoneNumber;
    public String tourType;
    public String message;
}
