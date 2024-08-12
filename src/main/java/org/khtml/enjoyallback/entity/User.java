package org.khtml.enjoyallback.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.khtml.enjoyallback.dto.UserInfo;
import org.khtml.enjoyallback.global.UserStatus;

import java.util.Set;

@Entity
@Data
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "social_email", unique = true, nullable = false)
    private String socialEmail;

    private String contact;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToMany
    @JoinTable(
            name = "user_keyword",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private Set<Keyword> keywords;

    public void updateUserInfo(UserInfo userInfo) {
        if (userInfo.getName() != null && !userInfo.getName().trim().isEmpty()){
            this.setName(userInfo.getName());
        }
        this.contact = userInfo.getContact();
    }
}
