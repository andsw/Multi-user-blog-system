package cn.jxufe.dao;

import cn.jxufe.bean.User;

public interface UserDao {

//    customize

    User selectByUserId(Integer userId);

//    auto

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}