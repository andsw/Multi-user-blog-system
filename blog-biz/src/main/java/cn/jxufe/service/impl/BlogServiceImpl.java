package cn.jxufe.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cn.jxufe.bean.Blog;
import cn.jxufe.bean.BlogContent;
import cn.jxufe.dao.BlogContentDao;
import cn.jxufe.dao.BlogDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.exception.BlogWritingException;
import cn.jxufe.service.BlogService;
import cn.jxufe.service.CorpusService;

/**
 * @author hsw
 * @date 2020/1/12 19:27
 */
@Service
public class BlogServiceImpl implements BlogService {

    private BlogDao blogDao;
    private BlogContentDao blogContentDao;
    private final UserDao userDao;

    private CorpusService corpusService;


    @Autowired
    public BlogServiceImpl(BlogDao blogDao, BlogContentDao blogContentDao, CorpusService corpusService, UserDao userDao) {
        this.blogDao = blogDao;
        this.blogContentDao = blogContentDao;
        this.corpusService = corpusService;
        this.userDao = userDao;
    }

    /**
     * 获取用户文章总阅读量
     * @param userId
     * @param blogNum
     * @return
     */
    @Override
    public Integer getSumReadNumByUserId(Integer userId, int blogNum) {
        return blogDao.selectSumReadNumByUserIdLimit(userId, blogNum);
    }

    @Override
    public List<Blog> getTopHottestBlog(Integer userId, int n) {
        PageHelper.startPage(0, n);
        return blogDao.selectBlogByUserIdSortedByReadNum(userId);
    }

    @Override
    public Page<Blog> listUserBlogByPagination(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page<Blog>) blogDao.listByUserId(userId);
    }

    /**
     * 检测corpus_id是否存在，检测BlogContent和title等
     * 注意文章信息和内容插入必须同步，所以这里必须用事务。
     *
     * @param blog    blog信息
     * @param content 文章内容，判空交给hibernate-validation
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean insertBlog(Blog blog, BlogContent content) throws BlogWritingException {
        if (!corpusService.checkIfCorpusExists(blog.getCorpusId())) {
            throw new BlogWritingException("找不到文章所属文集");
        }
        if (blogDao.insertBlog(blog) == 1) {
            content.setBlogId(blog.getId());
            blogContentDao.insertBlogContent(content);
            userDao.plusNumByUserIdSelective(blog.getUserId(), "blogNum", 1);
            return true;
        }
        return false;
    }

    /**
     * 同样事务，需要同时删除信息及内容
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean deleteBlog(Integer blogId) {
        if (blogId == null) {
            return false;
        }
        return false;
    }
}
