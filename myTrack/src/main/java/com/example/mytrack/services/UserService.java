package com.example.mytrack.services;

import com.example.mytrack.dtos.requests.LoginRequestDto;
import com.example.mytrack.dtos.requests.UserRequestDto;
import com.example.mytrack.dtos.responses.LoginResponseDto;
import com.example.mytrack.dtos.responses.UserResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    UserResponseDto registerUser(UserRequestDto userRequestDto);

    LoginResponseDto userLogin(LoginRequestDto loginRequestDto, HttpServletRequest request);

}
