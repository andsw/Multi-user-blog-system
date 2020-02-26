package cn.jxufe.bean;

import java.sql.Timestamp;

import lombok.Data;

/**
 * @author hsw
 * @date 2020/2/26 10:07 下午
 */
@Data
public class Token {
    private Integer userId;
    private String loginToken;
    private Timestamp latestLoginAt;

    public Token(Integer userId, String loginToken) {
        this.userId = userId;
        this.loginToken = loginToken;
    }
}
