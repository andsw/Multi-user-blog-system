package cn.jxufe.dao;

import cn.jxufe.entity.Comment;

/**
 *
 * @author hsw
 * @date 2020/3/11 3:56 下午
 */
public interface CommentDao {
    Comment insertComment(Comment comment);

    Integer deleteComment(Integer commentId);

    /**
     * 只有修改内容一个场景
     */
    Integer updateCommentContent(Integer id, String content);
}
