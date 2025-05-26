package com.vpr.app.security.service;

import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.security.dto.RegistrationRequest;
import com.vpr.app.security.dto.User;
import com.vpr.app.security.dto.UserConverter;
import com.vpr.app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(long id) {
    return userRepository.findById(id).orElseThrow(() -> new VprEntityNotFoundException(""));
  }

  public User updateUser(User user) {
    return userRepository.save(user);
  }

  public User createUser(RegistrationRequest registrationRequest) {
    return userRepository.save(userConverter.convertRegisterRequestToUser(registrationRequest));
  }

  public void deleteUser(long id) {
    userRepository.deleteById(id);
  }
}
