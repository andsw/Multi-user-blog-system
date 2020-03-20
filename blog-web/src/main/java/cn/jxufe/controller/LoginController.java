package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jxufe.bean.Token;
import cn.jxufe.bean.User;
import cn.jxufe.dto.RedirectResult;
import cn.jxufe.exception.LoginException;
import cn.jxufe.exception.RegisterException;
import cn.jxufe.my_enum.WebsitePathEnum;
import cn.jxufe.service.UserService;
import cn.jxufe.util.CookieUtil;
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

    @Value(value = "${cookie.name.token: mphjouplfo}")
    private String tokenCookieName;

    @Value(value = "${cookie.name.userId:userId}")
    private String userIdCookieName;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 可以用email或username登录
     *
     * @param user     属性组合为：用户名和密码，邮箱和密码
     * @param response 添加token
     * @return 成功后重定向到用户主页
     */
    @ApiOperation(value = "登录接口，前端ajax请求，dataType为json")
    @ApiImplicitParam(name = "user",
        value = "用户名(username, 唯一)，邮箱(email, 未注册过)，" +
            "密码(password, 长度区间6～16) json格式")
    @PostMapping(value = "/login")
    public RedirectResult login(@RequestBody @Validated(LoginGroup.class) User user,
                                HttpServletResponse response) throws LoginException {
        Token token = userService.login(user);
        // name为loginToken每个字母后加以为
        Cookie tokenCookie = new Cookie(tokenCookieName, token.getLoginToken());
        Cookie userIdCookie = new Cookie(userIdCookieName, "" + token.getUserId());
        response.addCookie(tokenCookie);
        response.addCookie(userIdCookie);

        return RedirectResult.redirect(WebsitePathEnum.USER_HOMEPAGE.getPath());
    }

    /**
     * 注册异常将抛出至异常处理器GlobalExceptionInterceptor处理
     * 其他接口同理
     * @param user
     * @return 注册成功重定向到登录界面
     * @throws RegisterException 注册异常
     */
    @ApiOperation(value = "注册接口")
    @ApiImplicitParam(name = "user", value = "用户名(username, 唯一)，邮箱(email, 未注册过)，密码(password, 长度区间6～16) json格式")
    @PostMapping("/register")
    public RedirectResult register(@RequestBody @Validated(RegisterGroup.class) User user)
        throws RegisterException {
        userService.registerUser(user);
        return RedirectResult.redirect(WebsitePathEnum.LOGIN.getPath());
    }

    /**
     * 登出接口，删除所有cookie
     * 重定向到登录界面
     */
    @GetMapping(value = "/logout")
    public RedirectResult logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.clearCookie(request, response);
        return RedirectResult.redirect("/login");
    }
}
