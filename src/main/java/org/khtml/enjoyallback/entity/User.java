package org.khtml.enjoyallback.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khtml.enjoyallback.dto.UserInfo;
import org.khtml.enjoyallback.dto.UserReqDto;
import org.khtml.enjoyallback.global.KeywordEnum;
import org.khtml.enjoyallback.global.UserStatus;

import java.util.Set;

@Entity
@Data
@Table(name = "USER")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String socialName;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "social_email", unique = true, nullable = false)
    private String socialEmail;

    private String contact;

    private KeywordEnum keyword;

    @Enumerated(EnumType.STRING)
    private UserStatus status;


    @Builder
    public User(String socialId, String socialEmail) {
        this.socialId = socialId;
        this.socialEmail = socialEmail;
        this.status = UserStatus.LOGIN;
    }

    @ManyToMany
    @JoinTable(
            name = "user_keyword",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private Set<Keyword> keywords;

    public void joinUser(UserReqDto userReqDto) {
        this.socialName = userReqDto.getSocialName();
        this.keyword = userReqDto.getKeyword();
        this.status = UserStatus.JOIN;
    }

    public User updateUserSocial(String socialEmail) {
        if (socialEmail != null && !this.socialEmail.equals(socialEmail)) {
            this.socialEmail = socialEmail;
        }
        return this;
    }

    public void wakeUp() {
        this.status = UserStatus.JOIN;
    }
}
