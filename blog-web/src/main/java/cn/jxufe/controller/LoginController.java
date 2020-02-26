package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import cn.jxufe.bean.Token;
import cn.jxufe.bean.User;
import cn.jxufe.dto.NormalResult;
import cn.jxufe.exception.LoginException;
import cn.jxufe.exception.RegisterException;
import cn.jxufe.service.UserService;
import cn.jxufe.validation.group.LoginGroup;
import cn.jxufe.validation.group.RegisterGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author hsw
 * @date 2020/2/22 9:21 下午
 */
@Api(tags = "登录注册接口")
@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 可以用email或username登录
     *
     * @param user     属性组合为：用户名和密码，邮箱和密码
     * @param response 添加token
     * @return 返回更丰富的个人信息
     */
    @ApiOperation(value = "登录接口，前端ajax请求，dataType为json")
    @ApiImplicitParam(name = "user",
        value = "用户名(username, 唯一)，邮箱(email, 未注册过)，" +
            "密码(password, 长度区间6～16) json格式")
    @PostMapping(value = "/login")
    public NormalResult<?> login(@RequestBody @Validated(LoginGroup.class) User user,
                                 HttpServletResponse response) throws LoginException {
        Token token = userService.login(user);

        return null;
    }

    @ApiOperation(value = "注册接口")
    @ApiImplicitParam(name = "user", value = "用户名(username, 唯一)，邮箱(email, 未注册过)，密码(password, 长度区间6～16) json格式")
    @PostMapping("/register")
    public NormalResult<?> Register(@RequestBody @Validated(RegisterGroup.class) User user)
        throws RegisterException {
        userService.registerUser(user);
        return NormalResult.success();
    }

}
