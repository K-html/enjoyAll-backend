package org.khtml.enjoyallback.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.khtml.enjoyallback.dto.BoardDetail;
import org.khtml.enjoyallback.global.Category;
import org.khtml.enjoyallback.global.KeywordEnum;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img_url")
    private String imgUrl;

    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private KeywordEnum keyword;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String eligibility;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private String contact;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "apply_method")
    private String applyMethod;

    @Column(name = "view_count")
    private Long viewCount=0L;

    private String age;

    public void AiCreateBoard(BoardDetail boardDetail){
        this.imgUrl = boardDetail.getImgUrl();
        this.title = boardDetail.getTitle();
        this.category = boardDetail.getCategory();
        this.keyword = boardDetail.getKeyword();
        this.eligibility = boardDetail.getEligibility();
        this.startDate = boardDetail.getStartDate();
        this.endDate = boardDetail.getEndDate();
        this.contact = boardDetail.getContact();
        this.content = boardDetail.getContent();
        this.applyMethod = boardDetail.getApplyMethod();
        this.age = boardDetail.getAge();
    }
}

