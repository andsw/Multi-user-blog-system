package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.bean.Blog;

public interface BlogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> listByUserId(Integer userId);
}