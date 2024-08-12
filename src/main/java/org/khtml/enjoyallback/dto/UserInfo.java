package org.khtml.enjoyallback.dto;

import lombok.Data;
import org.khtml.enjoyallback.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserInfo {
    private Long id;
    private String name;
    private String contact;
    private List<String> keywords = new ArrayList<>();

    public static UserInfo fromEntity(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setContact(user.getContact());
        userInfo.setKeywords(user.getKeywords().stream()
                .map(keyword -> {
                    if (keyword != null && keyword.getKeyword() != null) {
                        return keyword.getKeyword().toString();
                    } else {
                        return null;
                    }
                })
                .filter(keyword -> keyword != null)
                .collect(Collectors.toList()));
        return userInfo;
    }
}
