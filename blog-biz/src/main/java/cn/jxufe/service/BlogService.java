package cn.jxufe.service;

import com.github.pagehelper.Page;

import java.util.List;

import cn.jxufe.bean.Blog;
import cn.jxufe.bean.BlogContent;
import cn.jxufe.exception.BlogWritingException;

/**
 * @author hsw
 * @date 2020/1/12 19:23
 */
public interface BlogService {

    Integer getSumReadNumByUserId(Integer userId, int blogNum);

    List<Blog> getTopHottestBlog(Integer userId, int n);

    /**
     * 分页获取某用户的文章列表
     *
     * @param userId   .
     * @param pageNum  页数
     * @param pageSize 每页博客数量
     * @return 用户主页的文章列表
     */
    Page<Blog> listUserBlogByPagination(Integer userId, int pageNum, int pageSize);

    boolean insertBlog(Blog blog, BlogContent content) throws BlogWritingException;

    boolean deleteBlog(Integer blogId);
}
