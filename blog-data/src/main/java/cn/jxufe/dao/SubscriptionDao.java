package cn.jxufe.dao;

import cn.jxufe.bean.Subscription;
import cn.jxufe.bean.SubscriptionKey;

public interface SubscriptionDao {
    int deleteByPrimaryKey(SubscriptionKey key);

    int insert(Subscription record);

    int insertSelective(Subscription record);

    Subscription selectByPrimaryKey(SubscriptionKey key);

    int updateByPrimaryKeySelective(Subscription record);

    int updateByPrimaryKey(Subscription record);
}