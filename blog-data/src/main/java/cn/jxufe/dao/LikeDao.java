package cn.jxufe.dao;

import cn.jxufe.bean.Like;
import cn.jxufe.bean.LikeKey;

public interface LikeDao {
    int deleteByPrimaryKey(LikeKey key);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(LikeKey key);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);
}