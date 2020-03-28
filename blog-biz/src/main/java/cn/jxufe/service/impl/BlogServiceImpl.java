package cn.jxufe.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sun.rmi.runtime.Log;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import cn.jxufe.dao.BlogCollectionDao;
import cn.jxufe.dao.LoveDao;
import cn.jxufe.entity.Blog;
import cn.jxufe.entity.BlogContent;
import cn.jxufe.dao.BlogContentDao;
import cn.jxufe.dao.BlogDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.entity.vo.blog.BlogListItemVo;
import cn.jxufe.entity.vo.blog.BlogListVo;
import cn.jxufe.entity.vo.blog.BlogReadingVo;
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
    private CorpusService corpusService;
    private BlogCollectionDao blogCollectionDao;
    private LoveDao loveDao;


    @Autowired
    public BlogServiceImpl(BlogDao blogDao, BlogContentDao blogContentDao, CorpusService corpusService, BlogCollectionDao blogCollectionDao, LoveDao loveDao) {
        this.blogDao = blogDao;
        this.blogContentDao = blogContentDao;
        this.corpusService = corpusService;
        this.blogCollectionDao = blogCollectionDao;
        this.loveDao = loveDao;
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
            corpusService.updateBlogNum(blog.getCorpusId(), 1);
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

    @Override
    public BlogReadingVo getBlog(Integer blogId, Integer userId) {
        final Blog blog = blogDao.selectByBlogId(blogId);
        if (blog != null) {
            final BlogContent blogContent = blogContentDao.selectContentByBlogId(blogId);
            final List<Integer> collector = blogCollectionDao.selectCollectorByBlogId(blogId);
            final List<Integer> lover = loveDao.selectLoverByBlogId(blogId);
            // 未登录时就可以确保不会有过收藏点赞
            if (userId == null) {
                userId = -1;
            }
            return new BlogReadingVo(blog, blogContent == null ? "" : blogContent.getContent(), collector.size(),
                collector.contains(userId), lover.size(), lover.contains(userId));
        }
        return null;
    }

    /**
     * TODO 记得添加其他列的排序！
     * @author hsw
     * @date 22:24 2020/3/28
     * @param userId 表示请求文章所属用户
     * @param pageNum 第几页的文章（时间排序，默认页数5）
     * @param selfUserId 本人的userId，判断是否属于自己的文章，不是自己的则显示点赞按钮，是自己的显示 “编辑”， “删除”按钮！（前端逻辑）
     * @return java.util.List<cn.jxufe.entity.vo.blog.BlogListItemVo>
     **/
    @Override
    public BlogListVo getUsersBlogList(Integer userId, Integer corpusId, int pageNum, Integer selfUserId) {
        if (userId == null || userId <= 0) {
            throw new InvalidParameterException("文章作者信息有误！");
        }
        List<BlogListItemVo> voList = new ArrayList<>(5);
        PageHelper.startPage(pageNum, 5);
        final Page<Blog> blogs = (Page<Blog>) blogDao.listByUserId(userId, corpusId == -1 ? null : corpusId);
        if (blogs != null) {
            for (Blog blog : blogs) {
                final List<Integer> collector = blogCollectionDao.selectCollectorByBlogId(blog.getId());
                final List<Integer> lover = loveDao.selectLoverByBlogId(blog.getId());
                BlogListItemVo vo = new BlogListItemVo(blog,
                    collector.size(), selfUserId != -1 && collector.contains(selfUserId),
                    lover.size(), selfUserId != -1 && lover.contains(selfUserId));
                voList.add(vo);
            }
            return new BlogListVo(voList, userId.equals(selfUserId), blogs.getPages());
        }
        return null;
    }
}
