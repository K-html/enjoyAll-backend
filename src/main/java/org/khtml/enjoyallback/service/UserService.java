package org.khtml.enjoyallback.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.khtml.enjoyallback.dto.UserInfo;
import org.khtml.enjoyallback.entity.Keyword;
import org.khtml.enjoyallback.entity.User;
import org.khtml.enjoyallback.global.KeywordEnum;
import org.khtml.enjoyallback.global.UserStatus;
import org.khtml.enjoyallback.repository.KeywordRepository;
import org.khtml.enjoyallback.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public void joinUser(Long userId, UserInfo userInfo) {
        User user = findById(userId);
        updateUserKeywords(userId, userInfo.getKeywords());
        user.setStatus(UserStatus.JOIN);
        userRepository.save(user);
    }

    @Transactional
    public UserInfo  getUserInfo(Long userId) {
        User user = findById(userId);
        return UserInfo.fromEntity(user);
    }

    @Transactional
    public void updateUserKeywords(Long userId, List<String> keywords) {
        User user = findById(userId);
        Set<Keyword> keywordSet = keywords.stream()
                .map(keyword -> {
                    try {
                        KeywordEnum keywordEnum = KeywordEnum.valueOf(keyword.toUpperCase());
                        Keyword keywordEntity = keywordRepository.findByKeyword(keywordEnum);
                        if (keywordEntity == null) {
                            throw new EntityNotFoundException("Keyword not found");
                        }
                        return keywordEntity;
                    } catch (IllegalArgumentException e) {
                        throw new EntityNotFoundException("Keyword not found");
                    }
                })
                .collect(Collectors.toSet());
        if (keywordSet.isEmpty()) {
            throw new EntityNotFoundException("Keyword not found");
        }
        user.setKeywords(keywordSet);
        userRepository.save(user);
    }
}

