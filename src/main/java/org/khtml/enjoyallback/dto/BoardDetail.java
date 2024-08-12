package org.khtml.enjoyallback.dto;

import lombok.Data;
import org.khtml.enjoyallback.entity.Board;
import org.khtml.enjoyallback.global.Category;
import org.khtml.enjoyallback.global.KeywordEnum;

import java.time.LocalDateTime;

@Data
public class BoardDetail{
    private Long id;
    private String imgUrl;
    private String title;
    private Category category;
    private KeywordEnum keyword;
    private String eligibility;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String contact;
    private String content;
    private String applyMethod;
    private Long viewCount;

    public static BoardDetail fromEntity(Board board) {
        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setId(board.getId());
        boardDetail.setImgUrl(board.getImgUrl());
        boardDetail.setTitle(board.getTitle());
        boardDetail.setCategory(board.getCategory());
        boardDetail.setKeyword(board.getKeyword());
        boardDetail.setEligibility(board.getEligibility());
        boardDetail.setStartDate(board.getStartDate());
        boardDetail.setEndDate(board.getEndDate());
        boardDetail.setContact(board.getContact());
        boardDetail.setContent(board.getContent());
        boardDetail.setApplyMethod(board.getApplyMethod());
        boardDetail.setViewCount(board.getViewCount());
        return boardDetail;
    }
}

