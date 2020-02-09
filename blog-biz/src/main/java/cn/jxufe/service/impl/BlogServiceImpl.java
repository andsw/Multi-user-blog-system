package cn.jxufe.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

import cn.jxufe.bean.Blog;
import cn.jxufe.dao.BlogContentDao;
import cn.jxufe.dao.BlogDao;
import cn.jxufe.service.BlogService;

/**
 * @author hsw
 * @date 2020/1/12 19:27
 */
@Service
public class BlogServiceImpl implements BlogService {

    private BlogDao blogDao;
    private BlogContentDao blogContentDao;

    @Autowired
    public BlogServiceImpl(BlogDao blogDao, BlogContentDao blogContentDao) {
        this.blogDao = blogDao;
        this.blogContentDao = blogContentDao;
    }

    @Override
    public Long getSumReadNumByUserId(Integer userId) {

        return null;
    }

    @Override
    public List<Blog> getTopHottestBlog(Integer userId, int n) {
        if (userId == null) {
            throw new InvalidParameterException("userId is null");
        }
        PageHelper.startPage(0, 5);
        return blogDao.selectBlogByUserIdSortedByReadNum(userId);
    }

    @Override
    public Page<Blog> listUserBlogByPagination(int userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page<Blog>) blogDao.listByUserId(userId);
    }

}
