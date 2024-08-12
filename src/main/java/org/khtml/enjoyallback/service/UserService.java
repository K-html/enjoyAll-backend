package org.khtml.enjoyallback.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.khtml.enjoyallback.entity.User;
import org.khtml.enjoyallback.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
