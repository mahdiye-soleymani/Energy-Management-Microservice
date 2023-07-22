package com.bankino.energymanagement.service.impl;

import com.bankino.energymanagement.dto.UserDTO;
import com.bankino.energymanagement.entity.User;
import com.bankino.energymanagement.repository.UserRepository;
import com.bankino.energymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDto) {
        User user = userRepository.save(userDto.toUserEntity());
        return user.toUserDto();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userRepository.findById(userId).map(User::toUserDto).orElse(null);
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long userId, UserDTO userDto) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setName(userDto.getName());
            existingUser.setCity(userDto.getCity());
            existingUser.setNeighborhood(userDto.getNeighborhood());
            userRepository.save(existingUser);
            return existingUser.toUserDto();
        }).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteUser(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}

