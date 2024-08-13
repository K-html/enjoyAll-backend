package org.khtml.enjoyallback.dto;

import lombok.Data;
import org.khtml.enjoyallback.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserInfo {
    private Long id;
    private String socialName;
    private String socialId;
    private String socialEmail;
    private Integer age;
    private List<String> keywords = new ArrayList<>();

    public static UserInfo fromEntity(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setSocialName(user.getSocialName());
        userInfo.setSocialId(user.getSocialId());
        userInfo.setSocialEmail(user.getSocialEmail());
        userInfo.setAge(user.getAge());
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
