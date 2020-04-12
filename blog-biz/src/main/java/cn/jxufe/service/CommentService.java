package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.vo.FistLoadedCommentVo;

/**
 * @author hsw
 * @date 2020/4/11 18:58
 */
public interface CommentService {
    List<FistLoadedCommentVo> getInitBlogComments(Integer blogId, Integer parentCommentNum);
}
