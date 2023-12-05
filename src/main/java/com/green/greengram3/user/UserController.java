package com.green.greengram3.user;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.user.model.UserFollowDto;
import com.green.greengram3.user.model.UserSigninDto;
import com.green.greengram3.user.model.UserSigninVo;
import com.green.greengram3.user.model.UserSignupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;


    @Operation(summary = "회원가입", description = "회원가입 처리")
    @PostMapping("/signup")
    public ResVo postSignup(@RequestBody UserSignupDto dto) {
        log.info("dto: {}",dto);
        return service.signup(dto);
    }

    @PostMapping("/signin")
    public UserSigninVo postSignup(@RequestBody UserSigninDto dto) {
        log.info("dtd : {}",dto);
        return service.signin(dto);
        // result 1:성공, 2:아이디없음, 3:비밀번호틀림
    }


    // follow
    // result : 1(follow), 0:(취소)
    @PostMapping("/follow")
    public ResVo toggleFollow(@RequestBody UserFollowDto dto) {
        return service.toggleFollow(dto);
    }
}
