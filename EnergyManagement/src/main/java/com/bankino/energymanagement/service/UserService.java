package com.bankino.energymanagement.service;

import com.bankino.energymanagement.dto.UserDTO;

import java.util.List;

/**
 * Service interface for managing user data.
 */
public interface UserService {
    /**
     * Creates a new user.
     *
     * @param userDto The user data to create.
     * @return The created user.
     */
    UserDTO createUser(UserDTO userDto);

    /**
     * Retrieves all users.
     *
     * @return List of all users.
     */
    List<UserDTO> getAllUsers();

    /**
     * Retrieves a user by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user if found, otherwise null.
     */
    UserDTO getUserById(Long userId);

    /**
     * Updates a user by ID.
     *
     * @param userId  The ID of the user to update.
     * @param userDto The updated user data.
     * @return The updated user if found, otherwise null.
     */
    UserDTO updateUser(Long userId, UserDTO userDto);

    /**
     * Deletes a user by ID.
     *
     * @param userId The ID of the user to delete.
     * @return True if the user was found and deleted, otherwise false.
     */
    boolean deleteUser(Long userId);
}

