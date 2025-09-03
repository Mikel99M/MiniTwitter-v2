package com.example.demo.service;

import com.example.demo.domain.Like;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.UserPasswordChangeDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.exception.InvalidPasswordException;
import com.example.demo.exception.UserNameAlreadyTakenException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public User getUserById(Long id) {
        log.info("Getting user with id: " + id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User registerNewUser(UserRegistrationDto dto) {
        log.info("Registering user: " + dto.getUserName());

        if (userRepository.existsByUserName(dto.getUserName())) {
            log.warn("User by " + dto.getUserName() + " already exists.");
            throw new UserNameAlreadyTakenException("User by " + dto.getUserName() + " already exists.");
        } else {

        User user = userMapper.userRegistrationDtoToUser(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
        }
    }

    @Transactional
    public void updateUserPassword(Long id, UserPasswordChangeDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        log.info("Changing password of user: " + user.getUserName());

        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            log.warn("Incorrect password attempt for user {}", id);
            throw new InvalidPasswordException("Current password is incorrect");
        } else {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);
        }
    }

    @Transactional
    public List<Post> getPostsMadeByUser(Long id) {
        log.info("Getting all posts made by user with id: " + id);
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );

        return postRepository.findAllByAuthor(user);

    }

    @Transactional
    public List<Post> getPostsLikedByUser(Long id) {
        log.info("Getting all posts liked by user with id: " + id);
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );

        return user.getLikedPosts().stream()
                .map(Like::getLikedPost)
                .collect(Collectors.toList());

    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
        log.info("Deleting user with name: " + user.getUserName());

        userRepository.delete(user);
    }
}
