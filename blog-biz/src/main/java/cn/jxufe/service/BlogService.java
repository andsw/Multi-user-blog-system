package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.Blog;
import cn.jxufe.entity.BlogContent;
import cn.jxufe.entity.vo.blog.BlogListVo;
import cn.jxufe.entity.vo.blog.BlogReadingVo;
import cn.jxufe.exception.BlogWritingException;

/**
 * @author hsw
 * @date 2020/1/12 19:23
 */
public interface BlogService {

    Integer getSumReadNumByUserId(Integer userId, int blogNum);

    List<Blog> getTopHottestBlog(Integer userId, int n);

    boolean insertBlog(Blog blog, BlogContent content) throws BlogWritingException;

    boolean deleteBlog(Integer blogId);

    BlogReadingVo getBlog(Integer blogId, Integer userId);

    BlogListVo getUsersBlogList(Integer userId, Integer corpusId, int pageNum, Integer selfUserId);

}
