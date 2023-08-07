package com.example.mytrack.controllers;

import com.example.mytrack.dtos.requests.LoginRequestDto;
import com.example.mytrack.dtos.requests.UserRequestDto;
import com.example.mytrack.dtos.responses.UserResponseDto;
import com.example.mytrack.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mytask")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> userSignup (@RequestBody @Valid UserRequestDto userRequestDto) {
        System.out.println("I'm here");
        UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
        System.out.println("I'm here also");
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        var LoginResponseDto = userService.userLogin(loginRequestDto, request);
        return ResponseEntity.ok(LoginResponseDto);
    }

}