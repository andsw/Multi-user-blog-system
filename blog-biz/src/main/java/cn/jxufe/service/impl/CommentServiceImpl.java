package cn.jxufe.service.impl;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import cn.jxufe.dao.CommentDao;
import cn.jxufe.entity.vo.comment.FistLoadedCommentVo;
import cn.jxufe.service.CommentService;

/**
 * @author hsw
 * @date 2020/4/11 18:58
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

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
}
