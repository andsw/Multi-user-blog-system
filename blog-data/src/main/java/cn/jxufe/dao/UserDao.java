package cn.jxufe.dao;

import cn.jxufe.bean.User;

public interface UserDao {

    User selectByUserId(Integer userId);

    User selectByUsernameOrEmail(User user);

    Integer insertNewUser(User user);

    int updateInfoByUserIdSelective(User user);

    int plusNumByUserIdSelective(Integer userId, String colName, int num);

    int minusNumByUserIdSelective(Integer userId, String colName, int num);
}