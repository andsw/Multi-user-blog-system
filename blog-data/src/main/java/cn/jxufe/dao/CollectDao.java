package cn.jxufe.dao;

import cn.jxufe.bean.Collect;
import cn.jxufe.bean.CollectKey;

public interface CollectDao {
    int deleteByPrimaryKey(CollectKey key);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(CollectKey key);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}