package cn.jxufe.dao;

import cn.jxufe.bean.Token;

/**
 * @author hsw
 * @date 2020/2/26 10:26 下午
 */
public interface TokenDao {
    Token selectTokenByUserId(Integer userId);
    void insertToken(Token token);
}
