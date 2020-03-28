package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cn.jxufe.entity.Blog;
import cn.jxufe.entity.BlogContent;
import cn.jxufe.entity.dto.NormalResult;
import cn.jxufe.entity.vo.blog.BlogListVo;
import cn.jxufe.entity.vo.blog.BlogReadingVo;
import cn.jxufe.exception.BlogWritingException;
import cn.jxufe.service.BlogService;
import cn.jxufe.entity.vo.blog.BlogInsertionVo;
import cn.jxufe.util.CookieUtil;

/**
 * @author hsw
 * @date 2020/3/10 3:26 下午
 */
@RestController
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * @param userId, pageNum, request, corpusId: corpusId为-1时表示获取所有文集的文章分页
     * @return cn.jxufe.entity.dto.NormalResult<?>
     * @author hsw
     * @date 22:20 2020/3/28
     **/
    @GetMapping(value = "/user/{userId}/corpus/{corpusId}/page/{pageNum}/blog")
    public NormalResult<?> getUserBlogList(@PathVariable Integer userId, @PathVariable() Integer corpusId, @PathVariable Integer pageNum, HttpServletRequest request) {
        String selfUserId = CookieUtil.getCookieValue(request, "userId");
        final BlogListVo usersBlogList = blogService.getUsersBlogList(userId, corpusId, pageNum, selfUserId == null ? -1 : Integer.parseInt(selfUserId));
        return NormalResult.successWithData(usersBlogList);
    }

    /**
     * 新增文章
     * 事务方式写入 info和content
     * 更新corpus的blogNum，user的blogNum
     * @param vo
     * @return
     */
    @PostMapping(value = "/blog")

    public NormalResult<?> writeBlog(@RequestBody @Valid BlogInsertionVo vo) throws BlogWritingException {
        Blog blog = new Blog().setUserId(vo.getUserId())
            .setCorpusId(vo.getCorpusId()).setTitle(vo.getTitle())
            .setWordNum(vo.getWordNum());
        BlogContent content = new BlogContent().setContent(vo.getContent());
        return blogService.insertBlog(blog, content) ? NormalResult.success() : NormalResult.failure();
    }

    @GetMapping(value = "/blog/{blogId}")
    public NormalResult<?> readBlog(@PathVariable("blogId") Integer blogId, HttpServletRequest request) {
        final String userId = CookieUtil.getCookieValue(request, "userId");
        final BlogReadingVo blog = blogService.getBlog(blogId, userId == null ? -1 : Integer.parseInt(userId));
        if (blog != null) {
            return NormalResult.successWithData(blog);
        }
        return NormalResult.failureWithMessage("未找到文章！");
    }

    @DeleteMapping(value = "/blog/{blogId}")
    public NormalResult<?> deleteBlog(@PathVariable("blogId") Integer blogId) {
        blogService.deleteBlog(blogId);
        return NormalResult.success();
    }

}
