package org.khtml.enjoyallback.controller;

import lombok.RequiredArgsConstructor;
import org.khtml.enjoyallback.api.Api_Response;
import org.khtml.enjoyallback.dto.BoardDetail;
import org.khtml.enjoyallback.entity.CrawledData;
import org.khtml.enjoyallback.service.BoardService;
import org.khtml.enjoyallback.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/ai")
    public ResponseEntity<Api_Response<List<BoardDetail>>> createBoard(@RequestBody List<BoardDetail> boardDetails) {
        List<BoardDetail> savedBoardDetails = boardDetails.stream()
                .map(boardService::saveBoard).toList();
        return ApiResponseUtil.createResponse(
                HttpStatus.CREATED.value(),
                "Success Create Board",
                savedBoardDetails
        );
    }

    @GetMapping("/ai")
    public ResponseEntity<Api_Response<List<CrawledData>>> getCrawledData() {
        List<CrawledData> crawledData = boardService.getCrawledData();
        return ApiResponseUtil.createResponse(
                HttpStatus.OK.value(),
                "Success Get Crawled Data",
                crawledData
        );
    }
}

