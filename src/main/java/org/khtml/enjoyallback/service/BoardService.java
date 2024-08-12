package org.khtml.enjoyallback.service;

import lombok.RequiredArgsConstructor;
import org.khtml.enjoyallback.dto.BoardDetail;
import org.khtml.enjoyallback.entity.Board;
import org.khtml.enjoyallback.entity.CrawledData;
import org.khtml.enjoyallback.repository.BoardRepository;
import org.khtml.enjoyallback.repository.CrawledDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final CrawledDataRepository crawledDataRepository;

    public BoardDetail saveBoard(BoardDetail boardDetail) {
        Board board = new Board();
        board.AiCreateBoard(boardDetail);
        Board savedBoard = boardRepository.save(board);
        return BoardDetail.fromEntity(savedBoard);
    }

    public List<CrawledData> getCrawledData() {
        return crawledDataRepository.findAll();
    }
}

