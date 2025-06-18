package com.vpr.app.security.service;

import com.vpr.app.audit.log.annotation.AuditCreate;
import com.vpr.app.audit.log.annotation.AuditDelete;
import com.vpr.app.audit.log.annotation.AuditUpdate;
import com.vpr.app.exceptions.UserAlreadyExistsException;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.security.dto.converter.UserConverter;
import com.vpr.app.security.dto.request.RegistrationRequest;
import com.vpr.app.security.entity.User;
import com.vpr.app.security.enums.Role;
import com.vpr.app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service responsible for managing user entities in the system.
 * Provides CRUD operations for user data and handles user-specific business logic.
 * Includes role-based access control for administrative operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private static final String ENTITY_NAME = User.class.getSimpleName();
  private static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
  private static final String INSTANCE_WITH_EMAIL_DOES_NOT_EXIST =
      "%s instance with email %s does not exist";
  private static final String INSTANCE_ALREADY_EXIST = "%s instance with email %s already exist: ";

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  /**
   * Retrieves all users from the system.
   * Requires ADMIN role to access.
   *
   * @return a list of all users in the system
   */
  @PreAuthorize("hasRole('ADMIN')")
  public List<User> findAll() {
    return userRepository.findAll();
  }

  /**
   * Retrieves a user by their ID.
   * Requires ADMIN role to access.
   *
   * @param id the ID of the user to retrieve
   *
   * @return the user with the specified ID
   *
   * @throws VprEntityNotFoundException if no user exists with the given ID
   */
  @PreAuthorize("hasRole('ADMIN')")
  public User findById(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new VprEntityNotFoundException(
            String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
  }

  /**
   * Retrieves a user by their email address.
   *
   * @param email the email address of the user to retrieve
   *
   * @return the user with the specified email
   *
   * @throws VprEntityNotFoundException if no user exists with the given email
   */
  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new VprEntityNotFoundException(
            String.format(INSTANCE_WITH_EMAIL_DOES_NOT_EXIST, ENTITY_NAME, email)));
  }

  /**
   * Creates a new user based on the provided registration request.
   * Requires ADMIN role to access.
   *
   * @param registrationRequest the registration request containing user details
   *
   * @return the newly created user
   *
   * @throws UserAlreadyExistsException if a user with the same email already exists
   */
  @AuditCreate(entity = "User", id = "#result.id")
  @PreAuthorize("hasRole('ADMIN')")
  public User createUser(RegistrationRequest registrationRequest) {
    User user = userConverter.convertRegisterRequestToUser(registrationRequest);
    return saveUser(user);
  }

  /**
   * Creates a new user with VIEWER role based on the provided registration request.
   *
   * @param registrationRequest the registration request containing user details
   *
   * @return the newly created user with VIEWER role
   *
   * @throws UserAlreadyExistsException if a user with the same email already exists
   */
  @AuditCreate(entity = "User")
  public User createViewerUser(RegistrationRequest registrationRequest) {
    User user = userConverter.convertRegisterRequestToUser(registrationRequest);
    user.setRole(Role.VIEWER);
    return saveUser(user);
  }

  /**
   * Saves a new user to the system.
   *
   * @param user the user to save
   *
   * @return the saved user with generated ID
   *
   * @throws UserAlreadyExistsException if a user with the same email already exists
   */
  public User saveUser(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new UserAlreadyExistsException(
          String.format(INSTANCE_ALREADY_EXIST, ENTITY_NAME, user.getEmail()));
    }
    User saved = userRepository.save(user);
    log.info("Created new {} with id {}", ENTITY_NAME, saved.getId());
    return saved;
  }

  /**
   * Updates an existing user in the system.
   * Requires ADMIN role to access.
   *
   * @param user the user to update
   *
   * @return the updated user
   *
   * @throws VprEntityNotFoundException if no user exists with the given ID
   */
  @AuditUpdate(entity = "User")
  @PreAuthorize("hasRole('ADMIN')")
  public User updateUser(User user) {
    validateExistence(user.getId());
    User updated = userRepository.save(user);
    log.info("Updated {} with id {}", ENTITY_NAME, updated.getId());
    return updated;
  }

  /**
   * Deletes a user from the system by their ID.
   * Requires ADMIN role to access.
   *
   * @param id the ID of the user to delete
   *
   * @throws VprEntityNotFoundException if no user exists with the given ID
   */
  @AuditDelete(entity = "User")
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteUser(long id) {
    validateExistence(id);
    userRepository.deleteById(id);
    log.info("Successfully deleted {} with id {}", ENTITY_NAME, id);
  }

  /**
   * Validates that a user exists with the given ID.
   *
   * @param id the ID to validate
   *
   * @throws VprEntityNotFoundException if no user exists with the given ID
   */
  public void validateExistence(long id) {
    if (!userRepository.existsById(id)) {
      log.warn("Attempted to access non-existent {} with id {}", ENTITY_NAME, id);
      throw new VprEntityNotFoundException(
          String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
    }
  }

  /**
   * Checks if a user exists with the given email address.
   *
   * @param email the email address to check
   *
   * @return true if a user exists with the given email, false otherwise
   */
  public boolean isUserExistsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }
}
