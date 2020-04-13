package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import cn.jxufe.dao.SubscriptionDao;
import cn.jxufe.entity.vo.user.SubscribeUser;
import cn.jxufe.service.SubscriptionService;

/**
 * @author hsw
 * @date 2020/4/5 15:56
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Override
    public List<SubscribeUser> findSubscribedUser(Integer userId) {
        return subscriptionDao.selectUserByFanId(userId);
    }

    @Override
    public List<SubscribeUser> findFans(Integer userId) {
        return subscriptionDao.selectFansBySubId(userId);
    }

    @Override
    public Boolean isAlreadySubscribed(Integer fanId, Integer subId) {
        if (fanId == null || subId == null) {
            return false;
        }
        return subscriptionDao.alreadySubscribed(fanId, subId);
    }
}
