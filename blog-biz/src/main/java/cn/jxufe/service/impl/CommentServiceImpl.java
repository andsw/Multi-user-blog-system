package cn.jxufe.service.impl;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cn.jxufe.dao.BlogDao;
import cn.jxufe.dao.CommentDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.entity.Comment;
import cn.jxufe.entity.vo.comment.CommentWithUserInfoVo;
import cn.jxufe.entity.vo.comment.FistLoadedCommentVo;
import cn.jxufe.entity.vo.user.BasicUserInfo;
import cn.jxufe.service.CommentService;

/**
 * @author hsw
 * @date 2020/4/11 18:58
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BlogDao blogDao;

    /**
     * 分页获取文章的评论
     * 先获取第一集，然后再去获取它的子评论
     */
    @Override
    public List<FistLoadedCommentVo> getBlogComments(Integer blogId, Integer pageNum, Integer pageSize) {
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        return commentDao.selectFistLoadedCommentByBlogId(blogId);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommentWithUserInfoVo addComment(Comment comment) {
        // 事务
        commentDao.insertComment(comment);
        blogDao.plusCommentNum(comment.getBlogId());

        BasicUserInfo userInfo = userDao.selectBasicInfoByUserId(comment.getUserId());
        return new CommentWithUserInfoVo(comment.getId(),
            comment.getContent(), comment.getUserId(), userInfo.getUsername(), userInfo.getAvatar(),
            userInfo.getGender(), comment.getParentId(), null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteComment(Integer blogId, Integer commentId) {
        if (blogDao.minusCommentNum(blogId) == 1) {
            return commentDao.deleteComment(commentId) >= 1;
        }
        return false;
    }
}
