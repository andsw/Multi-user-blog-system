package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jxufe.entity.dto.NormalResult;
import cn.jxufe.service.CommentService;

/**
 * @author hsw
 * @date 2020/4/12 11:20
 */
@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/blog/{blogId}/comment")
    public NormalResult<?> getInitBlogComment(@PathVariable Integer blogId, @RequestParam(value = "commentNum", required = false) Integer initCommentNUm) {
        return NormalResult.successWithData(commentService.getInitBlogComments(blogId, initCommentNUm));
    }
}
