package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jxufe.entity.Comment;
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
    public NormalResult<?> getCommentPagination(@PathVariable(required = false) Integer blogId, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (blogId == null) {
            NormalResult.failureWithMessage("blogId不能为空！");

        }
        return NormalResult.successWithData(commentService.getBlogComments(blogId, pageNum, pageSize));
    }

    @PostMapping(value = "/blog/{blogId}/user/{userId}/parent/{parentId}/comment")
    public NormalResult<?> comment(@PathVariable Integer blogId, @PathVariable Integer userId, @PathVariable Integer parentId, @RequestBody String content) {
        Comment comment = new Comment().setBlogId(blogId).setContent(content).setUserId(userId).setParentId(parentId);

        return NormalResult.successWithData(commentService.addComment(comment));
    }
}
