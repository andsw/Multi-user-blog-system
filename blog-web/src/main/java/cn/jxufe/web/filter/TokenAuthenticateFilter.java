package cn.jxufe.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
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
@Component
@WebFilter(filterName = "鉴权过滤器", urlPatterns = "/home/*")
public class TokenAuthenticateFilter implements Filter {

    @Value(value = "${cookie.name.token}")
    private String tokenCookieName;

    @Value(value = "${cookie.name.userId}")
    private String userIdCookieName;

    private final TokenService tokenService;

    @Autowired
    public TokenAuthenticateFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        final String requestURI = ((HttpServletRequest) request).getRequestURI();
        if (requestURI.equals("/login") || requestURI.equals("/register")) {
            filterChain.doFilter(request, response);
        } else {
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            ServletOutputStream outputStream = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            if (cookies == null) {
                returnRedirectResponse(outputStream, "未登录，跳转至登录界面");
                return;
            }
            Map<String, String> cookieMap = Arrays.stream(cookies)
                .filter(c -> c.getName().equals(tokenCookieName) || c.getName().equals(userIdCookieName))
                .collect(Collectors.toMap(Cookie::getName, Cookie::getValue, (c1, c2) -> c1));
            String userIdStr = cookieMap.getOrDefault(userIdCookieName, null);
            String token = cookieMap.getOrDefault(tokenCookieName, null);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            if (StringUtils.isBlank(token) || StringUtils.isBlank(userIdStr) || token.length() != 32) {
                returnRedirectResponse(outputStream, "未登录，跳转至登录界面");
            } else {
                Integer userId = Integer.parseInt(userIdStr);
                Token tokenFromDb = tokenService.selectTokenByUserId(userId);
                if (tokenService.isTokenExpire(tokenFromDb.getLatestLoginAt())) {
                    returnRedirectResponse(outputStream, "登录信息已过期，请重新登录");
                } else if (!token.equals(tokenFromDb.getLoginToken())) {
                    returnRedirectResponse(outputStream, "登录信息错误，请重新登录");
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        }
    }

    private void returnRedirectResponse(OutputStream outputStream, String message) throws IOException {
        outputStream.write(JsonUtil.object2Json(RedirectResult.redirectWithMsg("/login.html", message)).getBytes());
    }
}
