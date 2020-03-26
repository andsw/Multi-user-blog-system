package cn.jxufe.dao;

/**
 * @author hsw
 * @date 2020/3/26 17:50
 */
public interface SubscriptionDao {
    Integer selectFansNumByUserId(Integer userId);

    Integer selectSubNumByUserId(Integer userId);
}
