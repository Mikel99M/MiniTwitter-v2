package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserPasswordChangeDto;
import com.example.demo.dto.UserRegistrationDto;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public User getUserById(Long id) {
        log.info("Geting user with id: " + id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User registerNewUser(UserRegistrationDto dto) {
        log.info("Registering user: " + dto.getUserName());

        if (userRepository.existsByUserName(dto.getUserName())) {
            log.warn("User by " + dto.getUserName() + " already exists.");
            throw new UserNameAlreadyTakenException("User by " + dto.getUserName() + " already exists.");
        }

        User user = userMapper.userRegistrationDtoToUser(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }

    @Transactional
    public void updateUserPassword(Long id, UserPasswordChangeDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        log.info("Changing password of user: " + user.getUserName());

        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            log.warn("Incorrect password attempt for user {}", id);
            throw new InvalidPasswordException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }

}
