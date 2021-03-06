package cn.jxufe.dao;

import cn.jxufe.entity.BlogContent;

public interface BlogContentDao {
    BlogContent selectContentByBlogId(Integer blogId);

    void insertBlogContent(BlogContent record);

    Integer deleteBlogByBlogId(Integer blogId);

    Integer updateBlogContentById(Integer blogId);
}