package com.vpr.app.security.service;

import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.security.dto.converter.UserConverter;
import com.vpr.app.security.dto.request.RegistrationRequest;
import com.vpr.app.security.entity.User;
import com.vpr.app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  public static final String ENTITY_NAME = User.class.getSimpleName();
  public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  /**
   * Retrieves all users.
   */
  @PreAuthorize("hasRole('ADMIN')")
  public List<User> findAll() {
    return userRepository.findAll();
  }

  /**
   * Retrieves a user by ID.
   */
  @PreAuthorize("hasRole('ADMIN')")
  public User findById(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new VprEntityNotFoundException(
            String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
  }

  /**
   * Creates a new user.
   */
  @PreAuthorize("hasRole('ADMIN')")
  public User createUser(RegistrationRequest registrationRequest) {
    User user = userConverter.convertRegisterRequestToUser(registrationRequest);
    User saved = userRepository.save(user);
    log.info("Created new {} with id {}", ENTITY_NAME, saved.getId());
    return saved;
  }

  /**
   * Updates an existing user.
   */
  @PreAuthorize("hasRole('ADMIN')")
  public User updateUser(User user) {
    validateExistence(user.getId());
    User updated = userRepository.save(user);
    log.info("Updated {} with id {}", ENTITY_NAME, updated.getId());
    return updated;
  }

  /**
   * Deletes a user by ID.
   */
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteUser(long id) {
    validateExistence(id);
    userRepository.deleteById(id);
    log.info("Successfully deleted {} with id {}", ENTITY_NAME, id);
  }

  private void validateExistence(long id) {
    if (!userRepository.existsById(id)) {
      log.warn("Attempted to access non-existent {} with id {}", ENTITY_NAME, id);
      throw new VprEntityNotFoundException(
          String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
    }
  }
}
