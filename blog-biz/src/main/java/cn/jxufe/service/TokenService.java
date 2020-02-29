package cn.jxufe.service;

import cn.jxufe.bean.Token;

/**
 * @author hsw
 * @date 2020/2/29 11:14 下午
 */
public interface TokenService {
    Token selectTokenByUserId(Integer userId);
}
