package com.example.mytrack.services.implementations;

import com.example.mytrack.dtos.requests.LoginRequestDto;
import com.example.mytrack.dtos.requests.UserRequestDto;
import com.example.mytrack.dtos.responses.LoginResponseDto;
import com.example.mytrack.dtos.responses.UserResponseDto;
import com.example.mytrack.entities.AppUser;
import com.example.mytrack.exceptions.InvalidDataException;
import com.example.mytrack.repositories.UserRepository;
import com.example.mytrack.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        Optional<?> userNameValidation = userRepository.findByUsername(userRequestDto.getUsername());
        if (userNameValidation.isPresent()){
            throw new InvalidDataException("This User already exist");
        }
            AppUser appUser = new AppUser();
            appUser.setUsername(userRequestDto.getUsername());
            appUser.setEmail(userRequestDto.getEmail());
            appUser.setPassword(userRequestDto.getPassword());
            AppUser savedAppUser = userRepository.save(appUser);

            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(savedAppUser.getId());
            userResponseDto.setUsername(savedAppUser.getUsername());
            userResponseDto.setEmail(savedAppUser.getEmail());


            return userResponseDto;

    }

    @Override
    public LoginResponseDto userLogin(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        Optional<AppUser> optionalAppUser = userRepository.findByUsername(loginRequestDto.getUsername());
        if (optionalAppUser.isEmpty()) {
            throw new InvalidDataException("Invalid email and password");
        }
        AppUser appUser = optionalAppUser.get();
        if (!loginRequestDto.getPassword().equals(optionalAppUser.get().getPassword())) {
        throw new InvalidDataException("Incorrect email and password");
        }

        HttpSession session = request.getSession();
                session.setAttribute("appUser", appUser);

        return LoginResponseDto.builder()
                .username(appUser.getUsername())
                .id(appUser.getId())
                .build();

    }

}
