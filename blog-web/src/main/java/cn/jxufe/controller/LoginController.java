package cn.jxufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import cn.jxufe.dto.NormalResult;
import cn.jxufe.util.EncodeUtil;
import io.swagger.annotations.ApiOperation;

/**
 * @author hsw
 * @date 2020/2/22 9:21 下午
 */
@Controller
public class LoginController {

    @ApiOperation(value = "登录接口，前端ajax请求，dataType为json")
    @PostMapping(value = "/login")
    public NormalResult<?> login(@NotBlank String email,
                                 @NotBlank String password,
                                 @NotBlank String verifyCode,
                                 HttpServletRequest request) {
        String[] utilResult = EncodeUtil.generate(password);
        String cipherText = utilResult[0];
        String salt = utilResult[1];

        return null;
    }
}
