package com.example.demo.user;

import com.example.demo.domain.User;
import com.example.demo.dto.UserPasswordChangeDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public User registerNewUser(UserRegistrationDto dto) {

        User user = userMapper.userRegistrationDtoToUser(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(UserPasswordChangeDto dto) {

        User user = userRepository.findById(dto.getId()).orElseThrow(() -> new UserNotFoundException(dto.getId()));

        if (!passwordEncoder.matches(dto.getPassword(),user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        } else {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);
        }
    }

}
