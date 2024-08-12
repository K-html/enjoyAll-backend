package org.khtml.enjoyallback.controller;

import lombok.RequiredArgsConstructor;
import org.khtml.enjoyallback.api.Api_Response;
import org.khtml.enjoyallback.config.jwt.JwtTokenUtil;
import org.khtml.enjoyallback.dto.UserReqDto;
import org.khtml.enjoyallback.entity.User;
import org.khtml.enjoyallback.global.UserStatus;
import org.khtml.enjoyallback.global.code.CommonErrorCode;
import org.khtml.enjoyallback.repository.UserRepository;
import org.khtml.enjoyallback.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    /*
    카카오 소셜 정보 id와 email을 받아 사용자 존재 여부를 확인하고, 회원 가입 안내 또는 로그인을 진행하는 api
     */
    @PostMapping("/kakao")
    public ResponseEntity<Api_Response<Object>> loginWithKakao(@RequestBody Map<String, String> request) throws Exception {
        String socialId = request.get("#socialId");
        String socialEmail = request.get("#socialEmail");

        if (socialId == null || socialEmail == null) {
            throw new AccessDeniedException(CommonErrorCode.MISSING_SOCIAL_INFO.getMessage());
        }

        User user = userRepository.findUserBySocialId(socialId).orElseGet(() -> User.builder()
                .socialId(socialId)
                .socialEmail(socialEmail)
                .build());
        user.updateUserSocial(socialEmail);
        userRepository.save(user);
        if (user.getStatus() == UserStatus.LOGIN) {
            return ApiResponseUtil.createSuccessResponse("회원가입이 필요합니다.", user.getId());
        } else if (user.getStatus() == UserStatus.SLEEP){
            user.wakeUp();
        }
        Map<String, String> response = createLoginToken(user);
        return ApiResponseUtil.createSuccessResponse("로그인 성공", response);
    }
    
    /*
    사용자 추가 정보를 받아 회원 가입을 진행하는 api, 회원 가입 성공 시 jwt 토큰 발급
     */
    @PostMapping("/join")
    public ResponseEntity<Api_Response<Map<String, String>>> joinUser(@RequestBody UserReqDto userReqDto) {
        User user = new User();
        user.joinUser(userReqDto);
        userRepository.save(user);
        Map<String, String> response = createLoginToken(user);
        return ApiResponseUtil.createSuccessResponse("LOGIN SUCCESS", response);
    }

    private Map<String, String> createLoginToken(User user) {
        if (user.getStatus() == UserStatus.JOIN) {
            String jwtAccessToken = jwtTokenUtil.createAccessToken(user.getId());
            String jwtRefreshToken = jwtTokenUtil.createRefreshToken(user.getId());
            Map<String, String> response = new HashMap<>();
            response.put("#jwtAccessToken", jwtAccessToken);
            response.put("#jwtRefreshToken", jwtRefreshToken);
            return response;
        }
        return null;
    }
    /*
    토큰 재발급 api, 유효한 리프레쉬 토큰을 받아 새로운 엑세스 토큰을 발급
     */
    @GetMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {
        String refreshToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            refreshToken = authorizationHeader.substring(7);
        }
        String userId = jwtTokenUtil.extractUserId(refreshToken);
        if (jwtTokenUtil.isValidateToken(refreshToken, userId)) {
            String newAccessToken = jwtTokenUtil.createAccessToken(Long.valueOf(userId));
            String newRefreshToken = jwtTokenUtil.createRefreshToken(Long.valueOf(userId));
            Map<String, String> response = new HashMap<>();
            response.put("#jwtAccessToken", newAccessToken);
            response.put("#jwtRefreshToken", newRefreshToken);
            return ApiResponseUtil.createSuccessResponse("Create a new token", response);
        } else {
            return ApiResponseUtil.createErrorResponse(CommonErrorCode.MISSING_TOKEN);
        }
    }
}
