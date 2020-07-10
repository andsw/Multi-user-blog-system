package cn.jxufe.util;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hsw
 * @date 2020/3/20 11:04 上午
 */
public class CookieUtil {
    public static HashMap<String, String> getCookieMap(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        HashMap<String, String> res = new HashMap<>(cookies.length);
        for (Cookie cookie : cookies) {
            res.put(cookie.getName(), cookie.getValue());
        }
        return res;
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        final Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);

            }
        }
    }

    public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
        for (Cookie c : request.getCookies()) {
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);
        }
    }
}
