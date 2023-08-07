package com.example.mytrack.dtos.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponseDto {

    private Long id;

    private String username;
}
