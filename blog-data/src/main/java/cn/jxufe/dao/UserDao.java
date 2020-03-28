package cn.jxufe.dao;

import cn.jxufe.entity.User;
import cn.jxufe.entity.vo.user.BasicUserInfo;

/**
 * @author PC
 */
public interface UserDao {

    BasicUserInfo selectBasicInfoByUserId(Integer userId);

    User selectByUserId(Integer userId);

    User selectByUsernameOrEmail(User user);

    Integer insertNewUser(User user);

    int updateInfoByUserIdSelective(User user);
}