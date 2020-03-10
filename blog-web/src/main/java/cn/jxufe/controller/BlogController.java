package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import cn.jxufe.bean.Blog;
import cn.jxufe.bean.BlogContent;
import cn.jxufe.dto.NormalResult;
import cn.jxufe.exception.BlogWritingException;
import cn.jxufe.service.BlogService;
import cn.jxufe.vo.blog.BlogInsertionVo;

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
     * 新增文章
     * @param vo
     * @return
     */
    @PostMapping(value = "/blog")
    public NormalResult<?> writeBlog(@RequestBody @Valid BlogInsertionVo vo) throws BlogWritingException {
        Blog blog = new Blog().setUserId(vo.getUserId())
            .setCorpusId(vo.getCorpusId()).setTitle(vo.getTitle())
            .setWordNum(vo.getWordNum());
        BlogContent content = new BlogContent().setContent(vo.getContent());
        blogService.insertBlog(blog, content);
        return NormalResult.success();
    }

    @DeleteMapping(value = "/blog/{blogId}")
    public NormalResult<?> deleteBlog(@PathVariable("blogId") Integer blogId) {
        blogService.deleteBlog(blogId);
        return NormalResult.success();
    }

}
