package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import cn.jxufe.dto.NormalResult;
import cn.jxufe.exception.RegisterException;
import cn.jxufe.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author hsw
 * @date 2020/2/22 9:21 下午
 */
@Api(tags = "登录注册接口")
@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "登录接口，前端ajax请求，dataType为json")
    @PostMapping(value = "/login")
    public NormalResult<?> login(@Email String email,
                                 @NotBlank String password,
                                 @NotBlank String verifyCode,
                                 HttpServletRequest request) {


        return null;
    }

    @ApiOperation(value = "注册接口")
    @ApiImplicitParams(
        {
            @ApiImplicitParam(name = "email", value = "注册邮箱，必须唯一"),
            @ApiImplicitParam(name = "username", value = "用户名，必须唯一"),
            @ApiImplicitParam(name = "password", value = "密码，长度区间[6,16]")
        }
    )
    @PostMapping("/register")
    public NormalResult<?> Register(@Email String email,
                                    @NotBlank String username,
                                    @NotBlank String password) {
        try {
            if (userService.registerUser(username, email, password)) {
                return NormalResult.success();
            } else {
                return NormalResult.failureWithMessage("未知错误");
            }
        } catch (RegisterException re) {
            return NormalResult.failureWithMessage(re.getMessage());
        } catch (Exception e) {
            return NormalResult.failureWithMessage("未知错误");
        }
    }

}
