package cn.jxufe.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.jxufe.bean.Token;
import cn.jxufe.dto.RedirectResult;
import cn.jxufe.service.TokenService;
import cn.jxufe.util.JsonUtil;

/**
 * @author hsw
 * @date 2020/2/29 9:54 下午
 */
@WebServlet(name = "鉴权过滤器", urlPatterns = "/*")
public class TokenAuthenticateFilter implements Filter {

    @Value(value = "${cookie.name.token}")
    private String tokenCookieName;

    @Value(value = "${cookie.name.userId}")
    private String userIdCookieName;

    @Autowired
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException {
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        Map<String, String> cookieMap = Arrays.stream(cookies)
            .filter(c -> c.getName().equals(tokenCookieName) || c.getName().equals(userIdCookieName))
            .collect(Collectors.toMap(Cookie::getName, Cookie::getValue, (c1, c2) -> c1));
        String userIdStr = cookieMap.getOrDefault(userIdCookieName, null);
        String token = cookieMap.getOrDefault(tokenCookieName, null);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ServletOutputStream outputStream = response.getOutputStream();

        if (StringUtils.isBlank(token) || StringUtils.isBlank(userIdStr) || token.length() != 32) {
            outputStream.write(JsonUtil.object2Json(RedirectResult.redirectWithMsg("/login", "未登录，跳转至登录界面")).getBytes());
        } else {
            Integer userId = Integer.parseInt(userIdStr);
            Token tokenFromDb = tokenService.selectTokenByUserId(userId);

        }
    }
}
