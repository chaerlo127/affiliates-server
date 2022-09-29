package com.example.affiliates.User.Controller;

import com.example.affiliates.User.DTO.UserDTO;
import com.example.affiliates.User.Entity.UserEntity;
import com.example.affiliates.User.Service.UserService;
import com.example.affiliates.Util.BaseException;
import com.example.affiliates.Util.BaseResponse;
import com.example.affiliates.jwt.DTO.TokenDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    /*
     * 오수연: 닉네임 변경
     * */
    @ResponseBody
    @ApiOperation(value = "닉네임 변경 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "요청에 성공하였습니다."),
            @ApiResponse(code = 2004, message = "중복된 닉네임입니다."),
            @ApiResponse(code = 2006, message = "변경할 닉네임을 입력해주세요."),
            @ApiResponse(code = 2007, message = "동일한 닉네임으로 변경할 수 없습니다.")
    })
    @PatchMapping(value = "/changeNickName")
    public BaseResponse<String> changeNickName (Principal principal, @RequestBody UserDTO.NickName nickName){
        try {
            this.userService.changeNickName(principal, nickName);
            return new BaseResponse<>("닉네임을 변경했습니다.");
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }


    /*
    * 장채은 : 로그아웃
    * */
    @ResponseBody
    @GetMapping("/logout")
    public BaseResponse<String> logout(Principal principal, HttpServletRequest request){
        try{
            userService.logout(principal, request);
            return new BaseResponse<>("로그아웃 되었습니다.");
        }catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}


