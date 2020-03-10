package cn.jxufe.dao;

import cn.jxufe.bean.BlogContent;

public interface BlogContentDao {
    BlogContent selectContentByBlogId(Integer blogId);

    void insertBlogContent(BlogContent record);

    Integer deleteBlogByBlogId(Integer blogId);

    Integer deleteBlogByCorpusId(Integer corpusId);
}