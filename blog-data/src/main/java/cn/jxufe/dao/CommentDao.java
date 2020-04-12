package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.entity.Comment;
import cn.jxufe.entity.vo.FistLoadedCommentVo;

/**
 *
 * @author hsw
 * @date 2020/3/11 3:56 下午
 */
public interface CommentDao {

    List<Comment> selectChildCommentByParentId(Integer parentId);

    List<FistLoadedCommentVo> selectFistLoadedCommentByBlogId(Integer blogId, int parentCommentNum);
}