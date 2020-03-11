package cn.jxufe.bean;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author hsw
 * @date 2020/2/26 10:07 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Token {
    private Integer userId;
    private String loginToken;
    private Timestamp latestLoginAt;

    public Token(Integer userId, String loginToken) {
        this.userId = userId;
        this.loginToken = loginToken;
    }
}
