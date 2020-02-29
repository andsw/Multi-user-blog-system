package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.Token;
import cn.jxufe.dao.TokenDao;
import cn.jxufe.service.TokenService;

/**
 * @author hsw
 * @date 2020/2/29 11:16 下午
 */
@Service
public class TokenServiceImpl implements TokenService {

    private final TokenDao tokenDao;

    @Autowired
    public TokenServiceImpl(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Override
    public Token selectTokenByUserId(Integer userId) {
        return tokenDao.selectTokenByUserId(userId);
    }

}
