package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.Comment;
import cn.jxufe.entity.vo.comment.CommentWithUserInfoVo;
import cn.jxufe.entity.vo.comment.FistLoadedCommentVo;

/**
 * @author hsw
 * @date 2020/4/11 18:58
 */
public interface CommentService {
    List<FistLoadedCommentVo> getBlogComments(Integer blogId, Integer pageNum, Integer pageSize);

    CommentWithUserInfoVo addComment(Comment comment);

    boolean deleteComment(Integer blogId, Integer commentId);
}
