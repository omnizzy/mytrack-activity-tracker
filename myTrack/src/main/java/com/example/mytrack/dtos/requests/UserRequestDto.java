package com.example.mytrack.dtos.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class UserRequestDto {

        @NotBlank(message = "Please provide a username")
        private String username;

        @Email(message = "Please provide a valid email")
        private String email;

        @NotEmpty(message = "Please provide a password")
        private String password;



}
