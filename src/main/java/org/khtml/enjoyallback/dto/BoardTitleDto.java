package org.khtml.enjoyallback.dto;

import lombok.Data;
import org.khtml.enjoyallback.entity.Board;
import org.khtml.enjoyallback.global.KeywordEnum;

import java.time.LocalDateTime;

@Data
public class BoardTitleDto {
    private Long id;
    private String title;
    private KeywordEnum keyword;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long viewCount;

    public static BoardTitleDto fromEntity(Board board) {
        BoardTitleDto boardTitleDto = new BoardTitleDto();
        boardTitleDto.setId(board.getId());
        boardTitleDto.setTitle(board.getTitle());
        boardTitleDto.setKeyword(board.getKeyword());
        boardTitleDto.setStartDate(board.getStartDate());
        boardTitleDto.setEndDate(board.getEndDate());
        boardTitleDto.setViewCount(board.getViewCount());
        return boardTitleDto;
    }
}
