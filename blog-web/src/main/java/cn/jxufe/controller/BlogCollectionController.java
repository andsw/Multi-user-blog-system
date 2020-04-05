package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cn.jxufe.entity.dto.NormalResult;
import cn.jxufe.entity.vo.blog.CollectedBlog;
import cn.jxufe.service.BlogCollectionService;

/**
 * @author hsw
 * @date 2020/4/1 16:38
 */
@RestController
public class BlogCollectionController {
    private final BlogCollectionService blogCollectionService;

    @Autowired
    public BlogCollectionController(BlogCollectionService blogCollectionService) {
        this.blogCollectionService = blogCollectionService;
    }

    @GetMapping(value = "/user/{userId}/collected_blog")
    public NormalResult<?> getUserCollectedBlogList(@PathVariable Integer userId) {
        final List<CollectedBlog> collectedByUserId = blogCollectionService.getCollectedByUserId(userId);
        return NormalResult.successWithData(collectedByUserId);
    }


    @GetMapping(value = "/user/{userId}/favorites")
    public NormalResult<?> getCorpusWithCollectedBlogIn(@PathVariable Integer userId) {
        return NormalResult.successWithData(blogCollectionService.getUserFavorites(userId));
    }
}
