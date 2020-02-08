package cn.jxufe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.Arrays;

import cn.jxufe.bean.User;
import cn.jxufe.dto.NormalResult;
import cn.jxufe.service.UserService;

/**
 * @author hsw
 * @date 2020/1/12 11:06
 */
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger("cn.jxufe.controller.UserController");

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
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

}
