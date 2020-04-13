package cn.jxufe.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import cn.jxufe.entity.vo.user.SubscribeUser;

/**
 * @author hsw
 * @date 2020/3/26 17:50
 */
public interface SubscriptionDao {
    Integer selectFansNumByUserId(Integer userId);

    Integer selectSubNumByUserId(Integer userId);

    /**
     * 找用户关注的人
     * @author hsw
     * @date 15:53 2020/4/5
     **/
    List<SubscribeUser> selectUserByFanId(Integer fanId);
    List<SubscribeUser> selectFansBySubId(Integer userId);

    Boolean alreadySubscribed(@Param(value = "fanId") Integer fanId, @Param(value = "subId") Integer subId);
}
