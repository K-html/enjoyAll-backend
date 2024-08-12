package org.khtml.enjoyallback.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.khtml.enjoyallback.global.KeywordEnum;

import java.util.Set;

@Entity
@Data
@Table(name = "KEYWORD")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private KeywordEnum keyword;

    @ManyToMany(mappedBy = "keywords")
    private Set<User> users;
}