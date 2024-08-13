package org.khtml.enjoyallback.controller;

import lombok.RequiredArgsConstructor;
import org.khtml.enjoyallback.api.Api_Response;
import org.khtml.enjoyallback.dto.UserInfo;
import org.khtml.enjoyallback.service.UserService;
import org.khtml.enjoyallback.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Api_Response<UserInfo>> getUserInfo(@PathVariable Long userId) {
        UserInfo userInfo = userService.getUserInfo(userId);
        return ApiResponseUtil.createResponse(
                HttpStatus.OK.value(),
                "Success Get User Info",
                userInfo
        );
    }

    @PostMapping("/join")
    public ResponseEntity<Api_Response<UserInfo>> joinUser(@PathVariable Long userId, @RequestBody UserInfo userInfo) {
        userService.joinUser(userId, userInfo);
        return ApiResponseUtil.createResponse(
                HttpStatus.CREATED.value(),
                "Success Create Board",
                userInfo
        );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Api_Response<UserInfo>> updateKeyword(@PathVariable Long userId, @RequestBody UserInfo userInfo) {
        userService.updateUserKeywords(userId, userInfo.getKeywords());
        return ApiResponseUtil.createResponse(
                HttpStatus.OK.value(),
                "Success Create Board",
                userInfo
        );
    }
}
