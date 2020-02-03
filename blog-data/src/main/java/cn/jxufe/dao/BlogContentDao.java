package cn.jxufe.dao;

import cn.jxufe.bean.BlogContent;

public interface BlogContentDao {
    int insert(BlogContent record);

    int insertSelective(BlogContent record);
}