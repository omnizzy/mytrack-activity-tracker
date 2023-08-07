package com.example.mytrack.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException{

    private String message;

    private final LocalDate time = LocalDate.now();
}
