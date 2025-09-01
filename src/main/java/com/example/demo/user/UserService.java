package com.example.demo.user;

import com.example.demo.domain.User;
import com.example.demo.dto.UserPasswordChangeDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.exception.InvalidPasswordException;
import com.example.demo.exception.UserNameAlreadyTakenException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User registerNewUser(UserRegistrationDto dto) {
        log.info("Registering user: " + dto.getUserName());

        if (userRepository.existsByUserName(dto.getUserName())) {
            throw new UserNameAlreadyTakenException("User by " + dto.getUserName() + " already exists.");
        }

        User user = userMapper.userRegistrationDtoToUser(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(UserPasswordChangeDto dto) {
        log.info("Changing password of user: " + dto.getUserName());
        User user = userRepository.findById(dto.getId()).orElseThrow(() -> new UserNotFoundException(dto.getId()));

        if (!passwordEncoder.matches(dto.getPassword(),user.getPassword())) {
            log.warn("Current password is incorrect.");
            throw new InvalidPasswordException("Current password is incorrect");
        } else {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);
        }
    }

}
