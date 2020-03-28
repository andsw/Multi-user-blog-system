package cn.jxufe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.jxufe.entity.User;
import cn.jxufe.entity.dto.NormalResult;
import cn.jxufe.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author hsw
 * @date 2020/1/12 11:06
 */
@Api(tags = "用户数据操作接口")
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger("cn.jxufe.controller.UserController");

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "根据userId获取用户信息")
    @GetMapping(value = "/user/{userId}")
    public NormalResult<User> getUserInfo(@PathVariable(name = "userId") Integer userId) {
        User user;
        try {
            user = userService.getUserInfo(userId);
            logger.info("[UserController.getUserInfo] userId = " + userId);
        } catch (Exception e) {
            logger.error("[UserController.getUserInfo] userId = " + userId);
            return NormalResult.failureWithMessage("用户信息获取失败！");
        }
        return NormalResult.successWithData(user);
    }

    @GetMapping(value = "/user/basic/{userId}")
    public NormalResult<?> getBasicUserInfo(@PathVariable(name = "userId") Integer userId) {
        return NormalResult.successWithData(userService.getBasicUserInfo(userId));
    }
}
