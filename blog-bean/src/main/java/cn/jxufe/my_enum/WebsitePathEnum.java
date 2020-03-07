package cn.jxufe.my_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hsw
 * @create 2020-03-07  8:29 下午
 */
@AllArgsConstructor
@Getter
public enum WebsitePathEnum {
    REGISTER("register.html"),
    LOGIN("/login.html"),
    USER_HOMEPAGE("/index.html"),
    HOMEPAGE("/home.html");

    private String path;

    @Override
    public String toString() {
        return path;
    }
}
