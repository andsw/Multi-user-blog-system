package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.vo.user.SubscribeUser;

/**
 * @author hsw
 * @date 2020/4/5 15:56
 */
public interface SubscriptionService {
    List<SubscribeUser> findSubscribedUser(Integer userId);

    List<SubscribeUser> findFans(Integer userId);
}
