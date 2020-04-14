package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.entity.Comment;
import cn.jxufe.entity.vo.comment.CommentWithUserInfoVo;
import cn.jxufe.entity.vo.comment.FistLoadedCommentVo;

/**
 *
 * @author hsw
 * @date 2020/3/11 3:56 下午
 */
public interface CommentDao {

    List<CommentWithUserInfoVo> selectChildCommentByParentId(Integer parentId);

    List<FistLoadedCommentVo> selectFistLoadedCommentByBlogId(Integer blogId);

    void insertComment(Comment comment);

}